package com.dpashko.transitionapp.extension

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes

fun View.playAnimation(
    @AnimRes animResId: Int,
    onAnimationRepeat: () -> Unit = {},
    onAnimationStart: () -> Unit = {},
    onAnimationEnd: () -> Unit = {}
) = with(AnimationUtils.loadAnimation(context, animResId)) {
    setAnimationListener(object : Animation.AnimationListener {

        override fun onAnimationRepeat(animation: Animation?) {
            onAnimationRepeat.invoke()
        }

        override fun onAnimationEnd(animation: Animation?) {
            onAnimationEnd.invoke()
        }

        override fun onAnimationStart(animation: Animation?) {
            onAnimationStart.invoke()
        }
    })
    startAnimation(this)
}