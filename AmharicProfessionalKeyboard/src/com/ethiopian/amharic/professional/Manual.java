package com.ethiopian.amharic.professional;

import android.app.*;
import android.os.*;
import android.webkit.*;

public class Manual extends Activity
{
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130903056);
        ((WebView)this.findViewById(2131492884)).loadUrl("file:///android_asset/manual.html");
    }
    
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
