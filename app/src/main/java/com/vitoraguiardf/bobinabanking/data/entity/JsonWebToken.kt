package com.vitoraguiardf.bobinabanking.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class JsonWebToken(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
) {
    fun getAuthorization(): String {
        return "Bearer $accessToken"
    }
}
