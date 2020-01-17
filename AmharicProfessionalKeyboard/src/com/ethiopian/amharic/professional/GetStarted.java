package com.ethiopian.amharic.professional;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class GetStarted extends PreferenceActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(0x7f030012);
  }

  protected void onPause()
  {
    super.onPause();
    finish();
  }
}

