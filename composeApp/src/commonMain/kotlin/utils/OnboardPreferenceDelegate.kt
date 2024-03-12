package utils

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class OnboardPreferenceDelegate: ReadWriteProperty<Any?, Boolean> {
    private val settings: Settings = Settings()

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return settings.getBoolean(KEY, DEFAULT_VALUE)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        settings[KEY] = value
    }

    companion object {
        private const val KEY = "onboardDone"
        private const val DEFAULT_VALUE = false
    }

}

fun onboardPreference() = OnboardPreferenceDelegate()
