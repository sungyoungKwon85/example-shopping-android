package com.example.shopping.api

import com.example.shopping.api.request.InquiryRequest
import com.example.shopping.api.request.SigninRequest
import com.example.shopping.api.request.SignupRequest
import com.example.shopping.api.response.ApiResponse
import com.example.shopping.api.response.InquiryResponse
import com.example.shopping.api.response.ProductResponse
import com.example.shopping.api.response.SigninResponse
import retrofit2.Response
import retrofit2.http.*

interface ShoppingApi {


    companion object {
        val instance = ApiGenerator().generate(ShoppingApi::class.java)
    }

    // Retrofit2.6.0부터 코루틴을 지원하므로 비동기 호출이 지원됨. suspend로 함수선언하면 된다
    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    @POST("/api/v1/users")
    suspend fun signup(@Body signupRequest: SignupRequest): ApiResponse<Void>

    @POST("/api/v1/signin")
    suspend fun signin(@Body signinRequest: SigninRequest): ApiResponse<SigninResponse>

    @GET("/api/v1/products/{id}")
    suspend fun getProduct(@Path("id") id: Long)
            : ApiResponse<ProductResponse>

    @GET("/api/v1/inquiries")
    suspend fun getInquiries(
        @Query("inquiryId") inquiryId: Long,
        @Query("productId") productId: Long? = null,
        @Query("requestUserId") requestUserId: Long? = null,
        @Query("productOwnerId") productOwnerId: Long? = null,
        @Query("direction") direction: String // prev,next
    ): ApiResponse<List<InquiryResponse>>

    @POST("/api/v1/inquiries")
    suspend fun registerInquiry(
        @Body request: InquiryRequest
    ): ApiResponse<Response<Void>>

}