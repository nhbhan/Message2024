package com.hannhb.message.module.message.domain.usercase

import com.hannhb.message.module.message.data.repository.MessageRepository

class GetFavouriteMessagesUseCase(private val messageRepository: MessageRepository) {
    operator fun invoke() = messageRepository.getFavoriteMessage()
}
