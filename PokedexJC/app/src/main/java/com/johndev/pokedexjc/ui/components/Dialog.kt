package com.johndev.pokedexjc.ui.components

import android.app.AlertDialog
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*

@Composable
fun AlertDialogDeveloping(
    openDialog: Boolean = false,
    closeDialog: (Boolean) -> Unit
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                closeDialog(false)
            },
            title = {
                Text(text = "Option Under Development")
            },
            text = {
                Text("This option is under development, check back soon to see the new updates")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog(false)
                    }
                ) {
                    Text(
                        text = "Confirm",
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
        )
    }
}