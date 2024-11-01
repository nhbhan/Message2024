package com.hannhb.message.module.message

import com.hannhb.message.module.message.data.datasource.MessageDataSource
import com.hannhb.message.module.message.data.datasource.MessageDateSourceImp
import com.hannhb.message.module.message.data.repository.MessageRepository
import com.hannhb.message.module.message.domain.usercase.GetFavouriteMessagesUseCase
import com.hannhb.message.module.message.domain.usercase.GetNormalMessageUserCase
import com.hannhb.message.module.message.domain.usercase.SaveMessageUseCase
import com.hannhb.message.module.message.ui.DetailMessageViewModel
import com.hannhb.message.module.message.ui.MessageViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val messageModule = module {
    single<MessageDataSource> { MessageDateSourceImp() }

    // repository
    single { MessageRepository(get()) }

    // user case
    single { GetFavouriteMessagesUseCase(get()) }
    single { GetNormalMessageUserCase(get()) }
    single { SaveMessageUseCase(get()) }

    // view model
    viewModel { MessageViewModel(get(), get(), get()) }
    viewModel { DetailMessageViewModel() }
}
