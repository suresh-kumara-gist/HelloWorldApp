package com.helloworld.app.utils

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AccelerateDecelerateInterpolator

object AnimationUtils {
    
    fun fadeInAnimation(view: View, duration: Long = 300) {
        val fadeIn = AlphaAnimation(0f, 1f).apply {
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator()
        }
        view.startAnimation(fadeIn)
        view.visibility = View.VISIBLE
    }
}