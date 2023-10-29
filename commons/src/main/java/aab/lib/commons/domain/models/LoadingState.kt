package aab.lib.commons.domain.models

data class LoadingState<T> (
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    var result: T? = null,
    var totals: Double = 0.0,
    var error: Resource.Error? = null,
)