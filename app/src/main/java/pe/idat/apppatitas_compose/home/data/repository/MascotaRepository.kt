package pe.idat.apppatitas_compose.home.data.repository

import pe.idat.apppatitas_compose.home.data.network.request.VoluntarioRequest
import pe.idat.apppatitas_compose.home.data.network.response.MascotaResponse
import pe.idat.apppatitas_compose.home.data.network.response.VoluntarioResponse
import pe.idat.apppatitas_compose.home.data.network.service.MascotaService
import javax.inject.Inject

class MascotaRepository @Inject constructor(
    private val mascotaService: MascotaService
) {

    suspend fun  listarMascota(): List<MascotaResponse>{
        return mascotaService.listarMascota()
    }

    suspend fun registrarVoluntario(voluntarioRequest: VoluntarioRequest): VoluntarioResponse{
        return mascotaService.registrarVoluntario(voluntarioRequest)
    }
}