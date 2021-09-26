package com.kerollosragaie.globalfly.services

import com.kerollosragaie.globalfly.models.Destination
import retrofit2.Call
import retrofit2.http.*


interface DestinationService {

    /**
     * This function to get list of destinations without filtering the list,
     * to filter the list, ex: Query("country") to filter by counter
     * param country is nullable as if send null so show all list, else filter by country
     * param count is nullable, ex:=3 show list of 3 items else if null so show all list data
     * @param filter in backend filter var is named also (name as i want), and it is
     * of type QueryMap as takes both country and count
     * @Headers : to send some values from client to server and device type or language, etc..
     * */

    //@Headers("x-device-type: Android","x-foo: bar")
    @GET("destination")
    //@QueryMap filter:HashMap<String,String>?
    fun getDestinationList(
        @QueryMap filter: HashMap<String, String>,
       // @Header("Accept-Language") language: String,
    ): Call<List<Destination>>


    //Get one particular destination using ID / using path paramters
    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>

    //Add new destiny
    //we will need body (object) to be added to destination list
    @POST("destination")
    fun addDestination(@Body destination: Destination): Call<Destination>

    //update an old destiny
    //we need  body (object) that we will update to
    @PUT("destination/{id}")
    fun updateDestination(@Path("id") id:Int,@Body destination: Destination):Call<Destination>

    //delete a destiny
    @DELETE("destination/{id}")
    fun deleteDestination(@Path("id") id:Int,):Call<Unit>
}