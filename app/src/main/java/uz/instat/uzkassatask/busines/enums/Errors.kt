package uz.instat.uzkassatask.busines.enums


enum class Errors(val code: Int) {
    NO_CONNECTION(-3),
    SERVER_ERROR(-2),
    EMPTY_DATA(-1),
    NETWORK_TIMEOUT_ERROR(408),
    UNKNOWN_ERROR(55000);

    companion object {
        val Int?.error
            get() = values().find { it.code == this } ?: NETWORK_TIMEOUT_ERROR

        val Errors?.message: String?
            get() = this?.let { getStringArray(R.array.errors)[ordinal] }
    }
}