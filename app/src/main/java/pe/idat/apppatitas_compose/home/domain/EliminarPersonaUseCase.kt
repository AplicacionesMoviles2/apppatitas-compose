package pe.idat.apppatitas_compose.home.domain

import pe.idat.apppatitas_compose.home.data.repository.PersonaHomeRepository
import javax.inject.Inject

class EliminarPersonaUseCase @Inject constructor(
    private val repository: PersonaHomeRepository
)  {

    suspend operator fun invoke(){
        return repository.eliminarPersona()
    }
}