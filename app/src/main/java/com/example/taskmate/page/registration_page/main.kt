package com.example.taskmate.page.registration_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.taskmate.R
import com.example.taskmate.component.form.FormField
import com.example.taskmate.component.form.FormFieldProperty
import com.example.taskmate.component.form.Form
import com.example.taskmate.component.form.Schema
import com.example.taskmate.component.form.Validator

@Composable
fun RegistrationPage(navController: NavController) {
    val initialVal = mapOf(
        "fullname" to "",
        "email" to "",
        "password" to "",
        "confirmPassword" to ""
    )

    val handleSubmit: (value: Map<String, Any>) -> Unit = {
//        Log.d("handleSubmit", "RegistrationPage: $initialValue")
    }
    val property = FormFieldProperty.Builder().setShape(RoundedCornerShape(100.dp)).setColors(
        TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            errorPlaceholderColor = Color.Red,
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F3))
    ) {
        Image(
            painter = painterResource(id = R.drawable.top_bubble),
            contentDescription = "",
            modifier = Modifier.align(
                Alignment.TopStart
            )
        )
        Form(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 35.dp)
                .padding(bottom = 29.dp),
            vertical = Arrangement.Bottom,
            horizontal = Alignment.CenterHorizontally,
            initialValue = initialVal,
            schema = Schema(
                "fullname" to { value, _ -> Validator(value).require() },
                "email" to { value, _ -> Validator(value).require().emailForm() },
                "password" to { value, entire ->
                    Validator(value).require().notEqualString(
                        entire["confirmPassword"]!!.toString(),
                        message = "it not same confirm password."
                    ).minLength(8)
                },
                "confirmPassword" to { value, entire ->
                    Validator(value).require()
                        .notEqualString(
                            entire["password"]!!.toString(),
                            message = "it not same password."
                        )
                        .minLength(8)
                }),
            onSubmit = handleSubmit
        ) {
            Text("Welcome to Onboard!", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(31.dp))
            Text(
                "Let’s help to meet up your tasks.",
                modifier = Modifier.width(180.dp),
                style = TextStyle(textAlign = TextAlign.Center)
            )
            Spacer(modifier = Modifier.height(90.dp))
            FormField(
                name = "fullname",
                modifier = Modifier.fillMaxWidth(),
                property = property.copy().setPlaceholder {
                    Text("Enter your full name", fontSize = 13.sp)
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            FormField(
                name = "email",
                modifier = Modifier.fillMaxWidth(),
                property = property.copy().setPlaceholder {
                    Text("Enter your Email", fontSize = 13.sp)
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            FormField(
                name = "password",
                modifier = Modifier.fillMaxWidth(),
                property = property.copy().setPlaceholder {
                    Text("Enter Password", fontSize = 13.sp)
                }.setVisualTransformation(PasswordVisualTransformation())
            )
            Spacer(modifier = Modifier.height(30.dp))
            FormField(
                name = "confirmPassword",
                modifier = Modifier.fillMaxWidth(),
                property = property.copy().setPlaceholder {
                    Text("Confirm password", fontSize = 13.sp)
                }.setVisualTransformation(PasswordVisualTransformation())
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { it() },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF50C2C9)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(text = "Register", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(19.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Already have an account ?", fontSize = 16.sp)
                Text(
                    "Sign in",
                    fontSize = 16.sp,
                    style = TextStyle(color = Color(0xFF50C2C9)),
                    modifier = Modifier
                        .clickable {
                            navController.navigate("/login")
                        }
                        .padding(start = 2.dp)
                )
            }
        }
    }

}
