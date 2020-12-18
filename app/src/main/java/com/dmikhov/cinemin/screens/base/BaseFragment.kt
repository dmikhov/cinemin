package com.dmikhov.cinemin.screens.base

import androidx.fragment.app.Fragment
import com.dmikhov.cinemin.screens.MainActivity

abstract class BaseFragment: Fragment() {
    protected val mainActivity get() = activity as? MainActivity
}