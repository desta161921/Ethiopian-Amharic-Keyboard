package com.ethiopian.amharic.professional.utils;

import com.ethiopian.amharic.professional.R;

import android.app.*;
import android.widget.*;
import android.net.Uri;
import android.os.*;
import android.view.*;
import android.content.*;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.*;

public class InfoScreen extends Activity {
	public void onCreate(final Bundle bundle) {
		super.onCreate(bundle);
		
		this.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		this.setContentView(R.layout.info_screen);
		this.getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.icon);
		this.getWindow().setLayout(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);

		final TextView textView = (TextView) this.findViewById(2131492880);
		final Button button = (Button) this.findViewById(2131492881);
		final Button button2 = (Button) this.findViewById(2131492882);
		final Button button3 = (Button) this.findViewById(2131492883);
		Button bindingButton = (Button)this.findViewById(R.id.app_binding_button);
		Button manualButton = (Button) this
				.findViewById(R.id.app_manual_button);
		Button rateButton = (Button) this.findViewById(R.id.app_rate_button);
		Button shareButton = (Button) this.findViewById(R.id.app_share_button);
		
		Button contactButton = (Button) this
				.findViewById(R.id.app_contact_button);
		Button donateButton = (Button) this
				.findViewById(R.id.app_donate_button);

		if (Integer.parseInt(Build.VERSION.SDK) < 5) {
			textView.setText((CharSequence) this.getResources()
					.getString(2131361852)
					.replace("Language & keyboard", "Locale & text"));
		}
		button.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
			public void onClick(final View view) {
				InfoScreen.this.startActivity(new Intent(
						"android.settings.INPUT_METHOD_SETTINGS"));
			}
		});
		button2.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
			public void onClick(final View view) {
				((InputMethodManager) InfoScreen.this
						.getSystemService("input_method"))
						.showInputMethodPicker();
			}
		});
		button3.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
			public void onClick(final View view) {
				InfoScreen.this.finish();
			}
		});
		bindingButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent("com.ethiopian.amharic.professional.Manual");
				startActivity(intent);
			}
		});

		manualButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent("com.ethiopian.amharic.professional.Help");
				startActivity(intent);
			}
		});

		rateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("market://details?id=com.ethiopian.amharic.professional")));

			}
		});
		shareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent localIntent3 = new Intent("android.intent.action.SEND");
				localIntent3.setType("text/plain");
				localIntent3.putExtra("android.intent.extra.SUBJECT",
						"Checkout Amharic Professional Keyboard.");
				localIntent3
						.putExtra(
								"android.intent.extra.TEXT",
								"A cool Amharic Professional Keyboard for android devices with smart auto-completion features. market://details?id=com.ethiopian.amharic.professional");
				startActivity(Intent.createChooser(localIntent3,
						"Thanks for sharing. Share with:"));

			}
		});

		contactButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent localIntent2 = new Intent("android.intent.action.SEND");
				localIntent2.putExtra("android.intent.extra.EMAIL",
						new String[] { "desta161921@gmail.com" });
				localIntent2.putExtra("android.intent.extra.SUBJECT",
						"[Amharic Professional Keyboard Feedback]");
				localIntent2.setType("plain/text");
				localIntent2.putExtra("android.intent.extra.TEXT", "");
				startActivity(Intent.createChooser(localIntent2,
						"Thanks for your feedback. Email with:"));

			}
		});
		donateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("https://destahaileselassie.wordpress.com/ethiopian-virtual-keyboard/donate/")));

			}
		});

	}
}
