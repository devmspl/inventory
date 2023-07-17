package com.app.inventory.Api

import com.app.inventory.Handler.AddProductHandler
import com.app.inventory.Handler.AddShopHandler
import com.app.inventory.Handler.DeleteProductHandler
import com.app.inventory.Handler.ForgotChangePassHandler
import com.app.inventory.Handler.ForgotPasswordHandler
import com.app.inventory.Handler.GetAllProductHandler
import com.app.inventory.Handler.GetAllShopHandler
import com.app.inventory.Handler.GetProductBySearchHandler
import com.app.inventory.Handler.LoginHandler
import com.app.inventory.Handler.OtpVerificationHandler
import com.app.inventory.Handler.ProductGetByIdHandler
import com.app.inventory.Handler.ProductGetByShopIdHandler
import com.app.inventory.Handler.ProductUpdateByidHandler
import com.app.inventory.Handler.RoleHandler
import com.app.inventory.Handler.SearchProductHandler
import com.app.inventory.Handler.SearchShopHandler
import com.app.inventory.Handler.ShopDeleteByShopIdHandler
import com.app.inventory.Handler.SignupHandler
import com.app.inventory.Handler.UpdateProductImageHandler
import com.app.inventory.Handler.UpdateShopHandler
import com.app.inventory.Handler.UpdateShopImageImageHandler
import com.app.inventory.Handler.UploadProductImageHandler
import com.app.inventory.Handler.UploadProfileImageHandler
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
import com.app.inventory.Utlis.SocketConnection
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WebServices {

    private val TAG = "WebSrvices"
    private lateinit var api: API


    fun create() {
        retrofit =
            Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()

        api = retrofit.create(API::class.java)

    }


    companion object Factory1 {
        private lateinit var mInstance: WebServices
        private lateinit var retrofit: Retrofit
        val base_url = "http://93.188.167.68:9002/api/"

        internal var okHttpClient = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS).build()

        fun getInstance(): WebServices? {
            mInstance = WebServices()
            return mInstance
        }
    }

    fun apiCreate() {
        api = retrofit.create(API::class.java)
    }

    fun signupMethod(
        name: String?,
        email: String?,
        phoneNo: String,
        password: String,
        deviceToken: String,
        signupHandler: SignupHandler,
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("name", name)
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("phoneNo", phoneNo)
        jsonObject.addProperty("password", password)
        jsonObject.addProperty("deviceToken", deviceToken)

        apiCreate()
        api?.signupAPI(jsonObject)?.enqueue(object : Callback<SignupExample?> {
            override fun onResponse(
                call: Call<SignupExample?>,
                response: Response<SignupExample?>,
            ) {
                val deviceToken = "deviceToken"
                // deviceToken = response.headers()["x-access  -token"]
                if (response.code() == 200) {
                    signupHandler.onSuccess(response.body(), deviceToken)
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            signupHandler.onError(message)
                        } else {
                            signupHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<SignupExample?>, t: Throwable) {
                signupHandler.onError(t.message)
            }
        })
    }

    fun loginMethod(email: String?, password: String?, token: String?, loginHandler: LoginHandler) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("password", password)
        jsonObject.addProperty("deviceToken", token)

        apiCreate()
        api?.logAPI(jsonObject)?.enqueue(object : Callback<LoginExample?> {
            override fun onResponse(call: Call<LoginExample?>, response: Response<LoginExample?>) {
                var acesstoken: String? = null
                acesstoken = response.headers()["x-access-token"]
                if (response.code() == 200) {
                    loginHandler.onSuccess(response.body(), acesstoken)
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            loginHandler.onError(message)
                        } else {
                            loginHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<LoginExample?>, t: Throwable) {
                loginHandler.onError(t.message)
            }
        })
    }


    fun forgotpassMethod(email: String, forgotPasswordHandler: ForgotPasswordHandler) {
        apiCreate()
        api?.forgotpassAPI(email)?.enqueue(object : Callback<ForgotPassExample?> {
            override fun onResponse(
                call: Call<ForgotPassExample?>,
                response: Response<ForgotPassExample?>,
            ) {
                if (response.code() == 200) {
                    forgotPasswordHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            forgotPasswordHandler.onError(message)
                        } else {
                            forgotPasswordHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ForgotPassExample?>, t: Throwable) {
                forgotPasswordHandler.onError(t.message)
            }
        })
    }
    //otpverifyMethod

    fun otpVerificationMethod(
        otp: String,
        otpToken: String,
        otpVerificationHandler: OtpVerificationHandler,
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("otp", otp)
        jsonObject.addProperty("otpToken", otpToken)
        apiCreate()
        api?.otpVerificationAPI(jsonObject)?.enqueue(object : Callback<OtpExample?> {
            override fun onResponse(call: Call<OtpExample?>, response: Response<OtpExample?>) {
                if (response.code() == 200) {
                    otpVerificationHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            otpVerificationHandler.onError(message)
                        } else {
                            otpVerificationHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<OtpExample?>, t: Throwable) {
                otpVerificationHandler.onError(t.message)
            }
        })
    }


    fun changePasswordMethod(
        otpToken: String,
        email: String,
        newPassword: String,
        forgotChangePassHandler: ForgotChangePassHandler,
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("otpToken", otpToken)
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("newPassword", newPassword)
        apiCreate()
        api?.forgotchangePasswordAPI(jsonObject)
            ?.enqueue(object : Callback<ForgotChangePassExample?> {
                override fun onResponse(
                    call: Call<ForgotChangePassExample?>,
                    response: Response<ForgotChangePassExample?>,
                ) {
                    if (response.code() == 200) {
                        forgotChangePassHandler.onSuccess(response.body())
                    } else {
                        val responceData = SocketConnection.convertStreamToString(
                            response.errorBody()!!.byteStream()
                        )
                        try {
                            val jsonObject = JSONObject(responceData)
                            val message = jsonObject.optString("message")
                            val error = jsonObject.optString("error")
                            if (!message.equals("", ignoreCase = true)) {
                                forgotChangePassHandler.onError(message)
                            } else {
                                forgotChangePassHandler.onError(error)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<ForgotChangePassExample?>, t: Throwable) {
                    forgotChangePassHandler.onError(t.message)
                }
            })
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //addshopFun
    fun addShopMethod(
        name: String,
        address: String,
        phoneno: Int,
        email: String,
        owner: String,
        addShopHandler: AddShopHandler,
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("name", name)
        jsonObject.addProperty("address", address)
        jsonObject.addProperty("phoneno", phoneno)
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("owner", owner)

        apiCreate()
        api?.addShopAPI(jsonObject)?.enqueue(object : Callback<AddShopModel?> {
            override fun onResponse(
                call: Call<AddShopModel?>,
                response: Response<AddShopModel?>,
            ) {
                if (response.code() == 200) {
                    addShopHandler.onSuccess(response.body(), owner)
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            addShopHandler.onError(message)
                        } else {
                            addShopHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<AddShopModel?>, t: Throwable) {
                addShopHandler.onError(t.message)
            }
        })
    }


    //uploadShopimage
    fun uploadProfileImage(
        id: String,
        image: MultipartBody.Part,
        uploadProfileImageHandler: UploadProfileImageHandler,
    ) {
        apiCreate()
        api.uploadShopImageAPI(image, id).enqueue(object : Callback<UploadShopImageExample> {
            override fun onResponse(
                call: Call<UploadShopImageExample>,
                response: Response<UploadShopImageExample>,
            ) {
                if (response.code() == 200) {
                    uploadProfileImageHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            uploadProfileImageHandler.onError(message)
                        } else {
                            uploadProfileImageHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<UploadShopImageExample>, t: Throwable) {
                uploadProfileImageHandler.onError(t.message)
            }
        })
    }

    //uploadShopimage
    fun updateShopImageMethod(
        id: String,
        image: MultipartBody.Part,
        updateShopImageImageHandler: UpdateShopImageImageHandler,
    ) {
        apiCreate()
        api.updateShopImageAPI(image, id).enqueue(object : Callback<UpdateShopImageExample> {
            override fun onResponse(
                call: Call<UpdateShopImageExample>,
                response: Response<UpdateShopImageExample>,
            ) {
                if (response.code() == 200) {
                    updateShopImageImageHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            updateShopImageImageHandler.onError(message)
                        } else {
                            updateShopImageImageHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<UpdateShopImageExample>, t: Throwable) {
                updateShopImageImageHandler.onError(t.message)
            }
        })
    }


    //getallshop
    fun getAllShop(
        token: String,
        pageNo: Int,
        pageSize: Int,
        getAllShopHandler: GetAllShopHandler,
    ) {
        apiCreate()
        api.getAllshopAPI(token, pageNo, pageSize).enqueue(object : Callback<GetAllShopModel> {
            override fun onResponse(
                call: Call<GetAllShopModel>,
                response: Response<GetAllShopModel>,
            ) {
                if (response.code() == 200) {
                    getAllShopHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getAllShopHandler.onError(message)
                        } else {
                            getAllShopHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetAllShopModel>, t: Throwable) {
                getAllShopHandler.onError(t.message)
            }
        })
    }

    //shopGetByUserMethod
    fun shopGetByUserMethod(
        id: String,
        roleHandler: RoleHandler,
    ) {
        apiCreate()
        api.shopGetByUser(id).enqueue(object : Callback<RoleExample> {
            override fun onResponse(
                call: Call<RoleExample>,
                response: Response<RoleExample>,
            ) {
                if (response.code() == 200) {
                    roleHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            roleHandler.onError(message)
                        } else {
                            roleHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<RoleExample>, t: Throwable) {
                roleHandler.onError(t.message)
            }
        })
    }

    //searchshop
    fun searchShop(
        searchValue: String,
        pageSize: String,
        phoneNo: String,
        searchShopHandler: SearchShopHandler,
    ) {
        apiCreate()
        api.searchShop(searchValue, pageSize, phoneNo)
            .enqueue(object : Callback<SearchShopExample> {
                override fun onResponse(
                    call: Call<SearchShopExample>,
                    response: Response<SearchShopExample>,
                ) {
                    if (response.code() == 200) {
                        searchShopHandler.onSuccess(response.body()!!)
                    } else {
                        val responceData = SocketConnection.convertStreamToString(
                            response.errorBody()!!.byteStream()
                        )
                        try {
                            val jsonObject = JSONObject(responceData)
                            val message = jsonObject.optString("message")
                            val error = jsonObject.optString("error")
                            if (!message.equals("", ignoreCase = true)) {
                                searchShopHandler.onError(message)
                            } else {
                                searchShopHandler.onError(error)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<SearchShopExample>, t: Throwable) {
                    searchShopHandler.onError(t.message)
                }
            })
    }

    fun updateShop(
        token: String,
        id: String,
        name: String,
        address: String,
        phoneno: Int,

        updateShopHandler: UpdateShopHandler,
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("name", name)
        jsonObject.addProperty("address", address)
        jsonObject.addProperty("phoneno", phoneno)


        apiCreate()
        api.updateshop(token, id, jsonObject).enqueue(object : Callback<UpdateShopExample> {
            override fun onResponse(
                call: Call<UpdateShopExample>,
                response: Response<UpdateShopExample>,
            ) {
                if (response.code() == 200) {
                    updateShopHandler.onSuccess(response.body()!!)
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            updateShopHandler.onError(message)
                        } else {
                            updateShopHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<UpdateShopExample>, t: Throwable) {
                updateShopHandler.onError(t.message)
            }
        })


    }


//    //shopGetByUserMethod
//    fun shopGetByUserIdMethod(
//        id: String,
//        roleHandler: RoleHandler,
//    ) {
//        apiCreate()
//        api.shopGetByUser(id).enqueue(object : Callback<RoleExample> {
//            override fun onResponse(
//                call: Call<RoleExample>,
//                response: Response<RoleExample>,
//            ) {
//                if (response.code() == 200) {
//                    roleHandler.onSuccess(response.body())
//                } else {
//                    val responceData = SocketConnection.convertStreamToString(
//                        response.errorBody()!!.byteStream()
//                    )
//                    try {
//                        val jsonObject = JSONObject(responceData)
//                        val message = jsonObject.optString("message")
//                        val error = jsonObject.optString("error")
//                        if (!message.equals("", ignoreCase = true)) {
//                            roleHandler.onError(message)
//                        } else {
//                            roleHandler.onError(error)
//                        }
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<RoleExample>, t: Throwable) {
//                roleHandler.onError(t.message)
//            }
//        })
//    }


    fun shopDeleteByShopIdMethod(
        token: String,
        id: String,
        shopDeleteByShopIdHandler: ShopDeleteByShopIdHandler,
    ) {
        apiCreate()
        api.shopDeleteByShopId(token, id).enqueue(object : Callback<ShopDeleteByShopIdExample> {
            override fun onResponse(
                call: Call<ShopDeleteByShopIdExample>,
                response: Response<ShopDeleteByShopIdExample>,
            ) {
                if (response.code() == 200) {
                    shopDeleteByShopIdHandler.onSuccess(response.body()!!)
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            shopDeleteByShopIdHandler.onError(message)
                        } else {
                            shopDeleteByShopIdHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ShopDeleteByShopIdExample>, t: Throwable) {
                shopDeleteByShopIdHandler.onError(t.message)
            }
        })
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //addproductexample
    fun addProudctMethod(
        productname: String,
        description: String,
        quanlity: String,
        date: String,
        costprice: String,
        sellingprice: String,
        shopId: String,
        addProductHandler: AddProductHandler,
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("productname", productname)
        jsonObject.addProperty("description", description)
        jsonObject.addProperty("quanlity", quanlity)
        jsonObject.addProperty("date", date)
        jsonObject.addProperty("costprice", costprice)
        jsonObject.addProperty("sellingprice", sellingprice)
        jsonObject.addProperty("shopId", shopId)

        apiCreate()
        api.addProductAPI(jsonObject).enqueue(object : Callback<AddProductExample> {
            override fun onResponse(
                call: Call<AddProductExample>,
                response: Response<AddProductExample>,
            ) {
                if (response.code() == 200) {
                    addProductHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            addProductHandler.onError(message)
                        } else {
                            addProductHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<AddProductExample>, t: Throwable) {
                addProductHandler.onError(t.message)
            }
        })
    }


    //uploadproductimage
    fun uploadProductImage(
        id: String,
        image: MultipartBody.Part,
        uploadProductImageHandler: UploadProductImageHandler,
    ) {
        apiCreate()
        api.uploadProductImageAPI(image, id).enqueue(object : Callback<UploadProductImageModel> {
            override fun onResponse(
                call: Call<UploadProductImageModel>,
                response: Response<UploadProductImageModel>,
            ) {
                if (response.code() == 200) {
                    uploadProductImageHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            uploadProductImageHandler.onError(message)
                        } else {
                            uploadProductImageHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<UploadProductImageModel>, t: Throwable) {
                uploadProductImageHandler.onError(t.message)
            }
        })
    }

    //productbyid
    fun productGetById(
        id: String,
        productGetByIdHandler: ProductGetByIdHandler,
    ) {
        apiCreate()
        api.productGetById(id).enqueue(object : Callback<ProductGetByIdExample> {
            override fun onResponse(
                call: Call<ProductGetByIdExample>,
                response: Response<ProductGetByIdExample>,
            ) {
                if (response.code() == 200) {
                    productGetByIdHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            productGetByIdHandler.onError(message)
                        } else {
                            productGetByIdHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ProductGetByIdExample>, t: Throwable) {
                productGetByIdHandler.onError(t.message)
            }
        })
    }

    //productgetByShopId
    fun productgetByShopId(
        id: String,
        productGetByShopIdHandler: ProductGetByShopIdHandler,
    ) {
        apiCreate()
        api.productGetByShopId(id).enqueue(object : Callback<ProductGetByShopIdExample> {
            override fun onResponse(
                call: Call<ProductGetByShopIdExample>,
                response: Response<ProductGetByShopIdExample>,
            ) {
                if (response.code() == 200) {
                    productGetByShopIdHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            productGetByShopIdHandler.onError(message)
                        } else {
                            productGetByShopIdHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ProductGetByShopIdExample>, t: Throwable) {
                productGetByShopIdHandler.onError(t.message)
            }
        })
    }

    fun productSearchByShopIdMethod(
        shopId: String,
        searchValue: String,
        pageSize: String,
        pageNo: String,

        getProductBySearchHandler: GetProductBySearchHandler,
    ) {
        apiCreate()
        api.productSearchByShopIdAPI(shopId, searchValue, pageSize, pageNo)
            .enqueue(object : Callback<ProductSearchByShopId> {
                override fun onResponse(
                    call: Call<ProductSearchByShopId>,
                    response: Response<ProductSearchByShopId>,
                ) {
                    if (response.code() == 200) {
                        getProductBySearchHandler.onSuccess(response.body())
                    } else {
                        val responceData = SocketConnection.convertStreamToString(
                            response.errorBody()!!.byteStream()
                        )
                        try {
                            val jsonObject = JSONObject(responceData)
                            val message = jsonObject.optString("message")
                            val error = jsonObject.optString("error")
                            if (!message.equals("", ignoreCase = true)) {
                                getProductBySearchHandler.onError(message)
                            } else {
                                getProductBySearchHandler.onError(error)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<ProductSearchByShopId>, t: Throwable) {
                    getProductBySearchHandler.onError(t.message)
                }
            })
    }

    fun searchProductByShopIdMethod(
        shopId: String,
        searchValue: String,
        pageSize: String,
        pageNo: String,

        searchProductHandler: SearchProductHandler,
    ) {
        apiCreate()
        api.searcProducthByShopIdAPI(shopId, searchValue, pageSize, pageNo)
            .enqueue(object : Callback<SearchproductExample> {
                override fun onResponse(
                    call: Call<SearchproductExample>,
                    response: Response<SearchproductExample>,
                ) {
                    if (response.code() == 200) {
                        searchProductHandler.onSuccess(response.body()!!)
                    } else {
                        val responceData = SocketConnection.convertStreamToString(
                            response.errorBody()!!.byteStream()
                        )
                        try {
                            val jsonObject = JSONObject(responceData)
                            val message = jsonObject.optString("message")
                            val error = jsonObject.optString("error")
                            if (!message.equals("", ignoreCase = true)) {
                                searchProductHandler.onError(message)
                            } else {
                                searchProductHandler.onError(error)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<SearchproductExample>, t: Throwable) {
                    searchProductHandler.onError(t.message)
                }
            })
    }

    //getallproduct
    fun getAllproduct(
        token: String,
        pageNo: Int,
        pageSize: Int,
        allproductHandler: GetAllProductHandler,
    ) {
        apiCreate()
        api.getAllproductAPI(token, pageNo, pageSize)
            .enqueue(object : Callback<GetAllProductModel> {
                override fun onResponse(
                    call: Call<GetAllProductModel>,
                    response: Response<GetAllProductModel>,
                ) {
                    if (response.code() == 200) {
                        allproductHandler.onSuccess(response.body())
                    } else {
                        val responceData = SocketConnection.convertStreamToString(
                            response.errorBody()!!.byteStream()
                        )
                        try {
                            val jsonObject = JSONObject(responceData)
                            val message = jsonObject.optString("message")
                            val error = jsonObject.optString("error")
                            if (!message.equals("", ignoreCase = true)) {
                                allproductHandler.onError(message)
                            } else {
                                allproductHandler.onError(error)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<GetAllProductModel>, t: Throwable) {
                    allproductHandler.onError(t.message)
                }
            })
    }


    fun productUpdateByIdMethod(
        token: String,
        id: String,
        productname: String,
        description: String,
        quantity: String,
        date: String,
        costprice: Int,
        sellingprice: Int,
        productUpdateByidHandler: ProductUpdateByidHandler

    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("name", productname)
        jsonObject.addProperty("address", description)
        jsonObject.addProperty("phoneno", quantity)
        jsonObject.addProperty("phoneno", date)
        jsonObject.addProperty("phoneno", costprice)
        jsonObject.addProperty("phoneno", sellingprice)

        apiCreate()
        api.productUpdateById(token, id, jsonObject).enqueue(object : Callback<ProductUpdateByIdExample> {
            override fun onResponse(
                call: Call<ProductUpdateByIdExample>,
                response: Response<ProductUpdateByIdExample>,
            ) {
                if (response.code() == 200) {
                    productUpdateByidHandler.onSuccess(response.body()!!)
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            productUpdateByidHandler.onError(message)
                        } else {
                            productUpdateByidHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ProductUpdateByIdExample>, t: Throwable) {
                productUpdateByidHandler.onError(t.message)
            }
        })


    }

    fun updateProductImageMethod(
        id: String,
        image: MultipartBody.Part,
        updateProductImageHandler: UpdateProductImageHandler,
    ) {
        apiCreate()
        api.updateProductImageAPI(image, id).enqueue(object : Callback<ProductImageUpdateExample> {
            override fun onResponse(
                call: Call<ProductImageUpdateExample>,
                response: Response<ProductImageUpdateExample>,
            ) {
                if (response.code() == 200) {
                    updateProductImageHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            updateProductImageHandler.onError(message)
                        } else {
                            updateProductImageHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ProductImageUpdateExample>, t: Throwable) {
                updateProductImageHandler.onError(t.message)
            }
        })
    }

    //deleteProduct
    fun deleteProduct(token: String, id: String, deleteProductHandler: DeleteProductHandler) {
        apiCreate()
        api.deleteProduct(token, id).enqueue(object : Callback<DeleteProductExample> {
            override fun onResponse(
                call: Call<DeleteProductExample>,
                response: Response<DeleteProductExample>,
            ) {
                if (response.code() == 200) {
                    deleteProductHandler.onSuccess(response.body()!!)
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            deleteProductHandler.onError(message)
                        } else {
                            deleteProductHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<DeleteProductExample>, t: Throwable) {
                deleteProductHandler.onError(t.message)
            }
        })
    }
}