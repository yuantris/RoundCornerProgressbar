package com.core.lib.progressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.ffgreatking.progressbar.TextRoundCornerProgressBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<TextRoundCornerProgressBar>(R.id.progress_bar)

        lifecycleScope.launch {

            repeat(200) {
                delay(300)
                progressBar.setProgressText("$it%")
                progressBar.setProgress(it)
            }
        }

    }
}
