package com.ffgreatking.progressbar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.*
import androidx.customview.view.AbsSavedState
import com.ffgreatking.progressbar.common.AnimatedRoundCornerProgressBar
import io.core.widget.roundcornerprogressbar.R

@Suppress("unused", "MemberVisibilityCanBePrivate")
@Keep
open class TextRoundCornerProgressBar : AnimatedRoundCornerProgressBar,
    ViewTreeObserver.OnGlobalLayoutListener {
    companion object {
        protected const val DEFAULT_TEXT_SIZE = 16
        protected const val DEFAULT_TEXT_MARGIN = 10
        const val GRAVITY_START = 0
        const val GRAVITY_END = 1
        const val PRIORITY_INSIDE = 0
        const val PRIORITY_OUTSIDE = 1
    }

    private lateinit var tvProgress: TextView

    private var colorTextProgress = 0
    private var colorTextProgressOutside = 0
    private var textProgressSize = 0
    private var textProgressMargin = 0
    private var textProgress: String? = null
    private var textInsideGravity = 0
    private var textOutsideGravity = 0
    private var textPositionPriority = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun initLayout(): Int = R.layout.layout_text_round_corner_progress_bar

    override fun initStyleable(context: Context, attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.TextRoundCornerProgressBar)

        with(typedArray) {
            colorTextProgress = getColor(
                R.styleable.TextRoundCornerProgressBar_fyTextProgressColor, Color.WHITE
            )
            colorTextProgressOutside = getColor(
                R.styleable.TextRoundCornerProgressBar_fyTextProgressColorOutside, colorTextProgress
            )

            textProgressSize = getDimension(
                R.styleable.TextRoundCornerProgressBar_fyTextProgressSize,
                dp2px(DEFAULT_TEXT_SIZE.toFloat())
            ).toInt()
            textProgressMargin = getDimension(
                R.styleable.TextRoundCornerProgressBar_fyTextProgressMargin,
                dp2px(DEFAULT_TEXT_MARGIN.toFloat())
            ).toInt()

            textProgress = getString(R.styleable.TextRoundCornerProgressBar_fyTextProgress)

            textInsideGravity = getInt(
                R.styleable.TextRoundCornerProgressBar_fyTextInsideGravity, GRAVITY_START
            )
            textOutsideGravity = getInt(
                R.styleable.TextRoundCornerProgressBar_fyTextOutsideGravity, GRAVITY_START
            )
            textPositionPriority = getInt(
                R.styleable.TextRoundCornerProgressBar_fyTextPositionPriority,
                PRIORITY_INSIDE
            )
            recycle()
        }
    }

    override fun initView() {
        tvProgress = findViewById(R.id.tv_progress)
        tvProgress.viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    override fun drawProgress(
        layoutProgress: LinearLayout,
        progressDrawable: GradientDrawable,
        max: Float,
        progress: Float,
        totalWidth: Float,
        radius: Int,
        padding: Int,
        isReverse: Boolean,
    ) {
        val newRadius = radius - (padding / 2f)
        progressDrawable.cornerRadii = floatArrayOf(
            newRadius,
            newRadius,
            newRadius,
            newRadius,
            newRadius,
            newRadius,
            newRadius,
            newRadius,
        )
        layoutProgress.background = progressDrawable

        val ratio = max / progress
        val progressWidth = ((totalWidth - (padding * 2)) / ratio).toInt()
        val progressParams = layoutProgress.layoutParams as MarginLayoutParams
        if (padding + (progressWidth / 2) < radius) {
            val margin = (radius - padding).coerceAtLeast(0) - (progressWidth / 2)
            progressParams.topMargin = margin
            progressParams.bottomMargin = margin
        } else {
            progressParams.topMargin = 0
            progressParams.bottomMargin = 0
        }
        progressParams.width = progressWidth
        layoutProgress.layoutParams = progressParams
    }

    override fun onViewDraw() {
        drawTextProgress()
        drawTextProgressSize()
        drawTextProgressMargin()
        // Can't instantly change the text position of child view
        // when `onSizeChanged(...)` called. Using `post` method then
        // call these methods inside the Runnable will solved this.
        // Can't instantly change the text position of child view
        // when `onSizeChanged(...)` called. Using `post` method then
        // call these methods inside the Runnable will solved this.
        post { drawTextProgressPosition() }
        drawTextProgressColor()
    }

    private fun drawTextProgress() {
        tvProgress.text = textProgress
    }

    private fun drawTextProgressColor() {
        tvProgress.setTextColor(colorTextProgress)
    }

    private fun drawTextProgressSize() {
        tvProgress.setTextSize(TypedValue.COMPLEX_UNIT_PX, textProgressSize.toFloat())
    }

    private fun drawTextProgressMargin() {
        val params = tvProgress.layoutParams as MarginLayoutParams
        params.setMargins(textProgressMargin, 0, textProgressMargin, 0)
        tvProgress.layoutParams = params
    }

    private fun drawTextProgressPosition() {
        clearTextProgressAlign()
        val textProgressWidth: Int = tvProgress.measuredWidth + getTextProgressMargin() * 2
        val ratio = getMax() / getProgress()
        val progressWidth = ((getLayoutWidth() - (getPadding() * 2)) / ratio).toInt()
        if (textPositionPriority == PRIORITY_OUTSIDE) {
            if (getLayoutWidth() - progressWidth > textProgressWidth) {
                alignTextProgressOutsideProgress()
            } else {
                alignTextProgressInsideProgress()
            }
        } else {
            if (textProgressWidth + textProgressMargin > progressWidth) {
                alignTextProgressOutsideProgress()
                tvProgress.setTextColor(colorTextProgressOutside)
            } else {
                alignTextProgressInsideProgress()
                tvProgress.setTextColor(colorTextProgress)
            }
        }
    }

    private fun clearTextProgressAlign() {
        val params = tvProgress.layoutParams as RelativeLayout.LayoutParams
        with(params) {
            removeRule(RelativeLayout.LEFT_OF)
            removeRule(RelativeLayout.RIGHT_OF)
            removeRule(RelativeLayout.ALIGN_LEFT)
            removeRule(RelativeLayout.ALIGN_RIGHT)
            removeRule(RelativeLayout.ALIGN_PARENT_LEFT)
            removeRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            removeRule(RelativeLayout.START_OF)
            removeRule(RelativeLayout.END_OF)
            removeRule(RelativeLayout.ALIGN_START)
            removeRule(RelativeLayout.ALIGN_END)
            removeRule(RelativeLayout.ALIGN_PARENT_START)
            removeRule(RelativeLayout.ALIGN_PARENT_END)

            tvProgress.layoutParams = this
        }
    }

    private fun alignTextProgressInsideProgress() {
        val params = tvProgress.layoutParams as RelativeLayout.LayoutParams

        with(params) {
            if (isReverse()) {
                if (textInsideGravity == GRAVITY_END) {
                    addRule(RelativeLayout.ALIGN_RIGHT, R.id.layout_progress)
                    addRule(RelativeLayout.ALIGN_END, R.id.layout_progress)
                } else {
                    addRule(RelativeLayout.ALIGN_LEFT, R.id.layout_progress)
                    addRule(RelativeLayout.ALIGN_START, R.id.layout_progress)
                }
            } else {
                if (textInsideGravity == GRAVITY_END) {
                    addRule(RelativeLayout.ALIGN_LEFT, R.id.layout_progress)
                    addRule(RelativeLayout.ALIGN_START, R.id.layout_progress)
                } else {
                    addRule(RelativeLayout.ALIGN_RIGHT, R.id.layout_progress)
                    addRule(RelativeLayout.ALIGN_END, R.id.layout_progress)
                }
            }

            tvProgress.layoutParams = this
        }
    }

    private fun alignTextProgressOutsideProgress() {
        val params = tvProgress.layoutParams as RelativeLayout.LayoutParams

        with(params) {
            if (isReverse()) {
                if (textOutsideGravity == GRAVITY_END) {
                    addRule(RelativeLayout.ALIGN_PARENT_LEFT)
                    addRule(RelativeLayout.ALIGN_PARENT_START)
                } else {
                    addRule(RelativeLayout.LEFT_OF, R.id.layout_progress)
                    addRule(RelativeLayout.START_OF, R.id.layout_progress)
                }
            } else {
                if (textOutsideGravity == GRAVITY_END) {
                    addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                    addRule(RelativeLayout.ALIGN_PARENT_END)
                } else {
                    addRule(RelativeLayout.RIGHT_OF, R.id.layout_progress)
                    addRule(RelativeLayout.END_OF, R.id.layout_progress)
                }
            }
            tvProgress.layoutParams = this
        }
    }

    fun getProgressText(): String? = textProgress

    fun setProgressText(text: String) {
        textProgress = text
        drawTextProgress()
        drawTextProgressPosition()
    }

    override fun setProgress(progress: Int) {
        setProgress(progress.toFloat())
    }

    override fun setProgress(progress: Float) {
        super.setProgress(progress)
        drawTextProgressPosition()
    }

    fun getTextProgressColor(): Int = colorTextProgress

    fun setTextProgressColor(@ColorInt color: Int) {
        colorTextProgress = color
        drawTextProgressColor()
    }

    fun getTextProgressSize(): Int = textProgressSize

    fun setTextProgressSize(@Px size: Int) {
        textProgressSize = size
        drawTextProgressSize()
        drawTextProgressPosition()
    }

    fun getTextProgressMargin(): Int = textProgressMargin

    fun setTextProgressMargin(@Px margin: Int) {
        textProgressMargin = margin
        drawTextProgressMargin()
        drawTextProgressPosition()
    }

    @TextPositionPriority
    fun getTextPositionPriority(): Int = textPositionPriority

    fun setTextPositionPriority(@TextPositionPriority priority: Int) {
        textPositionPriority = priority
        drawTextProgressPosition()
    }

    @TextProgressGravity
    fun getTextInsideGravity(): Int = textInsideGravity

    fun setTextInsideGravity(@TextProgressGravity gravity: Int) {
        textInsideGravity = gravity
        drawTextProgressPosition()
    }

    @TextProgressGravity
    fun getTextOutsideGravity(): Int = textOutsideGravity

    fun setTextOutsideGravity(@TextProgressGravity gravity: Int) {
        textOutsideGravity = gravity
        drawTextProgressPosition()
    }

    override fun onGlobalLayout() {
        tvProgress.viewTreeObserver.removeOnGlobalLayoutListener(this)
        drawTextProgressPosition()
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(GRAVITY_START, GRAVITY_END)
    annotation class TextProgressGravity

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(PRIORITY_INSIDE, PRIORITY_OUTSIDE)
    annotation class TextPositionPriority

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState() ?: return null
        val state = SavedState(superState)
        state.colorTextProgress = this.colorTextProgress
        state.textProgressSize = this.textProgressSize
        state.textProgressMargin = this.textProgressMargin

        state.textProgress = this.textProgress

        state.textInsideGravity = this.textInsideGravity
        state.textOutsideGravity = this.textOutsideGravity
        state.textPositionPriority = this.textPositionPriority
        return state
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state !is SavedState) {
            super.onRestoreInstanceState(state)
            return
        }
        super.onRestoreInstanceState(state.superState)

        colorTextProgress = state.colorTextProgress
        textProgressSize = state.textProgressSize
        textProgressMargin = state.textProgressMargin

        textProgress = state.textProgress

        textInsideGravity = state.textInsideGravity
        textOutsideGravity = state.textOutsideGravity
        textPositionPriority = state.textPositionPriority
    }

    protected class SavedState : AbsSavedState {
        var colorTextProgress = 0
        var textProgressSize = 0
        var textProgressMargin = 0

        var textProgress: String? = null

        var textInsideGravity = 0
        var textOutsideGravity = 0
        var textPositionPriority = 0

        constructor(superState: Parcelable) : super(superState)

        constructor(source: Parcel) : super(source, null)

        constructor(source: Parcel, loader: ClassLoader? = null) : super(source, loader) {
            with(source) {
                colorTextProgress = readInt()
                textProgressSize = readInt()
                textProgressMargin = readInt()

                textProgress = readString()

                textInsideGravity = readInt()
                textOutsideGravity = readInt()
                textPositionPriority = readInt()
            }
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            super.writeToParcel(dest, flags)
            with(dest) {
                writeInt(colorTextProgress)
                writeInt(textProgressSize)
                writeInt(textProgressMargin)

                writeString(textProgress)

                writeInt(textInsideGravity)
                writeInt(textOutsideGravity)
                writeInt(textPositionPriority)
            }
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.ClassLoaderCreator<SavedState> =
                object : Parcelable.ClassLoaderCreator<SavedState> {
                    override fun createFromParcel(source: Parcel, loader: ClassLoader): SavedState =
                        SavedState(source, loader)

                    override fun createFromParcel(source: Parcel): SavedState = SavedState(source)

                    override fun newArray(size: Int): Array<SavedState> = newArray(size)
                }
        }
    }
}
