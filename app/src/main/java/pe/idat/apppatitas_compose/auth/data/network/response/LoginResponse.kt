package pe.idat.apppatitas_compose.auth.data.network.response

data class LoginResponse(
    var rpta: Boolean,
    var idpersona: String,
    var nombres: String,
    var apellidos: String,
    var celular: String,
    var email: String,
    var usuario: String,
    var password: String,
    var esvoluntario: String,
    var mensaje: String
)