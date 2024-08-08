package pe.idat.apppatitas_compose.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.home.data.network.request.VoluntarioRequest
import pe.idat.apppatitas_compose.home.data.network.response.MascotaResponse
import pe.idat.apppatitas_compose.home.data.network.response.VoluntarioResponse
import pe.idat.apppatitas_compose.home.domain.MascotaUseCase
import pe.idat.apppatitas_compose.home.domain.VoluntarioUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mascotaUseCase: MascotaUseCase,
    private val voluntarioUseCase: VoluntarioUseCase
) : ViewModel() {

    private val _mascotaResponse = MutableLiveData<List<MascotaResponse>>()
    val mascotaResponse: LiveData<List<MascotaResponse>> = _mascotaResponse
    private val _idpersona = MutableLiveData<Int>()
    val idpersona : LiveData<Int> = _idpersona
    private val _voluntarioResponse = MutableLiveData<VoluntarioResponse>()
    val voluntarioResponse : LiveData<VoluntarioResponse> = _voluntarioResponse

    init {
        listarMascotas()
    }

    fun listarMascotas(){
        viewModelScope.launch {
            val response = mascotaUseCase()
            _mascotaResponse.value = response
        }
    }

    fun registrarVoluntario(){
        viewModelScope.launch {
            val response = voluntarioUseCase(
                VoluntarioRequest(idpersona.value!!)
            )
            _voluntarioResponse.value = response
        }
    }

}