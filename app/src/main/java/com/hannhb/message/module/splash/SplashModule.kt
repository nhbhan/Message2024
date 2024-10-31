package vn.hannhb.message.module.splash

import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.dsl.module
import com.hannhb.message.module.splash.ui.SplashViewModel
import org.koin.core.module.dsl.viewModel

val splashModule = module {
    // view model
    viewModel { SplashViewModel() }
}
