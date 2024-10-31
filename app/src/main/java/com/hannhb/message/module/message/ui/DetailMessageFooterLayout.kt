package com.hannhb.message.module.message.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.hannhb.message.R
import com.hannhb.message.databinding.DetailMessageFooterLayoutBinding
import com.squareup.phrase.Phrase

class DetailMessageFooterLayout @JvmOverloads constructor(
    private val viewModel: DetailMessageViewModel,
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    lateinit var binding: DetailMessageFooterLayoutBinding

    init {
        initView()
        initEventListeners()
        setText()
    }

    private fun initView() {
        binding = DetailMessageFooterLayoutBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    private fun setText() {
        binding.commentTextView.text = Phrase.from(context?.getString(R.string.comment))
            .put("comment", viewModel.getTotalComments().toString()).format().toString()
    }

    private fun initEventListeners() {
        binding.replyButton.setOnClickListener { }

        binding.forwardButton.setOnClickListener { }
    }
}
