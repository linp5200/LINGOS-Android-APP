package com.lingos.app.ui.splash
import android.os.Bundle
import androidx.activity.ComponentActivity
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(android.widget.TextView(this).apply {
            text = "Splash"
        })
    }
}
