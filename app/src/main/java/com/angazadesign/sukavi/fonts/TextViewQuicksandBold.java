package com.angazadesign.sukavi.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;


public class TextViewQuicksandBold extends AppCompatTextView {
	
	public TextViewQuicksandBold(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	public TextViewQuicksandBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public TextViewQuicksandBold(Context context) {
		super(context);
		init();
	}
	
	private void init() {
		if (!isInEditMode()) {
			Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Quicksand-Bold.ttf");
			setTypeface(tf);
		}
	}
	
}