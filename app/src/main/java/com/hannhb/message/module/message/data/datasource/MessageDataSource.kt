package com.hannhb.message.module.message.data.datasource

import androidx.lifecycle.LiveData
import com.hannhb.message.Application
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.hannhb.message.module.message.domain.model.entities.Message

interface MessageDataSource {
    fun getFavouriteMessages(): LiveData<List<Message>>?
    fun getNormalMessages(): LiveData<List<Message>>?
    suspend fun saveFavouriteMessage(messageId: Int, favorite: Boolean)
}

class MessageDateSourceImp : MessageDataSource {
    override fun getFavouriteMessages(): LiveData<List<Message>>? {
        return Application.messageDatabase.messageDao().getMessageFromTypeLiveData(true)
    }

    override fun getNormalMessages(): LiveData<List<Message>>? {
        return Application.messageDatabase.messageDao().getMessageFromTypeLiveData(false)
    }

    override suspend fun saveFavouriteMessage(messageId: Int, favorite: Boolean) {
        GlobalScope.launch {
            Application.messageDatabase.messageDao().updateMessage(messageId, favorite)
        }
    }
}
