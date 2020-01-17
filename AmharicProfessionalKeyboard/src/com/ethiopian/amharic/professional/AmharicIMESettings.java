package com.ethiopian.amharic.professional;


import android.os.*;
import android.webkit.*;
import android.net.*;
import android.widget.*;
import android.content.*;
import android.view.*;
import android.preference.*;
import android.app.*;

public class AmharicIMESettings extends PreferenceActivity
{
    private static final int DIALOG_HELP = 0;
    private static final int DIALOG_VIBRATE_OPTIONS = 1;
    private static final String HELP = "help";
    private static final String VIBRATE_BUG_FIX = "vibrate_bug_fix";
    private static final String VIBRATE_DURATION = "vibrate_duration";
    private static final String VIBRATE_ENABLE = "vibrate_enable";
    private static final String VIBRATE_OPTIONS = "vibrate_options";
    private static final String RATE_APPLICATION = "rateApp";
    private static final String SHARE_APPLICATION = "shareApp";
    private static final String CONTACT_APPLICATION_CREATOR = "contactCreator";
    private static final String DONATE_APPLICATION = "donateApplication";
    
    private Preference mHelp;
    private Preference mVibrateOptions;
    private SharedPreferences sp;
    private Preference rateApplication;
    private Preference shareApplication;
    private Preference contactApplicationCreator;
    private Preference donateApplication;
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.addPreferencesFromResource(2130968597);
        this.mHelp = this.findPreference((CharSequence)"help");
        this.mVibrateOptions = this.findPreference((CharSequence)"vibrate_options");
        this.sp = PreferenceManager.getDefaultSharedPreferences((Context)this);
        rateApplication = findPreference(RATE_APPLICATION);
        shareApplication = findPreference(SHARE_APPLICATION);
        contactApplicationCreator = findPreference(CONTACT_APPLICATION_CREATOR);
        donateApplication = findPreference(DONATE_APPLICATION);
        new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(final Preference preference, final Object o) {
                if (preference instanceof ListPreference) {
                    ((ListPreference)preference).setValue(o.toString());
                }
                else if (preference instanceof CheckBoxPreference) {
                    ((CheckBoxPreference)preference).setChecked((boolean)o);
                }
                return true;
            }
        };
    }
    
    protected Dialog onCreateDialog(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                final View inflate = LayoutInflater.from((Context)this).inflate(2130903043, (ViewGroup)null);
                ((WebView)inflate.findViewById(2131492869)).loadUrl("file:///android_asset/about.html");
                return (Dialog)new AlertDialog.Builder((Context)this).setTitle(2131361858).setView(inflate).setIcon(17301569).setPositiveButton(2131361859, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        AmharicIMESettings.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://destahaileselassie.wordpress.com/")));
                    }
                }).setNeutralButton(2131361860, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        AmharicIMESettings.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=E6VYTN8UC9XAW")));
                    }
                }).setNegativeButton(2131361861, (DialogInterface.OnClickListener)null).create();
            }
            case 1: {
                final View inflate2 = LayoutInflater.from((Context)this).inflate(2130903047, (ViewGroup)null);
                final CheckBox checkBox = (CheckBox)inflate2.findViewById(2131492876);
                final TextView textView = (TextView)inflate2.findViewById(2131492877);
                final SeekBar seekBar = (SeekBar)inflate2.findViewById(2131492878);
                final CheckBox checkBox2 = (CheckBox)inflate2.findViewById(2131492879);
                checkBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener)new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                        seekBar.setEnabled(b);
                        checkBox2.setEnabled(b);
                    }
                });
                seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)new SeekBar.OnSeekBarChangeListener() {
                    public void onProgressChanged(final SeekBar seekBar, final int i, final boolean b) {
                        textView.setText((CharSequence)(String.valueOf(AmharicIMESettings.this.getResources().getString(2131361866)) + " " + Integer.toString(i) + " ms"));
                    }
                    
                    public void onStartTrackingTouch(final SeekBar seekBar) {
                    }
                    
                    public void onStopTrackingTouch(final SeekBar seekBar) {
                    }
                });
                return (Dialog)new AlertDialog.Builder((Context)this).setTitle(2131361863).setView(inflate2).setPositiveButton(2131361864, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        final SharedPreferences.Editor edit = AmharicIMESettings.this.sp.edit();
                        edit.putBoolean("vibrate_enable", checkBox.isChecked());
                        edit.putInt("vibrate_duration", seekBar.getProgress());
                        edit.putBoolean("vibrate_bug_fix", checkBox2.isChecked());
                        edit.commit();
                    }
                }).setNegativeButton(17039360, (DialogInterface.OnClickListener)null).create();
            }
        }
    }
    
    public boolean onPreferenceTreeClick(final PreferenceScreen preferenceScreen, final Preference preference) {
        if (preference == this.mHelp) {
            this.showDialog(0);
        }
        else if (preference == this.mVibrateOptions) {
            this.showDialog(1);
        }
        else if(preference == rateApplication){
        	startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=com.ethiopian.amharic.professional")));
        }else if(preference == shareApplication){
        	Intent localIntent3 = new Intent("android.intent.action.SEND");
            localIntent3.setType("text/plain");
            localIntent3.putExtra("android.intent.extra.SUBJECT", "Checkout Ethiopian Virtual Keyboard.");
            localIntent3.putExtra("android.intent.extra.TEXT", "A cool Amharic Professional Keyboard for android devices. market://details?id=com.ethiopian.amharic.professional");
            AmharicIMESettings.this.startActivity(Intent.createChooser(localIntent3, "Thanks for sharing. Share with:"));
        	
        }
        else if (preference == contactApplicationCreator){
        	Intent localIntent2 = new Intent("android.intent.action.SEND");
            localIntent2.putExtra("android.intent.extra.EMAIL", new String[] { "desta161921@gmail.com" });
            localIntent2.putExtra("android.intent.extra.SUBJECT", "[Amharic Professional Keyboard Feedback]");
            localIntent2.setType("plain/text");
            localIntent2.putExtra("android.intent.extra.TEXT", "");
            AmharicIMESettings.this.startActivity(Intent.createChooser(localIntent2, "Thanks for your feedback. Email with:"));
        	
        }else if(preference == donateApplication){
        	startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=E6VYTN8UC9XAW")));
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
    
    protected void onPrepareDialog(final int n, final Dialog dialog) {
        switch (n) {
            case 1: {
                final AlertDialog alertDialog = (AlertDialog)dialog;
                final CheckBox checkBox = (CheckBox)alertDialog.findViewById(2131492876);
                final TextView textView = (TextView)alertDialog.findViewById(2131492877);
                final SeekBar seekBar = (SeekBar)alertDialog.findViewById(2131492878);
                final CheckBox checkBox2 = (CheckBox)alertDialog.findViewById(2131492879);
                this.sp = PreferenceManager.getDefaultSharedPreferences((Context)this);
                final boolean boolean1 = this.sp.getBoolean("vibrate_enable", true);
                final int int1 = this.sp.getInt("vibrate_duration", this.getResources().getInteger(2131427328));
                checkBox.setChecked(boolean1);
                textView.setText((CharSequence)(String.valueOf(this.getResources().getString(2131361866)) + " " + Integer.toString(int1) + " ms"));
                seekBar.setProgress(int1);
                checkBox2.setChecked(this.sp.getBoolean("vibrate_bug_fix", false));
                if (!boolean1) {
                    seekBar.setEnabled(false);
                    checkBox2.setEnabled(false);
                    return;
                }
                break;
            }
        }
    }
    
    protected void onResume() {
        super.onResume();
    }
}
