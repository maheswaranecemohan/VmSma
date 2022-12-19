package com.vm.smacompose.network
import com.vm.smacompose.domain.data.DataState
import com.vm.smacompose.domain.data.UIComponent
import retrofit2.HttpException
import java.io.IOException

fun <T> handleUseCaseException(throwable: Throwable): DataState.Response<T> {
    throwable.printStackTrace()
    when(throwable){
        is IOException ->   return DataState.Response(
            uiComponent = UIComponent.Dialog(
                title = "Network Error",
                description = throwable.message?: "Unknown error"
            )
        )
        is HttpException -> {
            val code = throwable.code()
            val errorResponse = convertErrorBody(throwable)
            return DataState.Response(
                uiComponent = UIComponent.Dialog(
                    title = "Network Error",
                    description = throwable.message?: "Unknown error"
                )
            )
        }
        else -> {
            return DataState.Response(
                uiComponent = UIComponent.Dialog(
                    title = "Error",
                    description = throwable.message?: "Unknown error"
                )
            )
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        //ErrorHandling.UNKNOWN_ERROR
        "Unknown Error"
    }
}