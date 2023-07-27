package com.harbourspace.myapplication.ui.api


import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import com.harbourspace.genl_draft.ui.data.UnsplashPhoto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val AUTHORIZATION_CLIENT_ID = "Client-ID"
private const val ACCESS_KEY = "uMpOfSWPF1X4EACEg9lMoBlhItiBISGvUqU2wELYvvY"

interface UnsplashApi {


    @Headers("Authorization: $AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
    @GET("search/photos")
    fun searchPhoto(@Query(value = "query") keyword : String, @Query(value = "per_page") per_page : Int): Call<UnsplashPhoto>

}