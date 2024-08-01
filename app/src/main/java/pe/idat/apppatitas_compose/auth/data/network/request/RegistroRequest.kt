package pe.idat.apppatitas_compose.auth.data.network.request

data class RegistroRequest (
    var nombres: String,
    var apellidos: String,
    var email: String,
    var celular: String,
    var usuario: String,
    var password: String
)