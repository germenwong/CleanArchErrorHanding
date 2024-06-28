package com.hgm.cleanarcherrorhandling

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgm.cleanarcherrorhandling.presentation.AuthViewModel
import com.hgm.cleanarcherrorhandling.presentation.UserEvent
import com.hgm.cleanarcherrorhandling.presentation.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
      @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                  MyApplicationTheme {
                        Scaffold {
                              val navController= rememberNavController()
                              NavHost(
                                    navController = navController,
                                    startDestination = "register"
                              ) {
                                    composable("register"){
                                          Box(
                                                modifier = Modifier.fillMaxSize(),
                                                contentAlignment = Alignment.Center
                                          ) {
                                                val viewModel: AuthViewModel = hiltViewModel()
                                                val context = LocalContext.current
                                                LaunchedEffect(key1 = true) {
                                                      viewModel.events.collectLatest { event ->
                                                            when (event) {
                                                                  is UserEvent.Error -> {
                                                                        Toast.makeText(
                                                                              context,
                                                                              event.error.asString(context),
                                                                              Toast.LENGTH_SHORT
                                                                        )
                                                                              .show()
                                                                  }

                                                                  is UserEvent.Navigate -> navController.navigate("user")
                                                            }
                                                      }
                                                }


                                                Button(onClick = {
                                                      viewModel.onRegister(
                                                            email = "123",
                                                            username = "hgm",
                                                            password = "Hgm111666888"
                                                      )
                                                }) {
                                                      Text(text = "Register")
                                                }
                                          }
                                    }

                                    composable("user"){
                                          Box(
                                              modifier = Modifier.fillMaxSize(),
                                              contentAlignment = Alignment.Center
                                          ) {
                                              Text(text = "Register Successfully")
                                          }
                                    }
                              }
                        }
                  }
            }
      }
}
