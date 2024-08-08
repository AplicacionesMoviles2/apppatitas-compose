package pe.idat.apppatitas_compose.core.bd

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PatitasDbModule {

    @Provides
    fun providePersonaDao(patitasDatabase: PatitasDatabase): PersonaDao{
        return patitasDatabase.personaDao()
    }

    @Provides
    @Singleton
    fun providePatitasDatabase(@ApplicationContext context: Context): PatitasDatabase{
        return Room.databaseBuilder(context,
            PatitasDatabase::class.java,
            "patitasdb").build()

    }
}