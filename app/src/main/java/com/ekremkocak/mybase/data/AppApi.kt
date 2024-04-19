package com.ekremkocak.mybase.data


import com.ekremkocak.mybase.data.network.response.base.BaseResponse
import com.ekremkocak.mybase.utilities.Constants
import retrofit2.Response
import retrofit2.http.*


interface AppApi {

    @GET(Constants.GET_DISCOUNTS)
    suspend fun getDiscounts(
        @Query(Constants.QUERY_USER_ID) userId: Int,
        @Header(Constants.QUERY_TOKEN) token: String
    ): Response<ExploreResponse>

    @POST(Constants.ACCOUNT_LOG_OUT)
    suspend fun logOut(@Body request: LogOutRequest): Response<BaseResponse>


}