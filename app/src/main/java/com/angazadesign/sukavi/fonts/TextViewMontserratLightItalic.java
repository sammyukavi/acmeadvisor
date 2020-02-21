package com.angazadesign.sukavi.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;


public class TextViewMontserratLightItalic extends AppCompatTextView {
	
	public TextViewMontserratLightItalic(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	public TextViewMontserratLightItalic(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public TextViewMontserratLightItalic(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		if (!isInEditMode()) {
			Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-LightItalic.ttf");
			setTypeface(tf);
		}
	}
	
}