package com.example.taskmate.page.dashboard_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmate.R
import com.example.taskmate.component.compose_analog_clock.ComposeAnalogClock

@Composable
fun DashboardPage(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF50C2C9))
    ) {
        Image(
            painter = painterResource(id = R.drawable.top_bubble2),
            contentDescription = null,
            modifier = Modifier.align(
                Alignment.TopStart
            )
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(292.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        shape = CircleShape,
                        modifier = Modifier.size(100.dp)
                    ) {

                    }
                    Text("Welcome Jeegar goyani", color = Color.White, fontWeight = FontWeight.Bold)
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = 26.dp)
                    .padding(horizontal = 24.dp)
            ) {
                ComposeAnalogClock(isTimeStart = true)
            }
        }
    }

}