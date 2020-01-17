package com.ethiopian.amharic.professional.utils;

import java.util.*;
import android.text.format.*;
import java.io.*;
import android.inputmethodservice.*;
import android.content.*;
import android.util.*;

public class TextEntryState
{
    private static boolean LOGGING = false;
    private static final String[] STATES;
    public static final int STATE_ACCEPTED_DEFAULT = 3;
    public static final int STATE_IN_WORD = 2;
    public static final int STATE_PICKED_SUGGESTION = 4;
    public static final int STATE_PUNCTUATION_AFTER_ACCEPTED = 6;
    public static final int STATE_PUNCTUATION_AFTER_WORD = 5;
    public static final int STATE_SPACE_AFTER_ACCEPTED = 7;
    public static final int STATE_SPACE_AFTER_PICKED = 8;
    public static final int STATE_START = 1;
    public static final int STATE_UNDO_COMMIT = 9;
    public static final int STATE_UNKNOWN=0; //it was not initialized
    private static int sActualChars;
    private static int sAutoSuggestCount;
    private static int sAutoSuggestUndoneCount;
    private static int sBackspaceCount;
    private static FileOutputStream sKeyLocationFile;
    private static int sManualSuggestCount;
    private static int sSessionCount;
    private static int sState;
    private static int sTypedChars;
    private static FileOutputStream sUserActionFile;
    private static int sWordNotInDictionaryCount;
    
    static {
        TextEntryState.LOGGING = false;
        TextEntryState.sBackspaceCount = 0;
        TextEntryState.sAutoSuggestCount = 0;
        TextEntryState.sAutoSuggestUndoneCount = 0;
        TextEntryState.sManualSuggestCount = 0;
        TextEntryState.sWordNotInDictionaryCount = 0;
        TextEntryState.sSessionCount = 0;
        STATES = new String[] { "Unknown", "Start", "In word", "Accepted default", "Picked suggestion", "Punc. after word", "Punc. after accepted", "Space after accepted", "Space after picked", "Undo commit" };
        TextEntryState.sState = 0;
    }
    
    public static void acceptedDefault(final CharSequence charSequence, final CharSequence obj) {
        if (!charSequence.equals(obj)) {
            ++TextEntryState.sAutoSuggestCount;
        }
        TextEntryState.sTypedChars += charSequence.length();
        TextEntryState.sActualChars += obj.length();
        TextEntryState.sState = 3;
    }
    
    public static void acceptedSuggestion(final CharSequence charSequence, final CharSequence obj) {
        ++TextEntryState.sManualSuggestCount;
        if (charSequence.equals(obj)) {
            acceptedTyped(charSequence);
        }
        TextEntryState.sState = 4;
    }
    
    public static void acceptedTyped(final CharSequence charSequence) {
        ++TextEntryState.sWordNotInDictionaryCount;
        TextEntryState.sState = 4;
    }
    
    public static void backspace() {
        if (TextEntryState.sState == 3) {
            TextEntryState.sState = 9;
            ++TextEntryState.sAutoSuggestUndoneCount;
        }
        else if (TextEntryState.sState == 9) {
            TextEntryState.sState = 2;
        }
        ++TextEntryState.sBackspaceCount;
    }
    
    public static void endSession() {
        if (TextEntryState.sKeyLocationFile == null) {
            return;
        }
        try {
            TextEntryState.sKeyLocationFile.close();
            TextEntryState.sUserActionFile.write((String.valueOf(DateFormat.format((CharSequence)"MM:dd hh:mm:ss", Calendar.getInstance().getTime()).toString()) + " BS: " + TextEntryState.sBackspaceCount + " auto: " + TextEntryState.sAutoSuggestCount + " manual: " + TextEntryState.sManualSuggestCount + " typed: " + TextEntryState.sWordNotInDictionaryCount + " undone: " + TextEntryState.sAutoSuggestUndoneCount + " saved: " + (TextEntryState.sActualChars - TextEntryState.sTypedChars) / TextEntryState.sActualChars + "\n").getBytes());
            TextEntryState.sUserActionFile.close();
            TextEntryState.sKeyLocationFile = null;
            TextEntryState.sUserActionFile = null;
        }
        catch (IOException ex) {}
    }
    
    public static int getState() {
        return TextEntryState.sState;
    }
    
    public static void keyPressedAt(final Keyboard.Key keyboard$Key, final int i, final int j) {
        if (!TextEntryState.LOGGING || TextEntryState.sKeyLocationFile == null || keyboard$Key.codes[0] < 32) {
            return;
        }
        final String string = "KEY: " + (char)keyboard$Key.codes[0] + " X: " + i + " Y: " + j + " MX: " + (keyboard$Key.x + keyboard$Key.width / 2) + " MY: " + (keyboard$Key.y + keyboard$Key.height / 2) + "\n";
        try {
            TextEntryState.sKeyLocationFile.write(string.getBytes());
        }
        catch (IOException ex) {}
    }
    
    public static void newSession(final Context context) {
        ++TextEntryState.sSessionCount;
        TextEntryState.sAutoSuggestCount = 0;
        TextEntryState.sBackspaceCount = 0;
        TextEntryState.sAutoSuggestUndoneCount = 0;
        TextEntryState.sManualSuggestCount = 0;
        TextEntryState.sWordNotInDictionaryCount = 0;
        TextEntryState.sTypedChars = 0;
        TextEntryState.sActualChars = 0;
        TextEntryState.sState = 1;
        if (!TextEntryState.LOGGING) {
            return;
        }
        try {
            TextEntryState.sKeyLocationFile = context.openFileOutput("key.txt", 32768);
            TextEntryState.sUserActionFile = context.openFileOutput("action.txt", 32768);
        }
        catch (IOException obj) {
            Log.e("TextEntryState", "Couldn't open file for output: " + obj);
        }
    }
    
    public static void reset() {
        TextEntryState.sState = 1;
    }
    
    public static void typedCharacter(final char c, final boolean b) {
        final boolean b2 = c == ' ';
        switch (TextEntryState.sState) {
            case 2: {
                if (b2 || b) {
                    TextEntryState.sState = 1;
                    return;
                }
                break;
            }
            case 3:
            case 8: {
                if (b2) {
                    TextEntryState.sState = 7;
                    return;
                }
                if (b) {
                    TextEntryState.sState = 6;
                    return;
                }
                TextEntryState.sState = 2;
            }
            case 4: {
                if (b2) {
                    TextEntryState.sState = 8;
                    return;
                }
                if (b) {
                    TextEntryState.sState = 6;
                    return;
                }
                TextEntryState.sState = 2;
            }
            case 0:
            case 1:
            case 5:
            case 6:
            case 7: {
                if (!b2 && !b) {
                    TextEntryState.sState = 2;
                    return;
                }
                TextEntryState.sState = 1;
            }
            case 9: {
                if (b2 || b) {
                    TextEntryState.sState = 3;
                    return;
                }
                TextEntryState.sState = 2;
            }
        }
    }
}
