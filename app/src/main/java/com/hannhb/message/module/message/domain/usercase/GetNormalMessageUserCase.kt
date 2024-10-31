package vn.hannhb.message.module.message.domain.usercase

import com.hannhb.message.module.message.data.repository.MessageRepository

class GetNormalMessageUserCase(private val messageRepository: MessageRepository) {
    operator fun invoke() = messageRepository.getNormalMessages()
}
