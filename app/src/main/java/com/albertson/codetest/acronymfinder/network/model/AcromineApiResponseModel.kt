package com.albertson.codetest.acronymfinder.network.model

import com.google.gson.annotations.SerializedName

/**
 * AcromineApiResponseModel
 * Model class to store api response json
 */
class AcromineApiResponseModel : ArrayList<AcromineApiResponseModelItem>()

data class AcromineApiResponseModelItem(
    @SerializedName("lfs")
    val lfs: List<Lf> = listOf(),
    @SerializedName("sf")
    val sf: String = ""
)

data class Lf(
    @SerializedName("freq")
    val freq: Int = 0,
    @SerializedName("lf")
    val longFormWord: String = "",
    @SerializedName("since")
    val since: Int = 0,
    @SerializedName("vars")
    val vars: List<Var> = listOf()
)

data class Var(
    @SerializedName("freq")
    val freq: Int = 0,
    @SerializedName("lf")
    val lf: String = "",
    @SerializedName("since")
    val since: Int = 0
)