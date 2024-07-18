package pe.idat.apppatitas_compose.auth.view

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.idat.apppatitas_compose.auth.viewmodel.LoginViewModel

@Composable
fun loginScreen(loginViewModel: LoginViewModel){
    Scaffold {
        paddingInit ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingInit)){
            cabeceraLogin(Modifier.align(Alignment.TopEnd))

            pieLogin(Modifier.align(Alignment.BottomCenter))
        }
    }
}
@Composable
fun cabeceraLogin(modifier: Modifier){
    val activity = LocalContext.current as Activity
    Icon(imageVector = Icons.Default.Close,
        contentDescription = "Cerrar",
        modifier = modifier.clickable { activity.finish() })
}
@Composable
fun pieLogin(modifier: Modifier){
    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalDivider(
            modifier = Modifier
                .background(Color(0xFF21338D))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(20.dp))
        registroLogin()
        Spacer(modifier = Modifier.size(20.dp))
    }
}
@Composable
fun registroLogin(){
    Row(Modifier.fillMaxSize(), horizontalArrangement =  Arrangement.Center) {
        Text(text = "¿No tienes cuenta?  ", fontSize = 12.sp, color = Color(0xFF21338D))
        Text(text = "  Registrate Aquí", fontSize = 12.sp, color = Color(0xFF21338D),
            modifier = Modifier.clickable {  },
            fontWeight = FontWeight.Bold)
    }
}