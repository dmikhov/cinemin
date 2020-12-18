package com.dmikhov.cinemin.animation

import android.view.animation.Interpolator
import kotlin.math.cos
import kotlin.math.pow

internal class LittleBounceInterpolator : Interpolator {
    var mAmplitude = 0.15
    var mFrequency = 5.0

    constructor()

    constructor(amp: Double, freq: Double) {
        mAmplitude = amp
        mFrequency = freq
    }

    override fun getInterpolation(time: Float): Float {
        return (-1 * Math.E.pow(-time / mAmplitude) * cos(mFrequency * time) + 1).toFloat()
    }
}