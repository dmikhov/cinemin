package com.dmikhov.cinemin.screens.about

import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import com.dmikhov.cinemin.R
import com.dmikhov.cinemin.animation.BaseFragmentAnimationComposer
import com.dmikhov.cinemin.animation.LittleBounceInterpolator
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragmentAnimationComposer (fragmentView: View): BaseFragmentAnimationComposer(fragmentView) {
    override fun hide() = with(fragmentView) {
        val constraintSet = ConstraintSet()
        with(constraintSet) {
            clone(aboutConstraintLayout)

            clear(descriptionLayout.id, ConstraintSet.START)
            clear(descriptionLayout.id, ConstraintSet.END)
            clear(tmdbAttributionLayout.id, ConstraintSet.START)
            clear(tmdbAttributionLayout.id, ConstraintSet.END)
            clear(supportLayout.id, ConstraintSet.START)
            clear(supportLayout.id, ConstraintSet.END)
            clear(privacyPolicyLayout.id, ConstraintSet.START)
            clear(privacyPolicyLayout.id, ConstraintSet.END)

            connect(
                descriptionLayout.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                0
            )
            connect(
                tmdbAttributionLayout.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                0
            )
            connect(
                supportLayout.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                0
            )
            connect(
                privacyPolicyLayout.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                0
            )

            applyTo(aboutConstraintLayout)
        }
    }

    override fun show() = with(fragmentView) {
        val defaultPaddingDouble = resources.getDimension(R.dimen.default_padding_double).toInt()
        val constraintSet = ConstraintSet()
        with(constraintSet) {
            clone(aboutConstraintLayout)

            clear(descriptionLayout.id, ConstraintSet.START)
            clear(descriptionLayout.id, ConstraintSet.END)
            clear(tmdbAttributionLayout.id, ConstraintSet.START)
            clear(tmdbAttributionLayout.id, ConstraintSet.END)
            clear(supportLayout.id, ConstraintSet.START)
            clear(supportLayout.id, ConstraintSet.END)
            clear(privacyPolicyLayout.id, ConstraintSet.START)
            clear(privacyPolicyLayout.id, ConstraintSet.END)

            connect(
                descriptionLayout.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                defaultPaddingDouble
            )
            connect(
                descriptionLayout.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                defaultPaddingDouble
            )

            connect(
                tmdbAttributionLayout.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                defaultPaddingDouble
            )
            connect(
                tmdbAttributionLayout.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                defaultPaddingDouble
            )

            connect(
                supportLayout.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                defaultPaddingDouble
            )
            connect(
                supportLayout.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                defaultPaddingDouble
            )

            connect(
                privacyPolicyLayout.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                defaultPaddingDouble
            )
            connect(
                privacyPolicyLayout.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                defaultPaddingDouble
            )

            val transition = ChangeBounds()
            transition.interpolator = LittleBounceInterpolator()
            transition.duration = APPEARING_ANIMATION_DURATION

            TransitionManager.beginDelayedTransition(aboutConstraintLayout, transition)
            constraintSet.applyTo(aboutConstraintLayout)
        }
    }
}