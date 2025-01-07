package com.example.nkzrinskitordinci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.nkzrinskitordinci.data.PlayerViewModel
import com.example.nkzrinskitordinci.ui.theme.NKZrinskiTordinciTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<PlayerViewModel>()

        enableEdgeToEdge()
        setContent {
            NKZrinskiTordinciTheme {
                NavigationController(viewModel)
            }
        }
    }
}

