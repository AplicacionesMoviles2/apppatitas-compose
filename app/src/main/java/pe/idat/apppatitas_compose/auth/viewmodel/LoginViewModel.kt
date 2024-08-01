package pe.idat.apppatitas_compose.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.auth.data.network.request.LoginRequest
import pe.idat.apppatitas_compose.auth.data.network.response.LoginResponse
import pe.idat.apppatitas_compose.auth.domain.LoginUseCase
import pe.idat.apppatitas_compose.core.utils.Event
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _usuario = MutableLiveData<String>()
    val usuario : LiveData<String> = _usuario
    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password
    var _botonLoginHabilitado = MutableLiveData<Boolean>()
    var botonLoginHabilitado : LiveData<Boolean> = _botonLoginHabilitado
    private val _loginResponse = MutableLiveData<Event<LoginResponse>>()
    val loginResponse: LiveData<Event<LoginResponse>> = _loginResponse

    fun onLoginValueChanged(usuario:String, password: String){
        _usuario.value = usuario
        _password.value = password
        _botonLoginHabilitado.value = habilitarBoton(usuario, password)
    }
    fun habilitarBoton (usuario:String, password: String)
                = usuario.length > 2 && password.length > 2

    fun login(){
        viewModelScope.launch {
            val response = loginUseCase(LoginRequest(usuario.value!!, password.value!!))
            _loginResponse.value = Event(response)
        }
    }
}