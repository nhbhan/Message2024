package com.hannhb.message.module.message.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.hannhb.message.core.ui.BaseViewModel
import com.hannhb.message.module.message.domain.model.entities.Message
import com.hannhb.message.module.message.domain.usercase.GetFavouriteMessagesUseCase
import com.hannhb.message.module.message.domain.usercase.GetNormalMessageUserCase
import com.hannhb.message.module.message.domain.usercase.SaveMessageUseCase

class MessageViewModel(
    private val getFavouriteMessagesUseCase: GetFavouriteMessagesUseCase,
    private val getNormalMessageUserCase: GetNormalMessageUserCase,
    private val saveMessageUseCase: SaveMessageUseCase
) : BaseViewModel() {

    val favouriteMessages: LiveData<List<Message>>? = getFavouriteMessagesUseCase.invoke()
    val normalMessage: LiveData<List<Message>>? = getNormalMessageUserCase.invoke()

    fun saveMessage(messageId: Int, favourite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveMessageUseCase.invoke(messageId, favourite)
        }
    }
}
