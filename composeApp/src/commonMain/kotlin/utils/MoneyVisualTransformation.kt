package utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class MoneyVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilter(text)
    }

    private fun maskFilter(text: AnnotatedString): TransformedText {
        val trimmedText = text.text.trim()

        val decimalPart = when {
            trimmedText.length > 2 -> trimmedText.takeLast(2)
            trimmedText.length == 1 -> "0$trimmedText"
            else -> trimmedText
        }

        val integerPart = when {
            trimmedText.length > 2 -> formatIntegerPart(trimmedText.dropLast(2))
            else -> "0"
        }

        val formattedText = if (trimmedText.isNotEmpty()) {
            "$integerPart$DECIMAL_SEPARATOR$decimalPart"
        } else {
            decimalPart
        }

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset == 0) return offset
                if (offset <= 3) return 4

                var transformedOffset = offset

                for (i in 3..offset) {
                    if (i % 3 == 0 && i <= offset) {
                        transformedOffset++
                    }
                }

                return transformedOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 4) {
                    return decimalPart.toIntOrNull()?.toString()?.length ?: 0
                }

                var originalOffset = offset

                for (i in 4..offset) {
                    if ((i + 1) % 3 == 0) {
                        originalOffset--
                    }
                }

                return originalOffset
            }
        }

        return TransformedText(AnnotatedString(formattedText), numberOffsetTranslator)
    }

    private fun formatIntegerPart(integerPart: String): String {
        val reversed = integerPart.reversed()
        var builder = ""
        var counter = 0
        for (c in reversed) {
            builder += c
            counter++
            if (counter % 3 == 0 && counter < reversed.length) {
                builder += THOUSAND_SEPARATOR
            }
        }
        return builder.reversed()
    }

    companion object {
        private const val THOUSAND_SEPARATOR = " "
        private const val DECIMAL_SEPARATOR = "."
    }
}
