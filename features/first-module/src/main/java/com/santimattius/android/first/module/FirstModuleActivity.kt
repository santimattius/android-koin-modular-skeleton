package com.santimattius.android.first.module

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.santimattius.android.ui.component.AppScaffold
import com.santimattius.android.ui.component.Center
import org.koin.compose.koinInject

class FirstModuleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScaffold(
                title = "First Module"
            ) {
                FirstModuleScreen()
            }
        }
    }


}

@Composable
fun FirstModuleScreen() {
    val services = koinInject<FirstModuleServices>()
    Center(modifier = Modifier.fillMaxSize()) {
        Text(text = services.doSomething())
    }
}