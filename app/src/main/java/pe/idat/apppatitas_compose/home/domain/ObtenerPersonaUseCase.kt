package pe.idat.apppatitas_compose.home.domain

import androidx.lifecycle.LiveData
import pe.idat.apppatitas_compose.core.bd.PersonaEntity
import pe.idat.apppatitas_compose.home.data.repository.PersonaHomeRepository
import javax.inject.Inject

class ObtenerPersonaUseCase @Inject constructor(
    private val repository: PersonaHomeRepository
) {
    operator fun invoke(): LiveData<PersonaEntity>{
        return repository.obtenerPersona()
    }
}