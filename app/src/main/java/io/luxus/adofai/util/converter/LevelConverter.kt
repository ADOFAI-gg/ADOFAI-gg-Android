package io.luxus.adofai.util.converter

import javax.inject.Inject
import kotlin.math.floor

class LevelConverter @Inject constructor() {

    companion object {
        fun toDouble(level: String): Double {
            val idx = level.indexOf('+')
            return if (idx != -1) {
                level.substring(0, idx).toDouble() + 0.5
            } else {
                level.toDouble()
            }
        }

        fun toString(level: Double): String =
            "${level.toInt()}${if (floor(level) != level) "+" else ""}"
    }

}