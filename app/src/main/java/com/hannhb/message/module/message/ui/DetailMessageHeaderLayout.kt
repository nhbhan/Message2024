package com.hannhb.message.module.message.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.hannhb.message.R
import com.hannhb.message.databinding.DetailMessageHeaderLayoutBinding
import com.squareup.phrase.Phrase

class DetailMessageHeaderLayout @JvmOverloads constructor(
    private val viewModel: DetailMessageViewModel,
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var binding: DetailMessageHeaderLayoutBinding

    init {
        initView()
        setData()
    }

    private fun initView() {
        binding = DetailMessageHeaderLayoutBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    private fun setData() {
        val name = viewModel.getFullName()
        val avatar = viewModel.getAvatarUrl()
//        binding.avatarView?.loadImage(
//            name,
//            avatar,
//            ContextCompat.getDrawable(context, R.drawable.ic_default_avatar)
//        )
        binding.titleTextView?.text = viewModel.getTitle()
        binding.contentTextView?.text = viewModel.getContent()
        binding.attachmentTextView?.text = Phrase.from(context?.getString(R.string.attachment))
            .put("attachment", viewModel.getNumberOfAttachments().toString()).format().toString()
        binding.timeTextView?.text = viewModel.getTime()
        binding.nameTextView?.text = name
    }
}
