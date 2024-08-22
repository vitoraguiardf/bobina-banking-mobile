package com.vitoraguiardf.bobinabanking.data.rest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.io.IOException

abstract class AbstractRestRepository {
    suspend fun <ResultType> execute(request: Call<ResultType>): Result<ResultType> {
        return withContext(Dispatchers.IO) {
            try {
                val response = request.execute()
                if (response.errorBody() != null) {
                    Result.failure(Throwable("code: ${response.code()}, body: ${response.errorBody()!!.string()}"))
                } else {
                    Result.success(response.body()!!)
                }
            } catch (ex: IOException) {
                Result.failure(ex)
            }
        }
    }

}