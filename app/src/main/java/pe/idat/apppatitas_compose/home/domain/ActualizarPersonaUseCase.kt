package pe.idat.apppatitas_compose.home.domain

import pe.idat.apppatitas_compose.core.bd.PersonaEntity
import pe.idat.apppatitas_compose.home.data.repository.PersonaHomeRepository
import javax.inject.Inject

class ActualizarPersonaUseCase @Inject constructor(
    private val repository: PersonaHomeRepository
)  {
    suspend operator fun invoke(personaEntity: PersonaEntity){
        return repository.actualizarPersona(personaEntity)
    }
}