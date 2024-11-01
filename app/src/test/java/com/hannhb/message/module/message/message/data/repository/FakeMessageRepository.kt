package com.hannhb.message.module.message.message.data.repository

import androidx.lifecycle.LiveData
import com.hannhb.message.module.message.domain.model.entities.Message
import vn.hannhb.message.module.message.MessageRepository
import com.hannhb.message.module.message.message.data.datasource.FakeMessageDataSource

class FakeMessageRepository(private val dataSource: FakeMessageDataSource) : MessageRepository(dataSource) {

    fun getFavouriteMessages(): LiveData<List<Message>>? {
        return dataSource.getFavouriteMessages()
    }

    fun getNormalMessagesList(): LiveData<List<Message>>? {
        return dataSource.getNormalMessages()
    }

    suspend fun saveFavouriteMessage(messageId: Int, favourite: Boolean) {
        dataSource.saveFavouriteMessage(messageId, favourite)
    }
}
