package pe.idat.apppatitas_compose.auth.domain

import pe.idat.apppatitas_compose.auth.data.network.request.RegistroRequest
import pe.idat.apppatitas_compose.auth.data.network.response.RegistroResponse
import pe.idat.apppatitas_compose.auth.data.repository.AuthRepository
import javax.inject.Inject

class RegistroUseCase @Inject constructor(private val repository: AuthRepository)
{

    suspend operator fun invoke(registroRequest: RegistroRequest): RegistroResponse{
        return repository.registro(registroRequest)
    }
}