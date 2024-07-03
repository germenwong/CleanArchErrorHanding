package com.hgm.cleanarcherrorhandling.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgm.cleanarcherrorhandling.domain.AuthRepository
import com.hgm.cleanarcherrorhandling.domain.DataValidator
import com.hgm.cleanarcherrorhandling.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
      private val dataValidator: DataValidator,
      private val authRepository: AuthRepository
) : ViewModel() {

      private val eventChannel = Channel<UserEvent>()
      val events = eventChannel.receiveAsFlow()

      fun onRegister(
            email: String,
            username: String,
            password: String
      ) {
            viewModelScope.launch {
                  // 本地校验
                  when (val result = dataValidator.validatePassword(password)) {
                        is Result.Error -> {
                              eventChannel.send(UserEvent.Error(result.message.asUiText()))
                        }

                        is Result.Success -> {

                        }
                  }

                  // 服务器校验
                  when (val result = authRepository.register(email, username, password)) {
                        is Result.Error -> {
                              val errorMessage = result.asErrorUiText()
                              eventChannel.send(UserEvent.Error(errorMessage))
                        }

                        is Result.Success -> {
                              eventChannel.send(UserEvent.Navigate("user"))
                        }
                  }
            }
      }
}

sealed interface UserEvent {
      data class Error(val error: UiText) : UserEvent
      data class Navigate(val route:String):UserEvent
}