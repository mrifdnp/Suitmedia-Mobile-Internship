package com.example.ispalindrome

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.ispalindrome.data.services.response.User
import com.example.ispalindrome.ui.screen.FirstScreen
import com.example.ispalindrome.ui.screen.SecondScreen
import com.example.ispalindrome.ui.screen.ThirdScreen
import com.example.ispalindrome.ui.theme.IsPalindromeTheme
import androidx.navigation.compose.composable

import androidx.navigation.navArgument
import com.example.ispalindrome.viewmodel.SharedViewModel


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IsPalindromeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedViewModel: SharedViewModel = viewModel()

    var name by remember { mutableStateOf("") }
    var sentence by remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "first") {
        composable("first") {
            FirstScreen(
                name = name,
                sentence = sentence,
                onNameChange = { name = it },
                onSentenceChange = { sentence = it },
                onCheckClick = {
                    val clean = sentence.replace(" ", "").lowercase()
                    val isPalindrome = clean == clean.reversed()
                    Toast.makeText(
                        context,
                        if (isPalindrome) "isPalindrome" else "not palindrome",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onNextClick = {
                    if (name.isBlank()) {
                        Toast.makeText(context, "Please enter your name first", Toast.LENGTH_SHORT).show()
                    } else {
                        navController.navigate("second_screen/$name")
                    }
                }
            )
        }

        composable(
            route = "second_screen/{userName}",
            arguments = listOf(navArgument("userName") { defaultValue = "" })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""

            SecondScreen(
                userName = userName,
                sharedViewModel = sharedViewModel,
                onChooseUserClick = { navController.navigate("third") },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("third") {
            ThirdScreen(
                onUserClick = { user ->
                    sharedViewModel.setSelectedUserName("${user.firstName} ${user.lastName}")
                    navController.popBackStack()
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

