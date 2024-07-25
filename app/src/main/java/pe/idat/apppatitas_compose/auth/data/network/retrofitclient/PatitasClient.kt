package pe.idat.apppatitas_compose.auth.data.network.retrofitclient

import pe.idat.apppatitas_compose.auth.data.network.request.LoginRequest
import pe.idat.apppatitas_compose.auth.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PatitasClient {

    @POST("login.php")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}