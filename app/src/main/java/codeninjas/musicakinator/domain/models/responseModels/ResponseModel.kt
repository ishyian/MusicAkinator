package codeninjas.musicakinator.domain.models.responseModels

import com.google.gson.annotations.SerializedName


data class ResponseModel<out T>(
    @SerializedName("data")
    val data: T?
)