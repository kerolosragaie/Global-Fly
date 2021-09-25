package com.kerollosragaie.globalfly.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface MessageService {

    /**
     * Now we call messages from another server
     * http://127.0.0.1:7000/
     * */
    @GET
    fun getMessages(@Url serverUrl:String): Call<String>

}