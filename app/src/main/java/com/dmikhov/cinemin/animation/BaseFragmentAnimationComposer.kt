package com.dmikhov.cinemin.animation

import android.view.View

abstract class BaseFragmentAnimationComposer(protected val fragmentView: View) {
    abstract fun hide()

    abstract fun show()

    companion object {
        const val APPEARING_ANIMATION_DURATION = 700L
    }
}