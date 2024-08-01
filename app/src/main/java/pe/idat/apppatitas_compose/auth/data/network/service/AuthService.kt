package pe.idat.apppatitas_compose.auth.data.network.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.idat.apppatitas_compose.auth.data.network.request.LoginRequest
import pe.idat.apppatitas_compose.auth.data.network.request.RegistroRequest
import pe.idat.apppatitas_compose.auth.data.network.response.LoginResponse
import pe.idat.apppatitas_compose.auth.data.network.response.RegistroResponse
import pe.idat.apppatitas_compose.auth.data.network.retrofitclient.PatitasClient
import javax.inject.Inject


class AuthService @Inject constructor(
    private val patitasClient: PatitasClient) {

    suspend fun login(loginRequest: LoginRequest): LoginResponse{
        return withContext(Dispatchers.IO){
            val response = patitasClient.login(loginRequest)
            response.body()!!
        }
    }

    suspend fun registro(registroRequest: RegistroRequest): RegistroResponse{
        return withContext(Dispatchers.IO){
            val response = patitasClient.registro(registroRequest)
            response.body()!!
        }
    }

}