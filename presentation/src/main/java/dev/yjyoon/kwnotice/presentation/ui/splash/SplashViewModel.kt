package dev.yjyoon.kwnotice.presentation.ui.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yjyoon.kwnotice.domain.model.FcmTopic
import dev.yjyoon.kwnotice.domain.usecase.CheckFirstLaunchUseCase
import dev.yjyoon.kwnotice.domain.usecase.SubscribeFcmTopicUseCase
import dev.yjyoon.kwnotice.presentation.fcm.FcmSubscription
import dev.yjyoon.kwnotice.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkFirstLaunchUseCase: CheckFirstLaunchUseCase,
    private val subscribeFcmTopicUseCase: SubscribeFcmTopicUseCase,
    private val fcmSubscription: FcmSubscription
) : BaseViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Waiting)
    val state: StateFlow<SplashState> = _state.asStateFlow()

    init {
        checkFirstLaunch()
    }

    private fun checkFirstLaunch() {
        launch {
            val isFirst = checkFirstLaunchUseCase().getOrThrow()

            if (isFirst) {
                FcmTopic.values().forEach { topic ->
                    subscribeFcmTopicUseCase(topic)
                        .onSuccess { fcmSubscription.subscribeTo(topic) }
                }
            }

            _state.value = SplashState.Done
        }
    }
}
