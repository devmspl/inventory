package com.app.inventory.Api

import com.app.inventory.Models.AddProductModel.AddProductExample
import com.app.inventory.Models.AddShopModel.AddShopModel
import com.app.inventory.Models.DeleteProductModel.DeleteProductExample
import com.app.inventory.Models.ForgotChangePassword.ForgotChangePassExample
import com.app.inventory.Models.ForgotPasseword.ForgotPassExample
import com.app.inventory.Models.GetAllProductModel.GetAllProductModel
import com.app.inventory.Models.GetAllShopModel.GetAllShopModel
import com.app.inventory.Models.LoginExample
import com.app.inventory.Models.OtpVerification.OtpExample
import com.app.inventory.Models.ProductGetById.ProductGetByIdExample
import com.app.inventory.Models.ProductGetByShopid.ProductGetByShopIdExample
import com.app.inventory.Models.ProductSearchByShopIdModel.ProductSearchByShopId
import com.app.inventory.Models.ProductUpdateByiD.ProductUpdateByIdExample
import com.app.inventory.Models.ProductUpdateImageModel.ProductImageUpdateExample
import com.app.inventory.Models.RoleModel.RoleExample
import com.app.inventory.Models.SearchShopModel.SearchShopExample
import com.app.inventory.Models.SearchproductModel.SearchproductExample
import com.app.inventory.Models.ShopDeleteByShopIdModel.ShopDeleteByShopIdExample
import com.app.inventory.Models.SignUp.SignupExample
import com.app.inventory.Models.UpdateShopImageExample.UpdateShopImageExample
import com.app.inventory.Models.UpdateShopModel.UpdateShopExample
import com.app.inventory.Models.UploadProductImage.UploadProductImageModel
import com.app.inventory.Models.UploadshopImageModel.UploadShopImageExample
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    //LoginAPI
    @POST("users/login")
    fun logAPI(@Body jsonObject: JsonObject?): Call<LoginExample?>?

    //signupAPI
    @POST("users/create")
    fun signupAPI(@Body jsonObject: JsonObject?): Call<SignupExample>

    //forogotpasswordAPI
    @GET(" users/otp")
    fun forgotpassAPI(@Query("email") email: String): Call<ForgotPassExample>

    //otpverificationAPI
    @POST("users/otpVerify")
    fun otpVerificationAPI(@Body jsonObject: JsonObject?): Call<OtpExample>

    @POST("users/forgotPassword")
    fun forgotchangePasswordAPI(@Body jsonObject: JsonObject?): Call<ForgotChangePassExample>


    //.....................................................................................................


    //addshopapi
    @POST("shop/create")
    fun addShopAPI(@Body jsonObject: JsonObject?): Call<AddShopModel>

    //getallshop
    @GET("shop/getallShop")
    fun getAllshopAPI(
        @Header("x-access-token") token: String, @Query("pageNo") pageNo: Int,
        @Query("pageSize")
        pageSize: Int,
    ): Call<GetAllShopModel>

    //shop/getByUser/{userId}
    @GET("shop/getByUser/{userId}")
    fun shopGetByUser(@Path("userId") id: String): Call<RoleExample>

    //searchshop
    @GET("shop/search")
    fun searchShop(
        @Query("searchValue") searchValue: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNo") pageNo: String,
    ): Call<SearchShopExample>

    //uploadshopImage
    @Multipart
    @PUT("shop/profileImageUpload/{id}")
    fun uploadShopImageAPI(
        @Part image: MultipartBody.Part,
        @Path("id") id: String,
    ): Call<UploadShopImageExample>


    @Multipart
    @PUT("shop/profileImageUpload/{id}")
    fun updateShopImageAPI(
        @Part image: MultipartBody.Part,
        @Path("id") id: String,
    ): Call<UpdateShopImageExample>

    //updateshop
    @PUT("shop/updateShop/{id}")
    fun updateshop(
        @Header("x-access-token") token: String,
        @Path("id") id: String,
        @Body jsonObject: JsonObject?,
    ): Call<UpdateShopExample>


    @DELETE("shop/deleteShop/{id}")
    fun shopDeleteByShopId(
        @Header("x-access-token") token: String,
        @Path("id")id: String
    ):Call<ShopDeleteByShopIdExample>

    //......................................................................................................


    //createproduct
    @POST("product/create")
    fun addProductAPI(@Body jsonObject: JsonObject?): Call<AddProductExample>

    //productgetbyshopid
    @GET("product/getByshop/{id}")
    fun productGetByShopId(@Path("id") id: String): Call<ProductGetByShopIdExample>

    //productgetbyid
    @GET("product/getByshop/{id}")
    fun productGetById(@Path("id") id: String): Call<ProductGetByIdExample>

    //getproductbysearch
    @GET("product/searchProductsByShop")
    fun productSearchByShopIdAPI(
        @Query("shopId") shopId: String,
        @Query("searchValue") searchValue: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNo") pageNo: String,
    ): Call<ProductSearchByShopId>

    //getproductbysearch
    @GET("product/searchProductsByShop")
    fun searcProducthByShopIdAPI(
        @Query("shopId") shopId: String,
        @Query("searchValue") searchValue: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNo") pageNo: String,
    ): Call<SearchproductExample>

    //getallproduct
    @GET("product/getAll")
    fun getAllproductAPI(
        @Header("x-access-token") token: String, @Query("pageNo") pageNo: Int,
        @Query("pageSize")
        pageSize: Int,
    ): Call<GetAllProductModel>

    //uploadproductimage
    @Multipart
    @PUT("product/profileImageUpload/{id}")
    fun uploadProductImageAPI(
        @Part image: MultipartBody.Part,
        @Path("id") id: String,
    ): Call<UploadProductImageModel>


    //updateproduct
    @PUT("product/update/{id}")
    fun productUpdateById(
        @Header("x-access-token") token: String,
        @Path("id") id: String,
        @Body jsonObject: JsonObject?,
    ): Call<ProductUpdateByIdExample>

    //deleteProduct
    @DELETE("product/Delete/{id}")
    fun deleteProduct(
        @Header("x-access-token") token: String,
        @Path("id") id: String,
    ): Call<DeleteProductExample>

    @Multipart
    @PUT("product/profileImageUpload/{id}")
    fun updateProductImageAPI(
        @Part image: MultipartBody.Part,
        @Path("id") id: String,
    ): Call<ProductImageUpdateExample>
}