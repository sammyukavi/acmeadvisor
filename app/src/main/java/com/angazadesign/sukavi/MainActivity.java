package com.angazadesign.sukavi;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.angazadesign.sukavi.data.DataService;
import com.angazadesign.sukavi.data.FortunesDataService;
import com.angazadesign.sukavi.dialogs.IsBusyDialog;
import com.angazadesign.sukavi.fonts.TextViewMontserratLightItalic;
import com.angazadesign.sukavi.fonts.TextViewQuicksandBold;
import com.angazadesign.sukavi.models.Response;
import com.github.clans.fab.FloatingActionButton;

import java.util.List;

import static com.angazadesign.sukavi.application.Utility.getRandomQuote;

public class MainActivity extends AppCompatActivity {
	private MainActivity mContext;
	private IsBusyDialog mIsBusyDialog;
	private FortunesDataService mFortunesDataService;
	private TextViewQuicksandBold mQuoteTextView;
	private TextViewMontserratLightItalic mPersonTextView;
	private View mContentsHolder;
	private FloatingActionButton mFloatingActionButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mIsBusyDialog = new IsBusyDialog(mContext);
		mFortunesDataService = new FortunesDataService();
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		mContentsHolder = findViewById(R.id.holder);
		mQuoteTextView = findViewById(R.id.quote);
		mPersonTextView = findViewById(R.id.person);
		mFloatingActionButton = findViewById(R.id.fab);
		mFloatingActionButton.hide(false);
		mFloatingActionButton.setOnClickListener(v -> loadFortune());
		
		loadFortune();
		
	}
	
	private void loadFortune() {
		
		mIsBusyDialog.show();
		
		updateUI("", "");
		
		mFortunesDataService.cancelRequests();
		
		
		mFortunesDataService.getFortunes(new DataService.RestCallback<Response>() {
			@Override
			public void onCompleted(Response response) {
				List<String> fortunes = response.getFortunes();
				StringBuilder fortune = new StringBuilder();
				StringBuilder person = new StringBuilder();
				for (String quote : fortunes) {
					if (quote.startsWith("   ")) {
						person.append(quote);
					} else if (quote.endsWith("   ")) {
						fortune.append(quote);
						person.append(getString(R.string.sukavi));
					} else {
						fortune.append(quote);
					}
				}
				
				updateUI(fortune.toString(), person.toString());
				mIsBusyDialog.dismiss();
				animateFloatingButton();
			}
			
			@Override
			public void onError(Throwable throwable) {
				throwable.printStackTrace();
				onCompleted(getRandomQuote());
				mIsBusyDialog.dismiss();
			}
		});
	}
	
	private void animateFloatingButton() {
		mFloatingActionButton.hide(false);
		new Handler().postDelayed(() -> {
			mFloatingActionButton.show(true);
			mFloatingActionButton.setShowAnimation(AnimationUtils.loadAnimation(mContext, R.anim.show_from_bottom));
			mFloatingActionButton.setHideAnimation(AnimationUtils.loadAnimation(mContext, R.anim.hide_to_bottom));
		}, 800);
	}
	
	private void updateUI(String fortune, String person) {
		
		mContentsHolder.setVisibility(View.GONE);
		mPersonTextView.setVisibility(View.GONE);
		
		if (!fortune.trim().isEmpty() || !person.trim().isEmpty()) {
			mContentsHolder.setVisibility(View.VISIBLE);
		}
		
		if (!person.trim().isEmpty()) {
			mPersonTextView.setVisibility(View.VISIBLE);
		}
		
		mQuoteTextView.setText(fortune);
		mPersonTextView.setText(person);
	}
	
}
