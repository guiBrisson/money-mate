package utils.extensions

fun String.canBeLong() : Boolean {
    return this.toLongOrNull() != null
}
