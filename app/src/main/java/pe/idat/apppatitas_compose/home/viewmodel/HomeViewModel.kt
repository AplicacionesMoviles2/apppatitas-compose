package pe.idat.apppatitas_compose.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.core.bd.PersonaEntity
import pe.idat.apppatitas_compose.home.data.network.request.VoluntarioRequest
import pe.idat.apppatitas_compose.home.data.network.response.MascotaResponse
import pe.idat.apppatitas_compose.home.data.network.response.VoluntarioResponse
import pe.idat.apppatitas_compose.home.domain.ActualizarPersonaUseCase
import pe.idat.apppatitas_compose.home.domain.EliminarPersonaUseCase
import pe.idat.apppatitas_compose.home.domain.MascotaUseCase
import pe.idat.apppatitas_compose.home.domain.ObtenerPersonaUseCase
import pe.idat.apppatitas_compose.home.domain.VoluntarioUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mascotaUseCase: MascotaUseCase,
    private val voluntarioUseCase: VoluntarioUseCase,
    private val obtenerPersonaUseCase: ObtenerPersonaUseCase,
    private val eliminarPersonaUseCase: EliminarPersonaUseCase,
    private val actualizarPersonaUseCase: ActualizarPersonaUseCase
) : ViewModel() {

    private val _mascotaResponse = MutableLiveData<List<MascotaResponse>>()
    val mascotaResponse: LiveData<List<MascotaResponse>> = _mascotaResponse
    private val _idpersona = MutableLiveData<Int>()
    val idpersona : LiveData<Int> = _idpersona
    private val _voluntarioResponse = MutableLiveData<VoluntarioResponse>()
    val voluntarioResponse : LiveData<VoluntarioResponse> = _voluntarioResponse
    val persona : LiveData<PersonaEntity> = obtenerPersonaUseCase()


    init {
        listarMascotas()
    }

    fun listarMascotas(){
        viewModelScope.launch {
            val response = mascotaUseCase()
            _mascotaResponse.value = response
        }
    }

    fun eliminarPersona(){
        viewModelScope.launch {
            eliminarPersonaUseCase()
        }
    }

    fun registrarVoluntario(){
        viewModelScope.launch {
            val response = voluntarioUseCase(
                VoluntarioRequest(idpersona.value!!)
            )
            _voluntarioResponse.value = response
            if(response.rpta){
                actualizarPersonaUseCase(
                    PersonaEntity(persona.value!!.id,
                        persona.value!!.nombres,
                        persona.value!!.apellidos,
                        persona.value!!.email,
                        persona.value!!.celular,
                        persona.value!!.usuario,
                        persona.value!!.password,
                        "1")
                )
            }
        }
    }

}