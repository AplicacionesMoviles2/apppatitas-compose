package pe.idat.apppatitas_compose.auth.data.network.retrofitclient

import pe.idat.apppatitas_compose.auth.data.network.request.LoginRequest
import pe.idat.apppatitas_compose.auth.data.network.request.RegistroRequest
import pe.idat.apppatitas_compose.auth.data.network.response.LoginResponse
import pe.idat.apppatitas_compose.auth.data.network.response.RegistroResponse
import pe.idat.apppatitas_compose.home.data.network.request.VoluntarioRequest
import pe.idat.apppatitas_compose.home.data.network.response.MascotaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface PatitasClient {

    @POST("login.php")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @PUT("persona.php")
    suspend fun registro(@Body registroRequest: RegistroRequest): Response<RegistroResponse>

    @GET("mascotaperdida.php")
    suspend fun listarMascota(): Response<List<MascotaResponse>>

    @PUT("personavoluntario.php")
    suspend fun registrarVoluntario(@Body voluntarioRequest: VoluntarioRequest):
            Response<RegistroResponse>
}