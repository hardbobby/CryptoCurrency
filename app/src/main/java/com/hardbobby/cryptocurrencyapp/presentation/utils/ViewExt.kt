package com.hardbobby.cryptocurrencyapp.presentation.utils

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import com.hardbobby.cryptocurrencyapp.R

fun View.showToastMessage(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.showIf(condition : () -> Boolean) {
    if (condition.invoke()) setVisible()
    else setGone()
}

fun View.showSlidingIf(predicate: Boolean) {
    if (predicate) showSlideUp()
    else hideSlideDown()
}

fun View.showSlideUp() {
    if (this.visibility != View.VISIBLE) {
        setVisible()
        val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
        startAnimation(
            TranslateAnimation(
                0f,
                0f,
                height.toFloat() + bottomMargin.toFloat(),
                0f
            ).apply {
                duration = 500
            })
    }
}

fun View.hideSlideDown() {
    if (this.visibility == View.VISIBLE) {
        setGone()
        if (this.animation?.hasEnded().orTrue()) {
            val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
            startAnimation(
                TranslateAnimation(
                    0f,
                    0f,
                    0f,
                    height.toFloat() + bottomMargin.toFloat()
                ).apply {
                    duration = 500
                })
        } else {
            this.animation?.cancel()
        }
    }
}

inline fun View.setOnClickWithThrottle(
    threshold: Long = 1000L,
    crossinline action: (v: View) -> Unit
) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < threshold) return
            else action(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun TextInputLayout.getText(): String {
    return this.editText?.text.toString()
}

fun TextView.changeTextColor(
    predicate1: () -> Boolean = { true },
    predicate2: () -> Boolean = { true },
) {
    if (predicate1()) {
        this.setTextColor(ContextCompat.getColor(this.context, R.color.red_700))
    } else if (predicate2()) {
        this.setTextColor(ContextCompat.getColor(this.context, R.color.green_jade))
    }
}
