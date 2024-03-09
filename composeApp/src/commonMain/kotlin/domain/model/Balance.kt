package domain.model


data class Balance(val value: Long) {

    init {
        require(value >= 0) { "Value can't be negative" }
    }

    companion object {
        fun fromString(value: String): Balance {
            val amount = value.ifEmpty { "0" }
                .replace(" ", "")
                .toDouble().times(100)
                .toLong()
            return Balance(amount)
        }
    }

    operator fun plus(other: Long): Balance {
        val newValue = value + other
        return Balance(newValue)
    }

    operator fun minus(other: Long): Balance {
        var newValue = value - other
        if (newValue < 0) newValue = 0
        return Balance(newValue)
    }

    override fun toString(): String {
        val valueString = value.toString()
        if (valueString.length > 2) {
            val integerPart = valueString.substring(0, valueString.length - 2).reversed().chunked(3).reversed()
                .joinToString(" ") { it.reversed() }

            val decimalPart = valueString.substring(valueString.length - 2)
            return "$integerPart.$decimalPart"
        }

        return "0.${valueString.padEnd(2, '0')}"
    }
}
