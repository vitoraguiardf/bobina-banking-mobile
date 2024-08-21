package com.vitoraguiardf.bobinabanking.utils.logger

import java.util.Date

data class Log(
    val at: Date,
    val message: String,

) {
    constructor(message: String): this(Date(), message)
    override fun toString(): String {
        return this.message
    }
}
