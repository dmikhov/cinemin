package com.dmikhov.cinemin.screens.movie

import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import com.dmikhov.cinemin.R
import com.dmikhov.cinemin.animation.LittleBounceInterpolator
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MovieFragmentAnimationComposer(
    private val fragmentView: View,
) {
    fun hideMovieDetails() = with(fragmentView) {
        val constraintSet = ConstraintSet()
        with(constraintSet) {
            clone(movieConstraintLayout)

            clear(posterCardView.id, ConstraintSet.START)
            clear(posterCardView.id, ConstraintSet.END)
            clear(movieDetailsLayout.id, ConstraintSet.START)
            clear(movieDetailsLayout.id, ConstraintSet.END)
            clear(moneyDetailsCardView.id, ConstraintSet.START)
            clear(moneyDetailsCardView.id, ConstraintSet.END)
            clear(weightedMoneyDetailsCardView.id, ConstraintSet.START)
            clear(weightedMoneyDetailsCardView.id, ConstraintSet.END)
            clear(descriptionCardView.id, ConstraintSet.START)
            clear(descriptionCardView.id, ConstraintSet.END)
            clear(moneyDetailsOverlayView.id, ConstraintSet.START)
            clear(moneyDetailsOverlayView.id, ConstraintSet.END)
            clear(weightedMoneyDetailsOverlayView.id, ConstraintSet.START)
            clear(weightedMoneyDetailsOverlayView.id, ConstraintSet.END)

            connect(
                posterCardView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                0
            )
            connect(
                moneyDetailsCardView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                0
            )
            connect(
                weightedMoneyDetailsCardView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                0
            )
            connect(
                moneyDetailsOverlayView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                0
            )
            connect(
                weightedMoneyDetailsOverlayView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                0
            )
            connect(
                movieDetailsLayout.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                0
            )
            connect(
                descriptionCardView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                0
            )

            applyTo(movieConstraintLayout)
        }
    }

    fun animateMovieDetailsAppearing() = with(fragmentView) {
        val defaultPadding = resources.getDimension(R.dimen.default_padding).toInt()

        val constraintSet = ConstraintSet()
        with(constraintSet) {
            clone(movieConstraintLayout)

            clear(posterCardView.id, ConstraintSet.START)
            clear(posterCardView.id, ConstraintSet.END)
            clear(movieDetailsLayout.id, ConstraintSet.START)
            clear(movieDetailsLayout.id, ConstraintSet.END)
            clear(moneyDetailsCardView.id, ConstraintSet.START)
            clear(moneyDetailsCardView.id, ConstraintSet.END)
            clear(weightedMoneyDetailsCardView.id, ConstraintSet.START)
            clear(weightedMoneyDetailsCardView.id, ConstraintSet.END)
            clear(descriptionCardView.id, ConstraintSet.START)
            clear(descriptionCardView.id, ConstraintSet.END)
            clear(moneyDetailsOverlayView.id, ConstraintSet.START)
            clear(moneyDetailsOverlayView.id, ConstraintSet.END)
            clear(weightedMoneyDetailsOverlayView.id, ConstraintSet.START)
            clear(weightedMoneyDetailsOverlayView.id, ConstraintSet.END)

            connect(
                posterCardView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                defaultPadding
            )

            connect(
                moneyDetailsCardView.id,
                ConstraintSet.START,
                posterCardView.id,
                ConstraintSet.END,
                defaultPadding
            )
            connect(
                moneyDetailsCardView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                defaultPadding
            )

            connect(
                weightedMoneyDetailsCardView.id,
                ConstraintSet.START,
                posterCardView.id,
                ConstraintSet.END,
                defaultPadding
            )
            connect(
                weightedMoneyDetailsCardView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                defaultPadding
            )

            connect(
                moneyDetailsOverlayView.id,
                ConstraintSet.START,
                moneyDetailsCardView.id,
                ConstraintSet.START,
                0
            )
            connect(
                moneyDetailsOverlayView.id,
                ConstraintSet.END,
                moneyDetailsCardView.id,
                ConstraintSet.END,
                0
            )

            connect(
                weightedMoneyDetailsOverlayView.id,
                ConstraintSet.START,
                weightedMoneyDetailsCardView.id,
                ConstraintSet.START,
                0
            )
            connect(
                weightedMoneyDetailsOverlayView.id,
                ConstraintSet.END,
                weightedMoneyDetailsCardView.id,
                ConstraintSet.END,
                0
            )

            connect(
                movieDetailsLayout.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                defaultPadding
            )
            connect(
                movieDetailsLayout.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                defaultPadding
            )

            connect(
                descriptionCardView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                defaultPadding
            )
            connect(
                descriptionCardView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                defaultPadding
            )
        }

        val transition = ChangeBounds()
        transition.interpolator = LittleBounceInterpolator()
        transition.duration = APPEARING_ANIMATION_DURATION

        TransitionManager.beginDelayedTransition(movieConstraintLayout, transition)
        constraintSet.applyTo(movieConstraintLayout)
    }

    companion object {
        private const val APPEARING_ANIMATION_DURATION = 700L
    }
}