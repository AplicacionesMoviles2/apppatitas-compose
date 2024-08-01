package pe.idat.apppatitas_compose.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.auth.data.network.request.RegistroRequest
import pe.idat.apppatitas_compose.auth.data.network.response.RegistroResponse
import pe.idat.apppatitas_compose.auth.domain.LoginUseCase
import pe.idat.apppatitas_compose.auth.domain.RegistroUseCase
import pe.idat.apppatitas_compose.core.utils.Event
import javax.inject.Inject

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val registroUseCase: RegistroUseCase) : ViewModel() {

    private val _nombres = MutableLiveData<String>()
    val nombres: LiveData<String> = _nombres
    private val _apellidos = MutableLiveData<String>()
    val apellidos: LiveData<String> = _apellidos
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _celular = MutableLiveData<String>()
    val celular: LiveData<String> = _celular
    private val _usuario = MutableLiveData<String>()
    val usuario: LiveData<String> = _usuario
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _registroResponse = MutableLiveData<Event<RegistroResponse>>()
    val registroResponse: LiveData<Event<RegistroResponse>> = _registroResponse

    fun onRegistroChanged(nombres: String, apellidos: String, email: String,
                          celular: String, usuario: String, password: String){
        _nombres.value = nombres
        _apellidos.value = apellidos
        _email.value = email
        _celular.value = celular
        _usuario.value = usuario
        _password.value = password
    }
    fun setearRegistro(){
        _nombres.value = ""
        _apellidos.value = ""
        _email.value = ""
        _celular.value = ""
        _usuario.value = ""
        _password.value = ""
    }
    fun registrarPersona(){
        viewModelScope.launch {
            val response = registroUseCase(
                RegistroRequest(
                    nombres.value!!,
                    apellidos.value!!,
                    email.value!!,
                    celular.value!!,
                    usuario.value!!,
                    password.value!!)
            )
            _registroResponse.value = Event(response)
        }
    }

}