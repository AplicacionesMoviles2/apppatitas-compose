package pe.idat.apppatitas_compose.home.view

import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.home.viewmodel.HomeViewModel

@Composable
fun voluntarioScreen(homeViewModel: HomeViewModel) {
    val persona by homeViewModel.persona.observeAsState()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            persona?.let {
                value ->
                if(value.esvoluntario == "1"){
                    cabeceraVoluntario("Gracias por ser parte de nuestro equipo")
                }else{
                    cabeceraVoluntario("¡UNETE A NOSOTROS!")
                    Spacer(modifier = Modifier.height(10.dp))
                    formularioVoluntario(homeViewModel, snackbarHostState)
                }
            }


        }
    }
}

@Composable
fun cabeceraVoluntario(texto : String) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Filled.Pets, contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
        Text(text = texto,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
fun formularioVoluntario(homeViewModel: HomeViewModel,
                         state: SnackbarHostState){
    var isChecked by remember {
        mutableStateOf(false)
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        lblTerminosCondiciones()
        cbTerminosCondiciones(isChecked) {isChecked = it}
        btnRegistrarVoluntario(isChecked, homeViewModel, state)
    }
}

@Composable
fun lblTerminosCondiciones(){
    Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        fontSize = 12.sp, textAlign = TextAlign.Justify)
}

@Composable
fun cbTerminosCondiciones(isChecked: Boolean, onCheckedChange: (Boolean)-> Unit){
    Row {
        Checkbox(checked = isChecked, onCheckedChange = { onCheckedChange(it) })
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "Aceptar términos y condiciones", Modifier.padding(top = 12.dp),
            fontSize = 12.sp)
    }
}

@Composable
fun btnRegistrarVoluntario(isEnabled: Boolean,
                           homeViewModel: HomeViewModel,
                           state: SnackbarHostState){
    val voluntarioResponse by homeViewModel.voluntarioResponse.observeAsState()
    val scope = rememberCoroutineScope()
    Button(onClick = {
        homeViewModel.registrarVoluntario()
    },
        Modifier.fillMaxWidth(),
        enabled = isEnabled) {
        Text(text = "Registrar Voluntario")
    }
    voluntarioResponse?.let {
        value ->
        scope.launch {
            state.showSnackbar(
                value.mensaje,
                actionLabel = "OK",
                duration = SnackbarDuration.Short
            )
        }

    }
}
