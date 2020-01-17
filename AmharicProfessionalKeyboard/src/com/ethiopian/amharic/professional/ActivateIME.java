package com.ethiopian.amharic.professional;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivateIME extends Activity
{
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    startActivity(new Intent(getApplicationContext(), GetStarted.class));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    startActivityForResult(new Intent("android.settings.INPUT_METHOD_SETTINGS"), 0);
  }
}

