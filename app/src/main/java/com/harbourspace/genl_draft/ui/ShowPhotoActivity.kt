package com.harbourspace.genl_draft.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.harbourspace.genl_draft.AppApplication
import com.harbourspace.genl_draft.EXTRA_TEXT
import com.harbourspace.genl_draft.R
import com.harbourspace.genl_draft.UnsplashViewModel
import com.harbourspace.genl_draft.ui.theme.Genl_draftTheme
import com.harbourspace.genl_draft.ui.theme.Purple80

class ShowPhotoActivity : ComponentActivity() {

    private val unsplashViewModel: UnsplashViewModel by viewModels {
        UnsplashViewModel((application as AppApplication).repository)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = intent.extras!!.get(EXTRA_TEXT) as String
        unsplashViewModel.searchImage(text)
        setContent {
            Genl_draftTheme {
                val image = unsplashViewModel.item.observeAsState()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(stringResource(id = R.string.return_to_chat))
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    startActivity(
                                        Intent(
                                            applicationContext,
                                            MainActivity::class.java
                                        )
                                    )
                                    finish()
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = stringResource(id = R.string.return_to_chat)
                                    )
                                }
                            })
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                            .background(Color.White),
                    ) {
                        Surface() {
                            val painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(image.value?.results?.firstOrNull()?.urls?.regular)
                                    .build()
                            )
                            Image(
                                painter = painter,
                                contentDescription = "test",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp),
                                verticalArrangement = Arrangement.Top,
                            ) {
                                Spacer(modifier = Modifier.height(50.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                ) {
                                    Spacer(modifier = Modifier.width(20.dp))
                                    IconButton(onClick = { unsplashViewModel.saveImage(image.value!!) }) {
                                        Icon(
                                            modifier = Modifier.size(50.dp),
                                            imageVector = Icons.Filled.FavoriteBorder,
                                            contentDescription = stringResource(id = R.string.like_picture),
                                            tint = Purple80
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(30.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                ) {
                                    Spacer(modifier = Modifier.width(20.dp))
                                    IconButton(onClick = { startActivity(Intent(applicationContext, ShowFavoriteActivity::class.java)) }) {
                                        Icon(
                                            modifier = Modifier.size(50.dp),
                                            painter = painterResource(id = R.drawable.ic_checkroom),
                                            contentDescription = stringResource(id = R.string.go_to_checkroom),
                                            tint = Purple80
                                        )
                                    }
                                }

                            }
                        }

                    }
                }
            }
        }
    }
}