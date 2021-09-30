package uz.instat.uzkassatask.busines.interactors


data class DataState<T>(
    var data: T? = null,
    var code: Int? = null
) {
    companion object {

        fun <T> error(
            code: Int
        ): DataState<T> {
            return DataState(
                code = code,
                data = null
            )
        }

        fun <T> data(
            data: T? = null
        ): DataState<T> {
            return DataState(
                data = data,
                code = 0
            )
        }
    }
}