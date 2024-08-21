package com.vitoraguiardf.bobinabanking.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    @SerializedName("short_name")
    val shortName: String? = null,
    val email: String? = null,
    @SerializedName("employee_id")
    val employeeId: Long? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
)