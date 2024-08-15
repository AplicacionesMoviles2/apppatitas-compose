package pe.idat.apppatitas_compose.home.data.repository

import androidx.lifecycle.LiveData
import pe.idat.apppatitas_compose.core.bd.PersonaDao
import pe.idat.apppatitas_compose.core.bd.PersonaEntity
import javax.inject.Inject

class PersonaHomeRepository @Inject constructor(
    private val personaDao: PersonaDao
)  {
    suspend fun actualizarPersona(personaEntity: PersonaEntity){
        personaDao.actualizarPersona(personaEntity)
    }
    suspend fun eliminarPersona(){
        personaDao.eliminarPersona()
    }

    fun obtenerPersona(): LiveData<PersonaEntity>{
        return personaDao.obtenerPersona()
    }
}