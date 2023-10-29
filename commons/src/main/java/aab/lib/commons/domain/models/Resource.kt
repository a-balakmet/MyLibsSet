package aab.lib.commons.domain.models

sealed class Resource<out T> {
    class Success<out T>(val data: T) : Resource<T>()
    data class Error(
        val message: String,
        val code: Any,
    ) : Resource<Nothing>()

    data class Progress(val totals: Double) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
    data object LoadingMore : Resource<Nothing>()
}
