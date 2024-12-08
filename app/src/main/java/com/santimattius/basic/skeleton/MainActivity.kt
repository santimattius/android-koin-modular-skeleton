package com.santimattius.basic.skeleton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.santimattius.android.ui.component.AppScaffold
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainRoute()
        }
    }
}

@Composable
fun MainRoute(
    viewModel: MainViewModel = koinViewModel(),
) {
    AppScaffold(
        title = stringResource(id = R.string.app_name)
    ) {
        val state by viewModel.state.collectAsStateWithLifecycle()
        MainScreen(
            state = state,
            modifier = Modifier
                .padding(it)

        )
    }

}

@Composable
fun MainScreen(
    state: MainUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = state.message)
            }
        }
        val uriHandler = LocalUriHandler.current
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                uriHandler.openUri("app://feature/first")
            }) {
            Text(text = stringResource(id = R.string.text_desc_main_action))
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    AppScaffold {
        MainScreen(
            state = MainUiState(isLoading = false, message = "Hello Android"),
        )
    }
}