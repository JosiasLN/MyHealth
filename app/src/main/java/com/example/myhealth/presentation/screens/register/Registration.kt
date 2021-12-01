package com.example.myhealth.presentation.screens.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.myhealth.presentation.components.EventDialog
import com.example.myhealth.presentation.components.RoundedButton
import com.example.myhealth.presentation.components.TransparentTextField
import java.util.*


@Composable
fun Registration(
    state: RegisterState,
    onRegister: (String, String, String, String, String, String) -> Unit,
    onBack: () -> Unit,
    onDismissDialog: () -> Unit,
) {

    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val edadValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    val idUser = UUID.randomUUID().toString()
    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current


    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        onBack()
                    }
                ){
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Icon",
                        tint = MaterialTheme.colors.primary
                    )
                }

                Text(
                    text = "Crear una Cuenta",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
                
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                //Se obtiene el nombre
                TransparentTextField(
                    textFieldValue = nameValue,
                    textLabel = "Nombre",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )


                //Se obtiene el correo
                TransparentTextField(
                    textFieldValue = emailValue,
                    textLabel = "Correo",
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )

                TransparentTextField(
                    textFieldValue = edadValue,
                    textLabel = "Edad",
                    maxChar = 3,
                    keyboardType = KeyboardType.Number,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )


                TransparentTextField(
                    textFieldValue = passwordValue,
                    textLabel = "Contraseña",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = if(passwordVisibility) {
                                    Icons.Default.Visibility
                                } else {
                                    Icons.Default.VisibilityOff
                                },
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if(passwordVisibility) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )

                TransparentTextField(
                    textFieldValue = confirmPasswordValue,
                    textLabel = "Confirmar Contraseña",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onRegister(
                                nameValue.value,
                                emailValue.value,
                                edadValue.value,
                                passwordValue.value,
                                confirmPasswordValue.value,
                                idUser
                            )
                        }
                    ),
                    imeAction = ImeAction.Done,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                confirmPasswordVisibility = !confirmPasswordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = if(confirmPasswordVisibility) {
                                    Icons.Default.Visibility
                                } else {
                                    Icons.Default.VisibilityOff
                                },
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if(confirmPasswordVisibility) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                RoundedButton(
                    text = "Registrarse",
                    displayProgressBar = state.displayProgressBar ,
                    onClick = {
                        onRegister(
                            nameValue.value,
                            emailValue.value,
                            edadValue.value,
                            passwordValue.value,
                            confirmPasswordValue.value,
                            idUser
                        )
                    }
                )
                
                ClickableText(
                    text = buildAnnotatedString {
                        append("Ya tienes una cuenta? ")

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        ){
                            append(" Iniciar sesión")
                        }
                    },
                    onClick = {
                        onBack()
                    }
                )
            }

        }

        if(state.errorMessage != null){
            EventDialog(
                errorMessage = state.errorMessage,
                onDismiss = onDismissDialog)
        }

    }
}