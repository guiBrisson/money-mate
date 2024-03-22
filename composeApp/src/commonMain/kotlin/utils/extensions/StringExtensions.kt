package utils

fun String.canBeLong() : Boolean {
    return this.toLongOrNull() != null
}
