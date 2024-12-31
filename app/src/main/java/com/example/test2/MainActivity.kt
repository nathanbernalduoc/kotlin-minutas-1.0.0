package com.example.test2

import android.content.Context
import android.content.Intent
import android.content.res.Resources.Theme
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test2.core.navigation.NavigationWrapper
import com.example.test2.ui.theme.Test2Theme
import kotlin.contracts.contract


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            MaterialTheme {
                NavigationWrapper()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("---> ObStart()")
    }

    override fun onResume() {
        super.onResume()
        println("---> OnResume()")
    }

    override fun onStop() {
        super.onStop()
        println("----> On STOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("----> ON DESTROY")
    }

    override fun onRestart() {
        super.onRestart()
        println("----> onRestart")
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GreetingPreview() {
    Test2Theme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun LoginForm() {

    val usuario = rememberSaveable { mutableStateOf("")}
    val password = rememberSaveable { mutableStateOf("")}
    val validacionMensaje = rememberSaveable { mutableStateOf("")}

    Column (
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Text(
            "Minutas",
            style = TextStyle(
                fontSize = 40.sp,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Top,
                    trim = LineHeightStyle.Trim.Both
                )
            )
        )

        val logo = painterResource(R.drawable.nathanbernal)

        Image(
            painter = logo,
            contentDescription = "Nathan Logo"
            )

        OutlinedTextField(
            value = usuario.value,
            onValueChange = { usuario.value = it },
            label = { Text("Usuario") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true
        )

        Text(
            text = validacionMensaje.value,
            modifier = Modifier.padding(10.dp),
            style = TextStyle(
                fontSize = 15.sp,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Top,
                    trim = LineHeightStyle.Trim.Both
                )
            ),
            color = Color.Red
        )

        Button(
            onClick = {
                System.out.println("Usuario "+usuario.value)
                if (usuario.value.equals("") || password.value.equals("")) {
                    validacionMensaje.value = "El nombre de usuario y la contraseña son obligatorios."
                } else if (ValidaUsuario( usuario.value, password.value ) == true) {
                    System.out.println("Validación OK");
                    validacionMensaje.value = "Accediendo..."
                    //val intent = Intent(this@MainActivity, com.example.test2.MainMenuActivity::class.java)
                    //startActivity(intent)

                } else {
                    System.out.println("Credenciales incorrectas")
                    usuario.value = ""
                    validacionMensaje.value = "Las credenciales proporcionadas son incorrectas."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }

    }

}

fun ValidaUsuario(usuario:String, password:String):Boolean {
    if (usuario.equals("nathan") && password.equals("321")) {
        return true;
    }
    return false
}
