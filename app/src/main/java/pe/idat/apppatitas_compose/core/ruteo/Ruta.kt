package pe.idat.apppatitas_compose.core.ruteo

sealed class Ruta(val path: String) {
    object loginScreen : Ruta("loginScreen")
    object registroScreen : Ruta("registroScreen")
    object homeScreen : Ruta("homeScreen")
    object mascotaScreen : Ruta("mascotaScreen")
    object voluntarioScreen : Ruta("voluntarioScreen")
}