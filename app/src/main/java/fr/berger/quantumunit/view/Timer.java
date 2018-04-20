package fr.berger.quantumunit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fr.berger.quantumunit.R;

/**
 * TODO: document your custom view class.
 */
public class Timer extends AppCompatEditText {
	private String mExampleString; // TODO: use a default from R.string...
	private int mExampleColor = Color.RED; // TODO: use a default from R.color...
	private float mExampleDimension = 0; // TODO: use a default from R.dimen...
	private Drawable mExampleDrawable;
	
	private int lineColor;
	private int typeTextColor;
	private int untypedTextColor;
	private int unitColor;
	private double seconds;
	
	private TextPaint mTextPaint;
	private float mTextWidth;
	private float mTextHeight;
	
	public Timer(@NotNull Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}
	public Timer(@NotNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
		/*super(context, attrs);
		init(attrs, 0);*/
	}
	public Timer(@NotNull Context context) {
		this(context, null, 0);
		/*super(context);
		init(null, 0);*/
	}
	
	private void init(@Nullable AttributeSet attrs, int defStyle) {
		// Load attributes
		final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Timer, defStyle, 0);
		
		/* EXAMPLE */
		mExampleString = a.getString(R.styleable.Timer_exampleString);
		mExampleColor = a.getColor(R.styleable.Timer_exampleColor, mExampleColor);
		// Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
		// values that should fall on pixel boundaries.
		mExampleDimension = a.getDimension(
				R.styleable.Timer_exampleDimension,
				mExampleDimension);
		
		if (a.hasValue(R.styleable.Timer_exampleDrawable)) {
			mExampleDrawable = a.getDrawable(
					R.styleable.Timer_exampleDrawable);
			if (mExampleDrawable != null)
				mExampleDrawable.setCallback(this);
		}
		/* TIMER ATTRIBUTES */
		setLineColor(a.getColor(R.styleable.Timer_lineColor, ContextCompat.getColor(getContext(), android.R.color.darker_gray)));
		setTypeTextColor(a.getColor(R.styleable.Timer_typeTextColor, ContextCompat.getColor(getContext(), android.R.color.black)));
		setUntypedTextColor(a.getColor(R.styleable.Timer_untypedTextColor, ContextCompat.getColor(getContext(), android.R.color.darker_gray)));
		setUnitColor(a.getColor(R.styleable.Timer_unitColor, ContextCompat.getColor(getContext(), android.R.color.black)));
		setSeconds((double) a.getFloat(R.styleable.Timer_lineColor, 0f));
		
		a.recycle();
		
		// Set up a default TextPaint object
		mTextPaint = new TextPaint();
		mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setTextAlign(Paint.Align.LEFT);
		
		// Update TextPaint and text measurements from attributes
		invalidateTextPaintAndMeasurements();
	}
	
	private void invalidateTextPaintAndMeasurements() {
		mTextPaint.setTextSize(mExampleDimension);
		mTextPaint.setColor(mExampleColor);
		mTextWidth = mTextPaint.measureText(mExampleString);
		
		Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
		mTextHeight = fontMetrics.bottom;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// TODO: consider storing these as member variables to reduce
		// allocations per draw cycle.
		/*
		int paddingLeft = getPaddingLeft();
		int paddingTop = getPaddingTop();
		int paddingRight = getPaddingRight();
		int paddingBottom = getPaddingBottom();
		
		int contentWidth = getWidth() - paddingLeft - paddingRight;
		int contentHeight = getHeight() - paddingTop - paddingBottom;
		
		// Draw the text.
		canvas.drawText(mExampleString,
				paddingLeft + (contentWidth - mTextWidth) / 2,
				paddingTop + (contentHeight + mTextHeight) / 2,
				mTextPaint);
		
		// Draw the example drawable on top of the text.
		if (mExampleDrawable != null) {
			mExampleDrawable.setBounds(paddingLeft, paddingTop,
					paddingLeft + contentWidth, paddingTop + contentHeight);
			mExampleDrawable.draw(canvas);
		}*/
	}
	
	/* GETTERS & SETTERS */
	
	// EXAMPLE
	/**
	 * Gets the example string attribute value.
	 *
	 * @return The example string attribute value.
	 */
	public String getExampleString() {
		return mExampleString;
	}
	
	/**
	 * Sets the view's example string attribute value. In the example view, this string
	 * is the text to draw.
	 *
	 * @param exampleString The example string attribute value to use.
	 */
	public void setExampleString(String exampleString) {
		mExampleString = exampleString;
		invalidateTextPaintAndMeasurements();
	}
	
	/**
	 * Gets the example color attribute value.
	 *
	 * @return The example color attribute value.
	 */
	public int getExampleColor() {
		return mExampleColor;
	}
	
	/**
	 * Sets the view's example color attribute value. In the example view, this color
	 * is the font color.
	 *
	 * @param exampleColor The example color attribute value to use.
	 */
	public void setExampleColor(int exampleColor) {
		mExampleColor = exampleColor;
		invalidateTextPaintAndMeasurements();
	}
	
	/**
	 * Gets the example dimension attribute value.
	 *
	 * @return The example dimension attribute value.
	 */
	public float getExampleDimension() {
		return mExampleDimension;
	}
	
	/**
	 * Sets the view's example dimension attribute value. In the example view, this dimension
	 * is the font size.
	 *
	 * @param exampleDimension The example dimension attribute value to use.
	 */
	public void setExampleDimension(float exampleDimension) {
		mExampleDimension = exampleDimension;
		invalidateTextPaintAndMeasurements();
	}
	
	/**
	 * Gets the example drawable attribute value.
	 *
	 * @return The example drawable attribute value.
	 */
	public Drawable getExampleDrawable() {
		return mExampleDrawable;
	}
	
	/**
	 * Sets the view's example drawable attribute value. In the example view, this drawable is
	 * drawn above the text.
	 *
	 * @param exampleDrawable The example drawable attribute value to use.
	 */
	public void setExampleDrawable(Drawable exampleDrawable) {
		mExampleDrawable = exampleDrawable;
	}
	
	// TIMER
	
	public int getLineColor() {
		return lineColor;
	}
	
	public void setLineColor(int lineColor) {
		this.lineColor = lineColor;
	}
	
	public int getTypeTextColor() {
		return typeTextColor;
	}
	
	public void setTypeTextColor(int typeTextColor) {
		this.typeTextColor = typeTextColor;
	}
	
	public int getUntypedTextColor() {
		return untypedTextColor;
	}
	
	public void setUntypedTextColor(int untypedTextColor) {
		this.untypedTextColor = untypedTextColor;
	}
	
	public int getUnitColor() {
		return unitColor;
	}
	
	public void setUnitColor(int unitColor) {
		this.unitColor = unitColor;
	}
	
	public double getSeconds() {
		return seconds;
	}
	
	public void setSeconds(double seconds) {
		this.seconds = seconds;
	}
}
