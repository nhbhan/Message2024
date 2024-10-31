package com.hannhb.message.module.splash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.hannhb.message.R
import com.hannhb.message.core.ui.navigation.NavigationFragment
import com.hannhb.message.databinding.SplashFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel



class SplashFragment : NavigationFragment<SplashViewModel>() {
    private var adapter: Adapter? = null
    lateinit var binding: SplashFragmentBinding

    companion object {
        private const val SPLASH_SCREEN_TIME = 1000L
    }

    override val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initRecyclerView()
        initData()
    }

    private fun initData() {
        val data: MutableList<String> = ArrayList()
        data.add("BXH")
        data.add("Nghe nhạc ca sĩ yêu thích")
        data.add("Mở nhạc theo chủ đề")
        data.add("Mở nhạc theo mùa")
        data.add("Mở nhạc theo tâm trạng")

        adapter?.setList(data)
        adapter?.notifyDataSetChanged()
    }

    private fun initRecyclerView(){
        adapter = Adapter()
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView?.adapter = adapter
    }

    class Adapter: BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_layout, ArrayList()) {
        override fun convert(holder: BaseViewHolder, item: String) {
            holder.setText(R.id.contentTextView, item.toString())
        }
    }


}
