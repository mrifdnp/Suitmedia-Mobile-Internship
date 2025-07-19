package com.example.ispalindrome.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ispalindrome.R
import java.util.*
import androidx.compose.ui.graphics.Color
@Composable
fun FirstScreen(
    name: String,
    sentence: String,
    onNameChange: (String) -> Unit,
    onSentenceChange: (String) -> Unit,
    onCheckClick: () -> Unit,
    onNextClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var isPalindrome by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background), // background image
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(34.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_photo),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(164.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(58.dp))

            TextField(
                value = name,
                onValueChange = onNameChange,
                placeholder = {
                    Text(
                        text = "Name",
                        color = Color(0x5C686777)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = sentence,
                onValueChange = onSentenceChange,
                placeholder = {
                    Text(
                        text = "Palindrome",
                        color = Color(0x5C686777)  // 36% opacity
                    )
                }
                ,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )


            Spacer(modifier = Modifier.height(58.dp))

            Button(
                onClick = {
                    isPalindrome = isPalindrome(sentence)
                    showDialog = true
                    onCheckClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(41.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B637B)
                )
            ) {
                Text("CHECK", color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(41.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B637B)
                )
            ) {
                Text("NEXT", color = Color.White)
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                },
                text = {
                    Text(if (isPalindrome) "isPalindrome" else "not palindrome")
                }
            )
        }
    }
}
fun isPalindrome(input: String): Boolean {
    val cleaned = input.lowercase(Locale.ROOT).filter { it.isLetterOrDigit() }
    return cleaned == cleaned.reversed()
}

