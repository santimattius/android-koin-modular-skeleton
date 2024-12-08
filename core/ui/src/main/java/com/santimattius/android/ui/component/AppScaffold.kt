@file:OptIn(ExperimentalMaterial3Api::class)

package com.santimattius.android.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun AppScaffold(
    title: String = "",
    content: @Composable (PaddingValues) -> Unit
) {
    AppContainer {
        Scaffold(
            topBar = {
                AppBar(title = title)
            },
            content = { padding ->
                content(padding)
            }
        )
    }
}