package com.ethiopian.amharic.professional;

import android.preference.*;
import android.util.*;
import android.app.*;
import android.content.*;

public class ListPreferenceMultiSelect extends ListPreference
{
    private static final String ALL_CHECKED = "#ALL#";
    private static final String SEPARATOR = "OV=I=XseparatorX=I=VO";
    private boolean[] mClickedDialogEntryIndices;
    
    public ListPreferenceMultiSelect(final Context context) {
        this(context, null);
    }
    
    public ListPreferenceMultiSelect(final Context context, final AttributeSet set) {
        super(context, set);
        this.mClickedDialogEntryIndices = new boolean[this.getEntries().length];
    }
    
    public static String[] parseStoredValue(final CharSequence anObject) {
        if ("".equals(anObject)) {
            return null;
        }
        return ((String)anObject).split("OV=I=XseparatorX=I=VO");
    }
    
    private void restoreCheckedEntries() {
        final CharSequence[] entryValues = this.getEntryValues();
        final String[] storedValue = parseStoredValue(this.getValue());
        if (storedValue != null) {
            for (int i = 0; i < storedValue.length; ++i) {
                final String trim = storedValue[i].trim();
                for (int j = 0; j < entryValues.length; ++j) {
                    if (entryValues[j].equals(trim)) {
                        this.mClickedDialogEntryIndices[j] = true;
                        break;
                    }
                }
            }
        }
    }
    
    public boolean checkAllIfValueIsAll() {
        if ("#ALL#".equals(this.getValue())) {
            for (int i = 0; i < this.mClickedDialogEntryIndices.length; ++i) {
                this.mClickedDialogEntryIndices[i] = true;
            }
            this.onDialogClosed(true);
            return true;
        }
        return false;
    }
    
    protected void onDialogClosed(final boolean b) {
        final CharSequence[] entryValues = this.getEntryValues();
        if (b && entryValues != null) {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < entryValues.length; ++i) {
                if (this.mClickedDialogEntryIndices[i]) {
                    sb.append(entryValues[i]).append("OV=I=XseparatorX=I=VO");
                }
            }
            if (this.callChangeListener((Object)sb)) {
                String value = sb.toString();
                if (value.length() > 0) {
                    value = value.substring(0, value.length() - "OV=I=XseparatorX=I=VO".length());
                }
                this.setValue(value);
            }
        }
    }
    
    protected void onPrepareDialogBuilder(final AlertDialog.Builder alertDialogBuilder) {
        final CharSequence[] entries = this.getEntries();
        final CharSequence[] entryValues = this.getEntryValues();
        if (entries == null || entryValues == null || entries.length != entryValues.length) {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array which are both the same length");
        }
        this.restoreCheckedEntries();
        alertDialogBuilder.setMultiChoiceItems(entries, this.mClickedDialogEntryIndices, (DialogInterface.OnMultiChoiceClickListener)new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n, final boolean b) {
                ListPreferenceMultiSelect.this.mClickedDialogEntryIndices[n] = b;
            }
        });
    }
    
    public void setEntries(final CharSequence[] entries) {
        super.setEntries(entries);
        this.mClickedDialogEntryIndices = new boolean[entries.length];
    }
}
