package com.hannhb.message.module.message.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hannhb.message.Application
import com.hannhb.message.R
import com.hannhb.message.databinding.DetailMessageFragmentBinding
import com.hannhb.message.core.extensions.load
import com.hannhb.message.core.ui.navigation.NavigationFragment
import com.hannhb.message.module.message.domain.model.entities.Attachment
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailMessageFragment : NavigationFragment<DetailMessageViewModel>() {
    private var adapter: Adapter? = null

    private val args: DetailMessageFragmentArgs by navArgs()

    override val viewModel: DetailMessageViewModel by viewModel()

    private lateinit var binding: DetailMessageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailMessageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun loadArguments() {
        super.loadArguments()
        viewModel.message = args.message
    }

    private fun init() {
        initToolbar()
        initRecyclerView()
        initAttachmentData()
    }

    private fun initToolbar() {
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
    }

    private fun initRecyclerView() {
        adapter = Adapter()
        binding.attachmentRecyclerView.layoutManager = GridLayoutManager(context, 4)
        binding.attachmentRecyclerView.adapter = adapter
        val headerLayout = DetailMessageHeaderLayout(context = context, viewModel = viewModel)
        adapter?.addHeaderView(headerLayout)
        val footerView = DetailMessageFooterLayout(context = context, viewModel = viewModel)
        adapter?.addFooterView(footerView)

        adapter?.setOnItemClickListener { _, _, position ->
            onAttachmentItemClicked(position)
        }
    }

    private fun initAttachmentData() {
        adapter?.data = viewModel.getAttachments()
        adapter?.notifyDataSetChanged()
    }

    private fun onAttachmentItemClicked(position: Int) {
        val item = adapter?.getItem(position) ?: return
        val urlImage = item.url ?: ""
//        val hierarchyBuilder = GenericDraweeHierarchyBuilder.newInstance(resources)
//            .setPlaceholderImage(R.drawable.ic_default_avatar)
//
//        ImageViewer.Builder(
//            context,
//            ArrayList<String>().apply {
//                add(urlImage)
//            }
//        )
//            .setStartPosition(0)
//            .setCustomDraweeHierarchyBuilder(hierarchyBuilder)
//            .show()
    }

    inner class Adapter : BaseQuickAdapter<Attachment, BaseViewHolder>(R.layout.detail_message_attachment_item_layout, ArrayList()) {
        override fun convert(holder: BaseViewHolder, item: Attachment) {
            val imageView = holder.getView<ImageView>(R.id.attachmentImageView)
            imageView.load(context = Application.mContext, model = item.url)
        }
    }
}
