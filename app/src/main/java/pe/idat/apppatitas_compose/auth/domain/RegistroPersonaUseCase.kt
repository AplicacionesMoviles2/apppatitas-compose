package pe.idat.apppatitas_compose.auth.domain

import pe.idat.apppatitas_compose.auth.data.repository.PersonaAuthRepository
import pe.idat.apppatitas_compose.core.bd.PersonaEntity
import javax.inject.Inject

class RegistroPersonaUseCase @Inject constructor(
    private val repository: PersonaAuthRepository
) {
    suspend operator fun invoke(personaEntity: PersonaEntity){
        return repository.insertarPersona(personaEntity)
    }
}