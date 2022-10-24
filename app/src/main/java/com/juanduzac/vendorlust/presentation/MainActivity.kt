package com.juanduzac.vendorlust.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.juanduzac.vendorlust.presentation.navigation.Navigation
import com.juanduzac.vendorlust.presentation.ui.theme.VendorLustTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VendorLustTheme {
                Navigation(
                    navController = rememberNavController(),
                    startCallIntent = ::startCallIntent,
                    startWebIntent = ::startWebIntent,
                    startEmailIntent = ::startEmailIntent
                )
            }
        }
    }

    private fun startCallIntent(number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    private fun startWebIntent(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            )
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this@MainActivity,
                "There are no browser clients installed.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun startEmailIntent(emailAddress: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject of email")
        intent.putExtra(Intent.EXTRA_TEXT, "body of email")
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this@MainActivity,
                "There are no email clients installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
