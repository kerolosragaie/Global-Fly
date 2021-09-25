package com.kerollosragaie.globalfly.services

import com.kerollosragaie.globalfly.models.Destination
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface DestinationService {

    /**
     * This function to get list of destinations without filtering the list,
     * to filter the list, ex: Query("country") to filter by counter
     * param country is nullable as if send null so show all list, else filter by country
     * param count is nullable, ex:=3 show list of 3 items else if null so show all list data
     * @param filter in backend filter var is named also (name as i want), and it is
     * of type QueryMap as takes both country and count
     * */
    @GET("destination")
    //@QueryMap filter:HashMap<String,String>?
    fun getDestinationList(): Call<List<Destination>>


    //Get one particular destination using ID / using path paramters
    @GET("destination/{id}")
    fun getDestination(@Path("id") id:Int):Call<Destination>



}