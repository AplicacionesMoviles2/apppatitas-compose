package pe.idat.apppatitas_compose.auth.view

import android.app.Activity
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.R
import pe.idat.apppatitas_compose.auth.viewmodel.LoginViewModel
import pe.idat.apppatitas_compose.core.ruteo.Ruta

@Composable
fun loginScreen(loginViewModel: LoginViewModel, navController: NavController){
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState)}) {
        paddingInit ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingInit)){
            cabeceraLogin(Modifier.align(Alignment.TopEnd))
            formularioLogin(Modifier.align(Alignment.Center),
                loginViewModel, snackbarHostState, navController)
            pieLogin(Modifier.align(Alignment.BottomCenter), navController)
        }
    }
}

@Composable
fun formularioLogin(modifier: Modifier, loginViewModel: LoginViewModel,
                    state: SnackbarHostState, navController: NavController){
    val usuario: String by loginViewModel.usuario.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val botonHabilitado: Boolean by loginViewModel.botonLoginHabilitado.observeAsState(initial = false)
    Column(modifier = modifier.padding(start = 5.dp, end = 5.dp)) {
        imagenLogo(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(4.dp))
        txtusuario(usuario = usuario) { loginViewModel.onLoginValueChanged(it, password) }
        Spacer(modifier = Modifier.size(4.dp))
        txtpassword(password = password) { loginViewModel.onLoginValueChanged(usuario, it) }
        Spacer(modifier = Modifier.size(4.dp))
        loginButton(botonHabilitado, loginViewModel, state, navController)
    }
    
}

@Composable
fun txtusuario(usuario: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(value = usuario,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Usuario") },
        maxLines = 1,
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "persona")},
        singleLine = true)
}
@Composable
fun txtpassword(password: String, onTextChanged: (String) -> Unit) {
    var visible by rememberSaveable {
        mutableStateOf(false)
    }
    OutlinedTextField(value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        leadingIcon = { Icon(imageVector = Icons.Default.Key, contentDescription = "persona")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icon = if(visible){
                Icons.Filled.VisibilityOff
            }else Icons.Filled.Visibility
            IconButton(onClick = { visible = !visible }) {
                Icon(imageVector = icon, contentDescription = "ver password")
            }
        },
        visualTransformation = if(visible){
            VisualTransformation.None
        } else PasswordVisualTransformation()
    )
}

@Composable
fun loginButton(botonHabilitado: Boolean, loginViewModel: LoginViewModel,
                state: SnackbarHostState, navController: NavController){
    val loginResponse by loginViewModel.loginResponse.observeAsState()
    val scope = rememberCoroutineScope()
    Button(enabled = botonHabilitado,
        onClick = { loginViewModel.login() },
        modifier = Modifier.fillMaxWidth()) {
        Text(text = "INGRESAR")
    }
    loginResponse?.getContentNotChange()?.let {
        response ->
        if(response.rpta){
            navController.navigate(Ruta.homeScreen.path)
        }else{
            scope.launch {
                state.showSnackbar("Login fallido: ${response.mensaje}",
                    actionLabel = "OK", duration = SnackbarDuration.Short)
            }
        }
    }
}

@Composable
fun imagenLogo(modifier: Modifier){
    Image(painter = painterResource(id = R.drawable.imgsplash), 
        contentDescription = "logo",
        modifier = modifier)
}

@Composable
fun cabeceraLogin(modifier: Modifier){
    val activity = LocalContext.current as Activity
    Icon(imageVector = Icons.Default.Close,
        contentDescription = "Cerrar",
        modifier = modifier.clickable { activity.finish() })
}
@Composable
fun pieLogin(modifier: Modifier, navController: NavController){
    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalDivider(
            modifier = Modifier
                .background(Color(0xFF21338D))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(20.dp))
        registroLogin(navController)
        Spacer(modifier = Modifier.size(20.dp))
    }
}
@Composable
fun registroLogin(navController: NavController){
    Row(Modifier.fillMaxWidth(), horizontalArrangement =  Arrangement.Center) {
        Text(text = "¿No tienes cuenta?  ", fontSize = 12.sp, color = Color(0xFF21338D))
        Text(text = "  Registrate Aquí", fontSize = 12.sp, color = Color(0xFF21338D),
            modifier = Modifier.clickable { navController.navigate(Ruta.registroScreen.path) },
            fontWeight = FontWeight.Bold)
    }
}

