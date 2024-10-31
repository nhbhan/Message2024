package com.hannhb.message.module.message.data.repository

import androidx.lifecycle.LiveData
import com.hannhb.message.module.message.data.datasource.MessageDataSource
import com.hannhb.message.module.message.domain.model.entities.Message

open class MessageRepository(private val messageDataSource: MessageDataSource) {
    fun getNormalMessages(): LiveData<List<Message>>? {
        return messageDataSource.getNormalMessages()
    }

    fun getFavoriteMessage(): LiveData<List<Message>>? {
        return messageDataSource.getFavouriteMessages()
    }

    suspend fun saveMessage(messageId: Int, favourite: Boolean) {
        return messageDataSource.saveFavouriteMessage(messageId, favourite)
    }
}
