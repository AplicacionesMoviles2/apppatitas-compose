package pe.idat.apppatitas_compose.core.bd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PersonaEntity::class], version = 1)
abstract class PatitasDatabase : RoomDatabase() {

    abstract fun personaDao(): PersonaDao

}