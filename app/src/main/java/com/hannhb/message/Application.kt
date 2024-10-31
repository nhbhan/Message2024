package com.hannhb.message

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication
//import com.facebook.drawee.backends.pipeline.Fresco
//import com.facebook.imagepipeline.core.ImagePipelineConfig
//import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig
//import com.thefinestartist.Base
import com.hannhb.message.core.helpers.KoinConfigurationHelper
import com.hannhb.message.module.message.domain.model.database.MessageDatabase
//import com.hannhb.message.module.message.domain.model.entities.Base

class Application : MultiDexApplication() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
        lateinit var messageDatabase: MessageDatabase
    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        mContext = applicationContext
//        Base.initialize(mContext)
        initInjection()
        initMessageDatabase()
        initZomInOrOutImageView()
    }

    private fun initMessageDatabase() {
        messageDatabase = MessageDatabase.invoke(this@Application)
    }

    private fun initInjection() {
        KoinConfigurationHelper.invoke(this@Application)
    }

    private fun initZomInOrOutImageView() {
//        val config = ImagePipelineConfig.newBuilder(this)
//            .setProgressiveJpegConfig(SimpleProgressiveJpegConfig())
//            .setResizeAndRotateEnabledForNetwork(true)
//            .setDownsampleEnabled(true)
//            .build()
//        Fresco.initialize(this, config)
    }
}
