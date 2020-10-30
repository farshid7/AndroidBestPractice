package com.example.androidbestpractice.view.calenderView.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.androidbestpractice.R

class DateItemCustomView : LinearLayout {
    private lateinit var textView: TextView

    constructor(context: Context, text: String, clickDelegate: ((Unit) -> Unit)) : super(context) {
        init(context, text,clickDelegate)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private fun init(context: Context, text: String, clickDelegate: (Unit) -> Unit) {
        orientation = VERTICAL
        setOnClickListener {
            clickDelegate.invoke(Unit)
        }
        layoutParams = LayoutParams(
            context.resources.getDimensionPixelOffset(R.dimen.my_size_),
            context.resources.getDimensionPixelOffset(R.dimen.my_size_)
        )
        val lay = LinearLayout(context).apply {
            orientation = HORIZONTAL
            addView(FrameLayout(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    context.resources.getDimensionPixelOffset(R.dimen.my_size),
                    context.resources.getDimensionPixelOffset(R.dimen.my_size)
                )
                textView = TextView(context).apply {
                    this.text = text
                    gravity = Gravity.CENTER
                }

                addView(textView)
            })
        }

        lay.addView(View(context).apply {
            layoutParams = LayoutParams(
                context.resources.getDimensionPixelOffset(R.dimen.my_divider),
                LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        })

        addView(lay)

        addView(View(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                context.resources.getDimensionPixelOffset(R.dimen.my_divider)
            )
            setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        })
    }
}