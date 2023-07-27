package com.harbourspace.genl_draft.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.harbourspace.genl_draft.R
import com.harbourspace.genl_draft.ui.theme.Genl_draftTheme
import com.harbourspace.unsplash.repository.AppPreferences


private const val TAG = "LoginActivity"

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Firebase.auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContent {

            val username = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }

            Genl_draftTheme(
                darkTheme = AppPreferences(this).isDarkTheme(),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AddCustomTextField(
                        text = username,
                        hint = R.string.login_username_hint,
                        visualTransformation = VisualTransformation.None,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    AddCustomTextField(
                        text = password,
                        hint = R.string.login_password_hint,
                        visualTransformation = PasswordVisualTransformation(),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {

                        Button(
                            onClick = {
                                authenticateUser(username.value, password.value)
                            }) {
                            Text(
                                text = stringResource(id = R.string.login_sign_in)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = {
                                registerUser(username.value, password.value)
                            },
                            enabled = username.value.isNotEmpty() && password.value.isNotEmpty()
                        ) {
                            Text(
                                text = stringResource(id = R.string.login_register)
                            )
                        }
                    }
                }
             }
        }
    }

    private fun authenticateUser(username: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(
            username,
            password
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Log.d(TAG, "Authentication failed. Error: ${it.exception}")
                Toast.makeText(baseContext, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(username: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(
            username,
            password
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Log.d(TAG, "Authentication failed. Error: ${it.exception}")
                Toast.makeText(baseContext, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun AddCustomTextField(
    text: MutableState<String>,
    @StringRes hint: Int,
    visualTransformation: VisualTransformation,
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = { value ->
            text.value = value
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(id = hint),
                maxLines = 1
            )
        },
        visualTransformation = visualTransformation
    )
}