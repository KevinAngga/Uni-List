package com.angga.univlist.ui.presentation

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.angga.univlist.ui.presentation.theme.UnivListTheme
import com.angga.univlist.ui.presentation.utils.openInAppBrowser

@Composable
fun UniversityScreenRoot(
    viewModel: UniversityViewModel = hiltViewModel()
) {
    UniversityScreen(
        state = viewModel.state,
        onAction = {
            viewModel.onAction(it)
        }
    )
}


@Composable
private fun UniversityScreen(
    state: UniversityState,
    onAction: (UniversityAction) -> Unit
) {
    Scaffold {
        val result = state.universityList.collectAsLazyPagingItems()
        val loadState = result.loadState
        val context : Context = LocalContext.current
        val focusRequester = remember { FocusRequester() }

        Column(
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            TextField(
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth(),
                value = state.searchValue,
                onValueChange = { searchStr ->
                    onAction(UniversityAction.OnTextChange(searchStr))
                },
                placeholder = {
                    Text(
                        text = "Input University Name",
                    )
                },
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                textStyle = MaterialTheme.typography.labelLarge,
            )

            LazyColumn(
                modifier = Modifier
                    .systemBarsPadding()
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                items(
                    count = result.itemCount,
                    key = result.itemKey { it.uniName }
                ) { index ->
                    val university = result[index]
                    if (university != null) {
                        UniversityItem(
                            university = university,
                            onItemClick = {
                                openInAppBrowser(context = context, url = it)
                            }
                        )
                    }
                }

                result.apply {
                    when {
                        loadState.append is LoadState.Loading -> {
                            item {
                                Text("loading")
                            }
                        }

                        loadState.refresh is LoadState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier.fillParentMaxSize()
                                ) {
                                    Text("loading")
                                }
                            }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val error = loadState.refresh as LoadState.Error
                            item {
                                error.error.localizedMessage?.let {
                                    ErrorMessage(
                                        modifier = Modifier.fillParentMaxSize(),
                                        message = it,
                                        onClickRetry = { retry() })
                                }
                            }
                        }


                        loadState.append is LoadState.Error -> {
                            val error = loadState.append as LoadState.Error
                            item {
                                error.error.localizedMessage?.let {
                                    ErrorMessage(
                                        modifier = Modifier.fillParentMaxSize(),
                                        message = it,
                                        onClickRetry = {  })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
        )

        OutlinedButton(onClick = onClickRetry) {
            Text(text = "retry")
        }
    }
}




@Preview
@Composable
private fun UniversityScreenPreview() {
    UnivListTheme {
        UniversityScreen(
            state = UniversityState(),
            onAction = {}
        )
    }
}