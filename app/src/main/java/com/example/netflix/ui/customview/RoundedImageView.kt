package com.example.netflix.ui.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.example.netflix.R

class RoundedImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    private val path = Path()

    private val mTopRightCornerRadius: Float
    private val mTopLeftCornerRadius: Float
    private val mBottomRightCornerRadius: Float
    private val mBottomClipRadius: Float

    init {
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.RoundedImageView, defStyleAttr, 0
        ).apply {
            try {
                mTopRightCornerRadius =
                    getDimensionPixelSize(
                        R.styleable.RoundedImageView_topRightCornerRadius,
                        0
                    ).toFloat()
                mTopLeftCornerRadius =
                    getDimensionPixelSize(
                        R.styleable.RoundedImageView_topLeftCornerRadius,
                        0
                    ).toFloat()
                mBottomRightCornerRadius =
                    getDimensionPixelSize(
                        R.styleable.RoundedImageView_bottomRightCornerRadius,
                        0
                    ).toFloat()
                mBottomClipRadius =
                    getDimensionPixelSize(
                        R.styleable.RoundedImageView_bottomClipRadius,
                        0
                    ).toFloat()
            } finally {
                recycle()
            }
        }
    }

    private val radiusArray = floatArrayOf(
        mTopLeftCornerRadius, mTopLeftCornerRadius,
        mTopRightCornerRadius, mTopRightCornerRadius,
        mBottomRightCornerRadius, mBottomRightCornerRadius / 3,
        mBottomClipRadius, mBottomClipRadius / 3
    )

    private var roundRect: RectF? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        with(path) {
            moveTo(0f, 0f)
            lineTo(0f, height - mBottomClipRadius)
            quadTo(
                width / 2f,height + mBottomClipRadius,
                w.toFloat(),h - mBottomClipRadius
            )
            lineTo(w.toFloat(), 0f)
            lineTo(0f, 0f)
            close()
        }
        roundRect = RectF(0f, 0f, w.toFloat(), h.toFloat())
    }


    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return

//        path.addRoundRect(
//            roundRect ?: return, radiusArray, Path.Direction.CW
//        )
        canvas.clipPath(path)
        super.onDraw(canvas)

    }
}