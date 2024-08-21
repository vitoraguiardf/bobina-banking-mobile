package com.vitoraguiardf.bobinabanking.data.rest

data class CustomResponse<Entity>(val message:String, val data: Entity)