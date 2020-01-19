package codeninjas.musicakinator.data.audd.responseModel

import com.google.gson.annotations.SerializedName


data class ResponseModel<out T>(
    @SerializedName("data")
    val data: T?
)