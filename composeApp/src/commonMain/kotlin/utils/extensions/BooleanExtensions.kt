package utils.extensions

fun Boolean.toLong(): Long =
    if (this.not()) 0 else 1