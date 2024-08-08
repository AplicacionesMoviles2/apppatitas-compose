package pe.idat.apppatitas_compose.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import pe.idat.apppatitas_compose.R
import pe.idat.apppatitas_compose.home.data.network.response.MascotaResponse
import pe.idat.apppatitas_compose.home.viewmodel.HomeViewModel

@Composable
fun mascotaScreen(homeViewModel: HomeViewModel) {
    val mascotas by homeViewModel.mascotaResponse.observeAsState(emptyList())
    LazyColumn {
        items(mascotas){
            mascota -> mascotaItem(mascota = mascota)
        }
    }

}

@Composable
fun mascotaItem(mascota: MascotaResponse) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ))
    {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment =  Alignment.CenterVertically
        ) {
            Image(painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = mascota.urlimagen)
                    .apply(block = fun ImageRequest.Builder.(){
                        crossfade(true)
                        placeholder(R.drawable.imgperfil)
                    }).build()
            ), contentDescription = null,
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = mascota.nommascota, fontWeight = FontWeight.Bold)
                Text(text = mascota.lugar, color = Color.Gray)
                Text(text = mascota.fechaperdida, color = Color.Gray)
                Text(text = mascota.contacto, fontWeight = FontWeight.Bold)
            }
        }
    }
}