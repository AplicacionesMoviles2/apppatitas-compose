package pe.idat.apppatitas_compose.core.bd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persona")
data class PersonaEntity(
    @PrimaryKey
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val email: String,
    val celular: String,
    val usuario: String,
    val password: String,
    val esvoluntario: String
)

