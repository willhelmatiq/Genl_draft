package com.harbourspace.genl_draft.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.harbourspace.genl_draft.AppApplication
import com.harbourspace.genl_draft.EXTRA_TEXT
import com.harbourspace.genl_draft.R
import com.harbourspace.genl_draft.UnsplashViewModel
import com.harbourspace.genl_draft.ui.data.UnsplashPhoto
import com.harbourspace.genl_draft.ui.theme.Genl_draftTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Genl_draftTheme {
                // A surface container using the 'background' color from the theme
                val search = remember { mutableStateOf("") }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(stringResource(id = R.string.menu_chat))
                            },
                            actions = {
                                IconButton(onClick = {
                                    Firebase.auth.signOut()
                                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                                    finish()
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_logout),
                                        contentDescription = "stringResource(id = R.string.description_exercises)"
                                    )
                                }
                            })
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {openShowPhotoActivity(search.value)}) {
                            Text("G")
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                            .background(Color.White),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.5f),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "Input searching parameter",
                                color = Color.Black
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(1.0f),
                            verticalAlignment = Alignment.Bottom
                        ) {

                            TextField(
                                value = search.value,
                                onValueChange = { value ->
                                    search.value = value
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                                placeholder = {
                                    Text(
                                        text = "",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        color = Color.Magenta,
                                    )
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Search,
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = Color.Black,
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent
                                )
                            )
                        }


                    }


                }
            }
        }
    }

    private fun onSignOut() {
        Firebase.auth.signOut()
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }

    private fun openShowPhotoActivity(text: String) {
        val intent = Intent(this, ShowPhotoActivity::class.java)
        intent.putExtra(EXTRA_TEXT, text)
        startActivity(intent)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        color = Color.Red
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Genl_draftTheme {
        Greeting("Android")
    }
}