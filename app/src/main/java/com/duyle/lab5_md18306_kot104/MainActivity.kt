package com.duyle.lab5_md18306_kot104

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.duyle.lab5_md18306_kot104.ui.theme.Lab5MD18306KOT104Theme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5MD18306KOT104Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {  innerPadding ->
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}

@Composable
fun LoginApp(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = CardDefaults.cardColors(containerColor =
            Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation =
            14.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            LoginScreen(navController)
        }
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    var isCheckedRememberAccount by remember {
        mutableStateOf(false)
    }

    if (showDialog) {
        val tatDialog = {
            showDialog = false
        }
        DialogComponent(
            onConfirmation = tatDialog,
            dialogTitle = "Notification",
            dialogMessage = dialogMessage
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter =
            painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Logo"
        )
        Spacer(modifier = Modifier.height(20.dp))
// Username TextField
        OutlinedTextField(
            value = username,
            onValueChange = { username = it }, label = { Text("Username") },
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))

        RememberMeSwitch(isCheckedRememberAccount){
            isCheckedRememberAccount = ! isCheckedRememberAccount
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                if (username.isNotBlank() && password.isNotBlank()) {

                    if (isCheckedRememberAccount) {
                        Toast.makeText(
                            context, "Da luu thong tin dang nhap!",
                            Toast.LENGTH_LONG
                        ).show()

                        // luu username - pass vao csdl
                    }

                    navController.navigate(ROUTE_SCREEN_NAME.bai3.name)

                    dialogMessage = "Login successful! $username"


                } else {
//                    Toast.makeText(
//                        context,
//                        "Please enter username and password",
//                        Toast.LENGTH_LONG
//                    ).show()

                    dialogMessage = "Please enter username and password"
                }

                showDialog = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            Text("Login")
        }
    }
}

@Composable
fun RememberMeSwitch(isCheckedRememberAccount: Boolean, toggleSaveData: (Boolean) -> Unit) {
    //var isChecked by remember { mutableStateOf(false) }
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Switch(
            checked = isCheckedRememberAccount,
            onCheckedChange =  toggleSaveData
        )
        Text("Remember Me?", modifier = Modifier.padding(start =
        12.dp))
    }
}

@Composable
fun DialogComponent(
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogMessage: String,
) {
    Dialog(onDismissRequest = {}) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier.padding(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(dialogTitle, style =
                MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(20.dp))
                Text(dialogMessage, style =
                MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onConfirmation,

                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text("Okay")
                }
            }
        }
    }
}