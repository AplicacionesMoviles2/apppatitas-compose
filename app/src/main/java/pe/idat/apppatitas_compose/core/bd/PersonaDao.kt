package pe.idat.apppatitas_compose.core.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonaDao {

    @Query("SELECT * FROM persona LIMIT 1")
    fun obtenerPersona(): Flow<PersonaEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarPersona(vararg persona: PersonaEntity)

    @Update
    suspend fun actualizarPersona(vararg persona: PersonaEntity)

    @Query("DELETE FROM persona")
    suspend fun eliminarPersona()

}