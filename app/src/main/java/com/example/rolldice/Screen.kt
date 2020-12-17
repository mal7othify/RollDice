package com.example.rolldice

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rolldice.ui.typography


@Composable
fun Screen() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    "Roll Dice Application",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    ) {
        Content()
    }
}

@Composable
fun Content() {
    val context = AmbientContext.current
    val rollState = remember { mutableStateOf(6) }
    val asset = when (rollState.value) {
        1 -> imageResource(R.drawable.dice_1)
        2 -> imageResource(R.drawable.dice_2)
        3 -> imageResource(R.drawable.dice_3)
        4 -> imageResource(R.drawable.dice_4)
        5 -> imageResource(R.drawable.dice_5)
        else -> imageResource(R.drawable.dice_6)
    }

    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(asset, modifier = Modifier.size(200.dp))
        Text(rollState.value.toString(), style = typography.h3, color = Color.Gray)
        Spacer(modifier = Modifier.preferredHeight(16.dp))
        Button(
            onClick = {
                roll(
                    updateRoll = { newRoll ->
                        rollState.value = newRoll
                    }
                )
                Toast.makeText(
                    context,
                    "Dice Rolled",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            Text(text = "ROLL")
        }
    }
}

fun roll(updateRoll: (Int) -> Unit) {
    return updateRoll(Dice(6).roll())
}

@Preview("Screen Preview")
@Composable
fun ScreenPreview() {
    Screen()
}