package pe.idat.apppatitas_compose.home.view

import androidx.compose.foundation.Image
import pe.idat.apppatitas_compose.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pe.idat.apppatitas_compose.core.utils.MenuItem

@Composable
fun homeScreen() {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Text(text = "Bienvenido", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun drawerMenuItem(item: MenuItem, onItemClick: (MenuItem) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(item) }
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = item.icon, contentDescription = "")
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item.titulo)
    }
}

@Composable
fun cabeceraMenu() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.imgperfil),
            contentDescription = "logo",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "Luis Salvat", fontWeight = FontWeight.Bold)
            Text(text = "lsalvat@gmail.com", color = Color.Gray)
        }
    }
}

fun opcionesMenu(): List<MenuItem> {
    return listOf(
        MenuItem(Icons.Default.Checklist, "Mascotas Perdidas"),
        MenuItem(Icons.Default.Pets, "Voluntario")
    )
}