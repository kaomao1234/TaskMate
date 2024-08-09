package com.example.taskmate.page.login_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.taskmate.R
import com.example.taskmate.component.form.FormFieldProperty
import com.example.taskmate.component.form.Form
import com.example.taskmate.component.form.FormField
import com.example.taskmate.component.form.Schema
import com.example.taskmate.component.form.Validator

@Composable
fun LoginPage(navController: NavController) {
    val handleSubmit: (value: Map<String, Any>) -> Unit = {

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
        Image(painter = painterResource(id = R.drawable.top_bubble), contentDescription = null)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 35.dp)
                .padding(bottom = 29.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome back", style = TextStyle(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(53.dp))
            Image(
                painter = painterResource(id = R.drawable.login_logo),
                contentDescription = null,
                modifier = Modifier.size(184.dp, 138.dp)
            )
            Spacer(modifier = Modifier.height(87.dp))
            Form(
                horizontal = Alignment.CenterHorizontally,
                initialValue = mapOf("email" to "", "password" to ""),
                schema = Schema(
                    "email" to { value, _ ->
                        Validator(value).require()
                    },
                    "password" to { value, _ ->
                        Validator(value).require()
                    }
                ),
                onSubmit = { handleSubmit(it) }) {
                FormField(name = "email", property = property.copy().setPlaceholder {
                    Text("Enter your Email", fontSize = 13.sp)
                }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(30.dp))
                FormField(name = "password", property = property.copy().setPlaceholder {
                    Text("Enter Password", fontSize = 13.sp)
                }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    text = "Forget password ?",
                    style = TextStyle(color = Color(0xFF50C2C9), fontSize = 13.sp),
                    modifier = Modifier.clickable { })
                Spacer(modifier = Modifier.height(48.dp))
                Button(
                    onClick = { it() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF50C2C9)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = "Login", fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.height(19.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Don’t have an account ?", fontSize = 16.sp)
                    Text(
                        "Sign Up",
                        fontSize = 16.sp,
                        style = TextStyle(color = Color(0xFF50C2C9)),
                        modifier = Modifier
                            .clickable {
                                navController.navigate("/registration")
                            }
                            .padding(start = 2.dp)
                    )
                }
            }
        }
    }

}
