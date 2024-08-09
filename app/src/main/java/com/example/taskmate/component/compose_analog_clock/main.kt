package com.example.taskmate.component.compose_analog_clock

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun ComposeAnalogClock(
    isTimeStart: Boolean = false,
    clockBackgroundColor: Color = Color.Black,
    clockThicknessColor: Color = Color.Gray,
    clockHourMarkersColor: Color = Color.White,
    clockMinuteMarkersColor: Color = Color.White,
    /** clock hand color **/
    hourHandColor: Color = Color.White,
    minuteHandColor: Color = Color.White,
    secondHandColor: Color = Color.Red,
) {
    var currentTimeMillis by remember { mutableStateOf(System.currentTimeMillis()) }

    // Update time every second if isTimeStart is true
    LaunchedEffect(Unit) {
        while (isTimeStart) {
            currentTimeMillis = System.currentTimeMillis()
            delay(1000L)  // Update every second
        }
    }

    // Initialize the calendar with the current time
    val calendar = rememberUpdatedState(Calendar.getInstance().apply {
        timeInMillis = currentTimeMillis
    })

    // Extract time components from the calendar
    val second by remember { derivedStateOf { calendar.value.get(Calendar.SECOND) } }
    val minute by remember { derivedStateOf { calendar.value.get(Calendar.MINUTE) } }
    val hour by remember { derivedStateOf { calendar.value.get(Calendar.HOUR) } }  // 12-hour format

    // Centralizing stroke widths and sizes
    val clockStrokeWidth = 10f
    val minuteMarkerStrokeWidth = 2f
    val hourMarkerStrokeWidth = 8f
    val secondHandStrokeWidth = 4f
    val minuteHandStrokeWidth = 5f
    val hourHandStrokeWidth = 6f

    Canvas(modifier = Modifier.fillMaxSize()) {
        // Calculate clock radius and center point of the canvas
        val circleRadius = (size.width / 2f)
        val canvasCenter = Offset(x = size.width / 2f, y = size.height / 2f)

        // Step 1: Draw clock background
        drawCircle(
            color = clockBackgroundColor,
            radius = circleRadius,
            center = canvasCenter
        )

        // Step 2: Draw clock thickness
        drawCircle(
            color = clockThicknessColor,
            style = Stroke(width = clockStrokeWidth),
            center = canvasCenter
        )

        // Step 3: Draw clock minute markers
        val minuteMarkerLength = circleRadius / 20f
        repeat(60) { minuteIndex ->
            rotate((minuteIndex / 60f) * 360) {
                val start = center - Offset(0f, circleRadius)
                val end = start + Offset(0f, minuteMarkerLength)
                drawLine(
                    color = clockMinuteMarkersColor,
                    start = start,
                    end = end,
                    strokeWidth = minuteMarkerStrokeWidth
                )
            }
        }

        // Step 4: Draw clock hour markers
        val hourMarkerLength = circleRadius / 10f
        repeat(12) { hourIndex ->
            rotate((hourIndex / 12f) * 360) {
                val start = center - Offset(0f, circleRadius)
                val end = start + Offset(0f, hourMarkerLength)
                drawLine(
                    color = clockHourMarkersColor,
                    start = start,
                    end = end,
                    strokeWidth = hourMarkerStrokeWidth
                )
            }
        }

        // Calculate rotation ratios for the clock hands
        val secondRatio = second / 60f
        val minuteRatio = (minute + secondRatio) / 60f
        val hourRatio = (hour + minuteRatio) / 12f

        // Step 5: Draw clock hour hand
        rotate(hourRatio * 360) {
            drawLine(
                color = hourHandColor,
                start = center,
                end = center - Offset(0f, circleRadius * 0.5f),
                strokeWidth = hourHandStrokeWidth
            )
        }

        // Step 6: Draw clock minute hand
        rotate(minuteRatio * 360) {
            drawLine(
                color = minuteHandColor,
                start = center,
                end = center - Offset(0f, circleRadius * 0.8f),
                strokeWidth = minuteHandStrokeWidth
            )
        }

        // Step 7: Draw clock second hand
        rotate(secondRatio * 360) {
            drawLine(
                color = secondHandColor,
                start = center,
                end = center - Offset(0f, circleRadius * 0.9f),
                strokeWidth = secondHandStrokeWidth
            )
        }

        // Optional: Draw central cap for the clock hands
        drawCircle(
            color = clockThicknessColor,
            radius = circleRadius * 0.05f,
            center = canvasCenter
        )
    }
}
