package com.dmikhov.cinemabudget.screens.base

import androidx.fragment.app.Fragment
import com.dmikhov.cinemabudget.screens.MainActivity

abstract class BaseFragment: Fragment() {
    protected val mainActivity get() = activity as? MainActivity
}