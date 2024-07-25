package pe.idat.apppatitas_compose.auth.data.network.request

data class LoginRequest(
    var usuario: String,
    var password: String)