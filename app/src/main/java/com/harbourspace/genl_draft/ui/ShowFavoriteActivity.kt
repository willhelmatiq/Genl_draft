package com.harbourspace.genl_draft.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.harbourspace.genl_draft.AppApplication
import com.harbourspace.genl_draft.UnsplashViewModel
import com.harbourspace.genl_draft.ui.theme.Genl_draftTheme

class ShowFavoriteActivity: ComponentActivity() {

    private val unsplashViewModel: UnsplashViewModel by viewModels {
        UnsplashViewModel((application as AppApplication).repository)
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Genl_draftTheme {
                val collections = (application as AppApplication).repository.items.observeAsState()
                LazyColumn {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item {

                        Column {
                            val pagerState = rememberPagerState(pageCount = {
                                collections.value?.size ?: 0
                            })

                            HorizontalPager(
                                state = pagerState
                            ) { page ->

                                val item = collections.value!![page]

                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp)
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                ) {

                                    val painter = rememberAsyncImagePainter(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(item.results?.firstOrNull()?.urls?.regular)
                                            .build()
                                    )

                                    Image(
                                        painter = painter,
                                        contentDescription = "stringResource(id = R.string.description_image_preview)",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }

                            if (pagerState.pageCount > 1) {
                                Row (
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    HorizontalPagerIndicator(
                                        pagerState = pagerState,
                                        pageCount = collections.value?.size ?: 0,
                                        activeColor = MaterialTheme.colorScheme.secondaryContainer
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