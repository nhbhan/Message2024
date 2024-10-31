package com.hannhb.message.module.message.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hannhb.message.R
import com.hannhb.message.databinding.MessageFragmentBinding
import com.hannhb.message.core.helpers.TimestampHelper
import com.hannhb.message.core.ui.navigation.NavigationFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.hannhb.message.module.message.domain.model.entities.Message

class MessageFragment : NavigationFragment<MessageViewModel>() {
    private var adapter: MessageAdapter? = null
    private var favouriteAdapter: MessageAdapter? = null
    private lateinit var binding: MessageFragmentBinding

    override val viewModel: MessageViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MessageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun initViewModelObservers() {
        super.initViewModelObservers()
        viewModel.normalMessage?.observe(
            viewLifecycleOwner
        ) {
            val unwrappedData = it ?: ArrayList()
            adapter?.data = unwrappedData.toMutableList()
            adapter?.notifyDataSetChanged()
        }

        viewModel.favouriteMessages?.observe(
            viewLifecycleOwner,
            Observer {
                val unwrappedData = it ?: ArrayList()
                if (unwrappedData.size == 0) {
                    binding.favouriteTextView?.visibility = View.GONE
                    binding.favouriteMessageRecyclerView?.visibility = View.GONE
                    return@Observer
                }
                binding.favouriteTextView?.visibility = View.VISIBLE
                binding.favouriteMessageRecyclerView?.visibility = View.VISIBLE
                favouriteAdapter?.data = unwrappedData.toMutableList()
                favouriteAdapter?.notifyDataSetChanged()
            }
        )
    }

    private fun init() {
        initRecyclerView()
        initFavouriteRecyclerView()
    }

    private fun initRecyclerView() {
        val context = context ?: return
        adapter = MessageAdapter()
        binding.messageRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter?.addChildClickViewIds(R.id.messageRowLayout, R.id.favouriteImageButton)
        binding.messageRecyclerView.adapter = adapter
        adapter?.addChildClickViewIds(R.id.messageRowLayout, R.id.favouriteImageButton)
        adapter?.setOnItemChildClickListener { _, view, position ->
            val message = adapter?.getItem(position) ?: return@setOnItemChildClickListener
            getItemChildClicked(message, view)
        }
    }

    private fun initFavouriteRecyclerView() {
        val context = context ?: return
        favouriteAdapter = MessageAdapter()
        binding.favouriteMessageRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.favouriteMessageRecyclerView.isNestedScrollingEnabled = false
        favouriteAdapter?.addChildClickViewIds(R.id.messageRowLayout, R.id.favouriteImageButton)
        binding.favouriteMessageRecyclerView.adapter = favouriteAdapter
        favouriteAdapter?.addChildClickViewIds(R.id.messageRowLayout, R.id.favouriteImageButton)
        favouriteAdapter?.setOnItemChildClickListener { _, view, position ->
            val message = favouriteAdapter?.getItem(position) ?: return@setOnItemChildClickListener
            getItemChildClicked(message, view)
        }
    }

    private fun getItemChildClicked(message: Message, view: View) {
        when (view.id) {
            R.id.messageRowLayout -> {
                navigate(MessageFragmentDirections.messageFragmentToDetailMessageFragment().setMessage(message))
            }

            R.id.favouriteImageButton -> {
                val messageId = message.id ?: return
                val favorite = !message.isFavourite
                viewModel.saveMessage(messageId, favorite)
            }

            else -> {}
        }
    }

    class MessageAdapter : BaseQuickAdapter<Message, BaseViewHolder>(R.layout.message_item_layout, ArrayList()) {
        override fun convert(holder: BaseViewHolder, item: Message) {
            holder.setText(R.id.contentTextView, item.title)
            holder.setText(R.id.nameTextView, item.owner?.fullName)
            holder.setText(R.id.timeTextView, TimestampHelper.getDateTime(timestamp = item.postTime, "dd/MM/yyyy"))
            val avatarView = holder.getView<AppCompatImageView>(R.id.avatarView)
            val avatar = item.owner?.avatar ?: ""
            val fullName = item.owner?.fullName ?: ""
//            avatarView.loadImage(fullName, avatar, ContextCompat.getDrawable(Application.mContext, R.drawable.ic_default_avatar))
            val imageButton = holder.getView<AppCompatImageButton>(R.id.favouriteImageButton)
            imageButton.setImageDrawable(ContextCompat.getDrawable(context, if (item.isFavourite) R.drawable.ic_favourite else R.drawable.ic_unfavourite))
        }
    }
}
