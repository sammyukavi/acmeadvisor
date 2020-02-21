package com.angazadesign.sukavi.dialogs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import com.angazadesign.sukavi.R;


public class IsBusyDialog extends AppCompatDialog {
	
	private TextView dialogMessage;
	
	public IsBusyDialog(@NonNull Activity activity, boolean isCancellable) {
		super(activity);
		setCancelable(isCancellable);
		initViews(activity);
	}
	
	public IsBusyDialog(@NonNull Activity activity) {
		super(activity);
		setCancelable(false);
		initViews(activity);
	}
	
	private void initViews(Activity activity) {
		View view = LayoutInflater.from(activity).inflate(R.layout.layout_progress_bar, null);
		dialogMessage = view.findViewById(R.id.dialogMessage);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(view);
	}
	
	public void setMessage(String message) {
		this.dialogMessage.setText(message);
	}
	
	/*@Override
	public void show() {
		if (!this.isShowing()) {
			super.show();
		}
	}*/
}
