package pe.idat.apppatitas_compose.home.domain

import pe.idat.apppatitas_compose.home.data.network.response.MascotaResponse
import pe.idat.apppatitas_compose.home.data.repository.MascotaRepository
import javax.inject.Inject

class MascotaUseCase @Inject constructor(
    private val repository: MascotaRepository)
{
    suspend operator fun invoke(): List<MascotaResponse>{
        return repository.listarMascota()
    }
}