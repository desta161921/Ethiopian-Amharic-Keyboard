package com.ethiopian.amharic.professional;

import android.media.*;
import android.preference.*;

import com.ethiopian.amharic.professional.utils.*;


import android.graphics.drawable.*;
import android.text.*;
import android.app.*;

import java.util.*;
import android.inputmethodservice.*;
import android.content.res.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.inputmethod.*;

import java.io.*;
import android.util.*;

public class AmharicIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener
{
    private static final int CPS_BUFFER_SIZE = 16;
    static final boolean DEBUG = false;
    private static final int DELETE_ACCELERATE_AT = 20;
    static final int FREQUENCY_FOR_AUTO_ADD = 250;
    static final int FREQUENCY_FOR_PICKED = 3;
    static final int FREQUENCY_FOR_TYPED = 3;
    static final int KEYCODE_ENTER = 10;
    static final int KEYCODE_SPACE = 32;
    public static final byte KEYMAP_DEFAULT = 1;
    public static final byte KEYMAP_RDC = 2;
    private static final int MSG_UPDATE_SHIFT_STATE = 2;
    private static final int MSG_UPDATE_SUGGESTIONS = 0;
    private static final boolean PERF_DEBUG = false;
 // Contextual menu positions
    private static final int POS_SETTINGS = 0;
    private static final int POS_METHOD = 1;
        private static final int POS_USER_DICTIONARY = 2;
    private static final int POS_LAYOUT = 3;
    private static final int POS_DICTIONARY = 4;
    private static final String PREF_AT_IS_WORD_SEPARATOR = "at_is_word_separator";
    private static final String PREF_AUTO_CAP = "auto_cap";
    private static final String PREF_AUTO_COMPLETE = "auto_complete";
    private static final String PREF_AUTO_DICTIONARY_CASE_SENSITIVE = "auto_dictionary_case_sensitive";
    private static final String PREF_AUTO_DICTIONARY_ENABLE = "auto_dictionary_enable";
    private static final String PREF_AUTO_DICTIONARY_LIMIT = "auto_dictionary_limit";
    private static final String PREF_DICTIONARY = "dictionary";
    private static final String PREF_DICTIONARY_MANUALLY = "dictionary_manually";
    private static final String PREF_KEYBOARD_LAYOUT = "keyboard_layout";
    private static final String PREF_KEYBOARD_TYPE = "keyboard_type";
    private static final String PREF_QUICK_FIXES = "quick_fixes";
    private static final String PREF_SHOW_SUGGESTIONS = "show_suggestions";
    private static final String PREF_SKIN = "skin";
    private static final String PREF_SOUND_ON = "sound_on";
    private static final String PREF_SPACE_AFTER_PREDICTION = "space_after_prediction";
    private static final String PREF_SWAP_COLON = "swap_colon";
    private static final String PREF_SWIPE_DICTIONARY = "swipe_dictionary";
    private static final String PREF_SWIPE_DOWN = "swipe_down";
    private static final String PREF_SWIPE_ENABLED = "swipe_enabled";
    private static final String PREF_SWIPE_KEYBOARD_LAYOUT = "swipe_keyboard_layout";
    private static final String PREF_SWIPE_LEFT = "swipe_left";
    private static final String PREF_SWIPE_RIGHT = "swipe_right";
    private static final String PREF_SWIPE_UP = "swipe_up";
    private static final String PREF_VIBRATE_BUG_FIX = "vibrate_bug_fix";
    private static final String PREF_VIBRATE_DURATION = "vibrate_duration";
    private static final String PREF_VIBRATE_ON = "vibrate_enable";
    private static final int QUICK_PRESS = 200;
    static final boolean TRACE = false;
    public static final int geez_key = -8080;
    public static boolean mGeezState;
    private final float FX_VOLUME;
    CoreIME geezime;
    private int lastKeyPressed;
    private HashMap<Integer, Integer> letterSymbolArray;
    private AudioManager mAudioManager;
    private boolean mAutoCap;
    private boolean mAutoCorrectOn;
    private boolean mAutoSpace;
    private CharSequence[] mAvailableDictionaries;
    private CharSequence[] mAvailableDictionaryValues;
    private CharSequence mBestWord;
    private boolean mCapsLock;
    private int mCommittedLength;
    private boolean mCompletionOn;
    private StringBuilder mComposing;
    private int mCorrectionMode;
    private int mCpsIndex;
    private long[] mCpsIntervals;
    private int mDeleteCount;
    private String mDictionary;
    private boolean mDictionaryManually;
    Handler mHandler;
    private AmharicKeyboardView mInputView;
    private boolean mJustAccepted;
    private CharSequence mJustRevertedSeparator;
    private int mKeyboardLayout;
    KeyboardSwitcher mKeyboardSwitcher;
    private int mKeyboardType;
    private long mLastCpsTime;
    private long mLastKeyTime;
    private String mLastSkin;
    private AlertDialog mOptionsDialog;
    private int mOrientation;
    private boolean mPredicting;
    private boolean mPredictionOn;
    private BroadcastReceiver mReceiver;
    private String mSentenceSeparators;
    private boolean mShowSuggestions;
    private boolean mSilentMode;
    private String mSkin;
    private boolean mSoundOn;
    private boolean mSwapColon;
    private String mSwipeDictionary;
    private int mSwipeDown;
    private boolean mSwipeEnabled;
    private String mSwipeKeyboardLayout;
    private int mSwipeLeft;
    private int mSwipeRight;
    private int mSwipeUp;
    private boolean mVibrateBugFix;
    private long mVibrateDuration;
    private boolean mVibrateOn;
    private long mVibrateStart;
    private Timer mVibrateTimer;
    private Vibrator mVibrator;
    private WordComposer mWord;
    private ArrayList<Integer> validKeyCodes;
    
    static {
        AmharicIME.mGeezState = false;
    }
    
    public AmharicIME() {
        super();
        this.geezime = new CoreIME();
        this.mComposing = new StringBuilder();
        this.mWord = new WordComposer();
        this.mVibrateDuration = 0L;
        this.FX_VOLUME = -1.0f;
        this.mHandler = new Handler() {
            public void handleMessage(final Message message) {
                switch (message.what) {
                    default: {}
                    case 2: {
                        AmharicIME.this.updateShiftKeyState(AmharicIME.this.getCurrentInputEditorInfo());
                    }
                }
            }
        };
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                AmharicIME.this.updateRingerMode();
            }
        };
        this.mCpsIntervals = new long[16];
    }
    
    static /* synthetic */ void access$4(final AmharicIME amharicIME, final long mVibrateStart) {
        amharicIME.mVibrateStart = mVibrateStart;
    }
    
    private void changeKeyboardMode() {
        this.mKeyboardSwitcher.toggleSymbols();
        if (this.mCapsLock && this.mKeyboardSwitcher.isAlphabetMode()) {
            ((AmharicKeyboard)this.mInputView.getKeyboard()).setShiftLocked(this.mCapsLock);
        }
        this.updateShiftKeyState(this.getCurrentInputEditorInfo());
    }
    
    private void changePreference(final String s, final String s2) {
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences((Context)this).edit();
        edit.putString(s, s2);
        edit.commit();
        this.onStartInputView(this.getCurrentInputEditorInfo(), true);
    }
    
    private void changePreference(final String s, final boolean b) {
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences((Context)this).edit();
        edit.putBoolean(s, b);
        edit.commit();
        this.onStartInputView(this.getCurrentInputEditorInfo(), true);
    }
    
    private void checkToggleCapsLock() {
        if (this.mInputView.getKeyboard().isShifted()) {
            this.toggleCapsLock();
        }
    }
    
    private void commitTyped(final InputConnection inputConnection) {
        if (this.mComposing.length() > 0) {
            if (inputConnection != null) {
                inputConnection.commitText((CharSequence)this.mComposing, 1);
            }
            this.mCommittedLength = this.mComposing.length();
            TextEntryState.acceptedTyped(this.mComposing);
            this.mComposing.setLength(0);
            this.mWord.reset();
        }
    }
    
    private void createLetterSymbolArray() {
        this.validKeyCodes = new ArrayList<Integer>();
        (this.letterSymbolArray = new HashMap<Integer, Integer>()).put(97, 64);
        this.letterSymbolArray.put(98, 59);
        this.letterSymbolArray.put(99, 39);
        this.letterSymbolArray.put(100, 36);
        this.letterSymbolArray.put(101, 51);
        this.letterSymbolArray.put(102, 37);
        this.letterSymbolArray.put(103, 38);
        this.letterSymbolArray.put(104, 42);
        this.letterSymbolArray.put(105, 56);
        this.letterSymbolArray.put(106, 45);
        this.letterSymbolArray.put(107, 43);
        this.letterSymbolArray.put(108, 40);
        this.letterSymbolArray.put(109, 63);
        this.letterSymbolArray.put(110, 47);
        this.letterSymbolArray.put(111, 57);
        this.letterSymbolArray.put(112, 48);
        this.letterSymbolArray.put(113, 49);
        this.letterSymbolArray.put(114, 52);
        this.letterSymbolArray.put(115, 35);
        this.letterSymbolArray.put(116, 53);
        this.letterSymbolArray.put(117, 55);
        this.letterSymbolArray.put(118, 58);
        this.letterSymbolArray.put(119, 50);
        this.letterSymbolArray.put(120, 34);
        this.letterSymbolArray.put(121, 54);
        this.letterSymbolArray.put(122, 33);
        switch (this.mKeyboardLayout) {
            default: {
                this.letterSymbolArray.put(230, 95);
                this.letterSymbolArray.put(248, 41);
                this.validKeyCodes.clear();
                this.validKeyCodes.add(230);
                this.validKeyCodes.add(248);
            }
            case 1:
            case 5:
            case 6: {
                this.letterSymbolArray.put(230, 41);
                this.letterSymbolArray.put(248, 95);
                this.validKeyCodes.clear();
                this.validKeyCodes.add(230);
                this.validKeyCodes.add(248);
            }
            case 3:
            case 4: {
                this.letterSymbolArray.put(228, 95);
                this.letterSymbolArray.put(246, 41);
                this.validKeyCodes.clear();
                this.validKeyCodes.add(228);
                this.validKeyCodes.add(246);
            }
            case 7: {
                this.letterSymbolArray.put(121, 33);
                this.letterSymbolArray.put(122, 54);
                this.letterSymbolArray.put(228, 95);
                this.letterSymbolArray.put(246, 41);
                this.validKeyCodes.clear();
                this.validKeyCodes.add(228);
                this.validKeyCodes.add(246);
            }
            case 8: {
                this.letterSymbolArray.put(225, 49);
                this.letterSymbolArray.put(353, 50);
                this.letterSymbolArray.put(359, 54);
                this.letterSymbolArray.put(248, 41);
                this.letterSymbolArray.put(230, 95);
                this.letterSymbolArray.put(382, 33);
                this.letterSymbolArray.put(122, 34);
                this.letterSymbolArray.put(269, 39);
                this.letterSymbolArray.put(99, 58);
                this.letterSymbolArray.put(118, 59);
                this.letterSymbolArray.put(98, 47);
                this.letterSymbolArray.put(110, 63);
                this.validKeyCodes.clear();
                this.validKeyCodes.add(225);
                this.validKeyCodes.add(230);
                this.validKeyCodes.add(248);
                this.validKeyCodes.add(269);
                this.validKeyCodes.add(353);
                this.validKeyCodes.add(359);
                this.validKeyCodes.add(382);
            }
        }
    }
    
    private void doubleSpace() {
        final InputConnection currentInputConnection = this.getCurrentInputConnection();
        if (currentInputConnection != null) {
            final CharSequence textBeforeCursor = currentInputConnection.getTextBeforeCursor(3, 0);
            if (textBeforeCursor != null && textBeforeCursor.length() == 3 && Character.isLetterOrDigit(textBeforeCursor.charAt(0)) && textBeforeCursor.charAt(1) == ' ' && textBeforeCursor.charAt(2) == ' ') {
                currentInputConnection.beginBatchEdit();
                currentInputConnection.deleteSurroundingText(2, 0);
                if (AmharicIME.mGeezState) {
                    currentInputConnection.commitText((CharSequence)"\u1362 ", 1);
                }
                else {
                    currentInputConnection.commitText((CharSequence)". ", 1);
                }
                currentInputConnection.endBatchEdit();
                this.updateShiftKeyState(this.getCurrentInputEditorInfo());
            }
        }
    }
    
    private void drawGeezState() {
        Drawable drawable;
        if (AmharicIME.mGeezState) {
            drawable = this.getResources().getDrawable(2130837531);
        }
        else {
            drawable = this.getResources().getDrawable(2130837530);
        }
        this.mKeyboardSwitcher.drawGeezState(drawable);
    }
    
    private void handleBackspace() {
        final InputConnection currentInputConnection = this.getCurrentInputConnection();
        if (currentInputConnection == null) {
            return;
        }
        boolean b;
        if (AmharicIME.mGeezState || this.mPredicting) {
            final int length = this.mComposing.length();
            if (length > 0) {
                this.mComposing.delete(length - 1, length);
                this.mWord.deleteLast();
                currentInputConnection.setComposingText((CharSequence)this.mComposing, 1);
                final int length2 = this.mComposing.length();
                b = false;
                if (length2 == 0) {
                    this.mPredicting = false;
                }
            }
            else {
                currentInputConnection.deleteSurroundingText(1, 0);
                b = false;
            }
        }
        else {
            b = true;
        }
        this.postUpdateShiftKeyState();
        TextEntryState.backspace();
        if (TextEntryState.getState() == 9) {
            this.revertLastWord(b);
            return;
        }
        if (b) {
            this.sendDownUpKeyEvents(67);
            if (this.mDeleteCount > 20) {
                this.sendDownUpKeyEvents(67);
            }
        }
        this.mJustRevertedSeparator = null;
    }
    
    private void handleCharacter(int char1, final int[] codePoints) {
        if (this.isAlphabet(char1) && this.isPredictionOn() && !this.isCursorTouchingWord() && !this.mPredicting) {
            this.mPredicting = true;
            this.mComposing.setLength(0);
            this.mWord.reset();
        }
        if (this.mInputView.isShifted()) {
            if (codePoints == null || codePoints[0] < 0 || codePoints[0] > 1114111) {
                return;
            }
            char1 = new String(codePoints, 0, 1).toUpperCase().charAt(0);
        }
        if (this.mPredicting) {
            if (this.mInputView.isShifted() && this.mComposing.length() == 0) {
                this.mWord.setCapitalized(true);
            }
            this.mComposing.append((char)char1);
            this.mWord.add(char1, codePoints);
            final InputConnection currentInputConnection = this.getCurrentInputConnection();
            if (currentInputConnection != null) {
                currentInputConnection.setComposingText((CharSequence)this.mComposing, 1);
            }
        }
        else {
            this.sendKeyChar((char)char1);
        }
        this.updateShiftKeyState(this.getCurrentInputEditorInfo());
        this.measureCps();
        TextEntryState.typedCharacter((char)char1, false);
    }
    
    private void handleClose() {
        this.commitTyped(this.getCurrentInputConnection());
        this.requestHideSelf(0);
        this.mInputView.closing();
        TextEntryState.endSession();
    }
    
    private void handleGeezCharacter(int upperCase, final int[] array) {
        if (this.mInputView.isShifted()) {
            upperCase = Character.toUpperCase(upperCase);
        }
        final char mapToGeez = this.geezime.MapToGeez((char)upperCase);
        if (mapToGeez == '\0') {
            return;
        }
        if (this.geezime.shouldErasePrev()) {
            Log.d("Desta", "Should Erase ");
            if (this.mComposing.length() > 0) {
                Log.d("Desta", "Erase " + this.mComposing.charAt(-1 + this.mComposing.length()));
                this.mComposing.delete(-1 + this.mComposing.length(), this.mComposing.length());
                this.mWord.deleteLast();
                this.getCurrentInputConnection().setComposingText((CharSequence)this.mComposing, 1);
            }
            this.geezime.shouldErasePrev(false);
        }
        final InputConnection currentInputConnection = this.getCurrentInputConnection();
        if (currentInputConnection != null) {
            this.commitTyped(currentInputConnection);
            this.mComposing.append(mapToGeez);
            this.mWord.add(mapToGeez, array);
            currentInputConnection.setComposingText((CharSequence)this.mComposing, 1);
        }
        this.updateShiftKeyState(this.getCurrentInputEditorInfo());
        this.measureCps();
        TextEntryState.typedCharacter(mapToGeez, false);
        this.mKeyboardSwitcher.onKey(mapToGeez);
    }
    
    private void handleSeparator(int mapToGeez) {
        boolean b = false;
        final InputConnection currentInputConnection = this.getCurrentInputConnection();
        if (currentInputConnection != null) {
            currentInputConnection.beginBatchEdit();
        }
        if (AmharicIME.mGeezState) {
            this.commitTyped(currentInputConnection);
            if (mapToGeez == 44) {
                mapToGeez = 4961;
            }
            else if (mapToGeez == 46) {
                mapToGeez = 4962;
                b = false;
            }
            else {
                mapToGeez = this.geezime.MapToGeez((char)mapToGeez);
                b = false;
            }
        }
        else {
            final boolean mPredicting = this.mPredicting;
            b = false;
            if (mPredicting) {
                if (this.mAutoCorrectOn && mapToGeez != 39 && (this.mJustRevertedSeparator == null || this.mJustRevertedSeparator.length() == 0 || this.mJustRevertedSeparator.charAt(0) != mapToGeez)) {
                    b = true;
                }
                else {
                    this.commitTyped(currentInputConnection);
                    b = false;
                }
            }
        }
        this.sendKeyChar((char)mapToGeez);
        TextEntryState.typedCharacter((char)mapToGeez, true);
        if (TextEntryState.getState() == 6 && mapToGeez != 10) {
            if (this.mSwapColon || (!":".equals(String.valueOf((char)mapToGeez)) && !";".equals(String.valueOf((char)mapToGeez)))) {
                this.swapPunctuationAndSpace();
            }
        }
        else if (this.isPredictionOn() && mapToGeez == 32) {
            this.doubleSpace();
        }
        if (b && this.mBestWord != null) {
            TextEntryState.acceptedDefault(this.mWord.getTypedWord(), this.mBestWord);
        }
        this.updateShiftKeyState(this.getCurrentInputEditorInfo());
        if (currentInputConnection != null) {
            currentInputConnection.endBatchEdit();
        }
    }
    
    private void handleShift() {
        if (this.mKeyboardSwitcher.isAlphabetMode()) {
            this.checkToggleCapsLock();
            this.mInputView.setShifted(this.mCapsLock || !this.mInputView.isShifted());
            return;
        }
        this.mKeyboardSwitcher.toggleShift();
    }
    
    private boolean isAlphabet(final int codePoint) {
        return Character.isLetter(codePoint);
    }
    
    private boolean isCandidateStripVisible() {
        return this.isPredictionOn() && this.mShowSuggestions;
    }
    
    private boolean isCursorTouchingWord() {
        final InputConnection currentInputConnection = this.getCurrentInputConnection();
        if (currentInputConnection != null) {
            final CharSequence textBeforeCursor = currentInputConnection.getTextBeforeCursor(1, 0);
            final CharSequence textAfterCursor = currentInputConnection.getTextAfterCursor(1, 0);
            if (!TextUtils.isEmpty(textBeforeCursor)) {
                return true;
            }
            if (!TextUtils.isEmpty(textAfterCursor)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isPredictionOn() {
        return this.mPredictionOn;
    }
    
    private void launchChooseLayout() {
        final CharSequence[] textArray = this.getResources().getTextArray(2131099651);
        int n = -1;
        for (int i = 0; i < textArray.length; ++i) {
            if (Integer.parseInt(textArray[i].toString()) == this.mKeyboardLayout) {
                n = i;
            }
        }
        final AlertDialog.Builder alertDialog$Builder = new AlertDialog.Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Change keyboard layout").setCancelable(true).setNegativeButton(17039360, (DialogInterface.OnClickListener)null).setSingleChoiceItems(2131099650, n, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                AmharicIME.this.changePreference("keyboard_layout", AmharicIME.this.getResources().getTextArray(2131099651)[n].toString());
                dialogInterface.dismiss();
            }
        });
        this.mOptionsDialog = alertDialog$Builder.create();
        final Window window = this.mOptionsDialog.getWindow();
        final WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.token = this.mInputView.getWindowToken();
        attributes.type = 1003;
        window.setAttributes(attributes);
        window.addFlags(131072);
        this.mOptionsDialog.show();
    }
    
    private void launchSettings() {
        this.handleClose();
        final Intent intent = new Intent();
        intent.setClass((Context)this, (Class)AmharicIMESettings.class);
        intent.setFlags(268435456);
        this.startActivity(intent);
    }
    
    private void loadSettings() {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)this);
        this.mKeyboardType = Integer.parseInt(defaultSharedPreferences.getString("keyboard_type", this.getResources().getString(2131361794)));
        this.mVibrateOn = defaultSharedPreferences.getBoolean("vibrate_enable", true);
        this.mVibrateDuration = defaultSharedPreferences.getInt("vibrate_duration", this.getResources().getInteger(2131427328));
        this.mVibrateBugFix = defaultSharedPreferences.getBoolean("vibrate_bug_fix", false);
        this.mSoundOn = defaultSharedPreferences.getBoolean("sound_on", false);
        this.mAutoCap = defaultSharedPreferences.getBoolean("auto_cap", true);
        this.mSwipeEnabled = defaultSharedPreferences.getBoolean("swipe_enabled", true);
        this.mSwipeUp = Integer.parseInt(defaultSharedPreferences.getString("swipe_up", this.getResources().getString(2131361877)));
        this.mSwipeDown = Integer.parseInt(defaultSharedPreferences.getString("swipe_down", this.getResources().getString(2131361878)));
        this.mSwipeLeft = Integer.parseInt(defaultSharedPreferences.getString("swipe_left", this.getResources().getString(2131361879)));
        this.mSwipeRight = Integer.parseInt(defaultSharedPreferences.getString("swipe_right", this.getResources().getString(2131361880)));
        this.mSwipeKeyboardLayout = defaultSharedPreferences.getString("swipe_keyboard_layout", this.getResources().getString(2131361881));
        this.mSwipeDictionary = defaultSharedPreferences.getString("swipe_dictionary", this.getResources().getString(2131361882));
        this.mSkin = defaultSharedPreferences.getString("skin", this.getResources().getString(2131361795));
        this.mKeyboardLayout = Integer.parseInt(defaultSharedPreferences.getString("keyboard_layout", this.getResources().getString(2131361796)));
        this.mDictionaryManually = defaultSharedPreferences.getBoolean("dictionary_manually", false);
        this.mDictionary = defaultSharedPreferences.getString("dictionary", this.getResources().getString(2131361797));
        this.createLetterSymbolArray();
        this.mShowSuggestions = defaultSharedPreferences.getBoolean("show_suggestions", this.getResources().getBoolean(2131165185));
        defaultSharedPreferences.getBoolean("auto_complete", this.getResources().getBoolean(2131165184));
        this.mAutoCorrectOn = false;
        this.mSwapColon = defaultSharedPreferences.getBoolean("swap_colon", true);
    }
    
    private void measureCps() {
    }
    
    private void playKeyClick(final int n) {
        if (this.mAudioManager == null && this.mInputView != null) {
            this.updateRingerMode();
        }
        if (this.mSoundOn && !this.mSilentMode) {
            int n2 = 5;
            switch (n) {
                case -5: {
                    n2 = 7;
                    break;
                }
                case 10: {
                    n2 = 8;
                    break;
                }
                case 32: {
                    n2 = 6;
                    break;
                }
            }
            this.mAudioManager.playSoundEffect(n2, -1.0f);
        }
    }
    
    private void postUpdateShiftKeyState() {
        this.mHandler.removeMessages(2);
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(2), 300L);
    }
    
    private void replaceLastCharacter(int char1, final int[] codePoints) {
        final InputConnection currentInputConnection = this.getCurrentInputConnection();
        if (currentInputConnection != null) {
            if (this.mInputView.isShifted()) {
                if (codePoints == null || codePoints[0] < 0 || codePoints[0] > 1114111) {
                    return;
                }
                char1 = new String(codePoints, 0, 1).toUpperCase().charAt(0);
            }
            if (this.mPredicting) {
                final int length = this.mComposing.length();
                if (length > 0) {
                    this.mComposing.delete(length - 1, length);
                    this.mWord.deleteLast();
                }
                else {
                    currentInputConnection.deleteSurroundingText(1, 0);
                }
                if (this.mInputView.isShifted() && this.mComposing.length() == 0) {
                    this.mWord.setCapitalized(true);
                }
                this.mComposing.append((char)char1);
                this.mWord.add(char1, codePoints);
                currentInputConnection.setComposingText((CharSequence)this.mComposing, 1);
            }
            else {
                this.sendDownUpKeyEvents(67);
                this.sendKeyChar((char)char1);
            }
            this.updateShiftKeyState(this.getCurrentInputEditorInfo());
            this.measureCps();
            TextEntryState.typedCharacter((char)char1, false);
        }
    }
    
    private void sendSpace() {
        this.sendKeyChar(' ');
        this.updateShiftKeyState(this.getCurrentInputEditorInfo());
    }
    
    private void showOptionsMenu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.ic_dialog_keyboard);
        builder.setNegativeButton(android.R.string.cancel, null);
        CharSequence itemSettings = getString(R.string.ime_settings);
        CharSequence itemInputMethod = getString(R.string.input_method);
        CharSequence itemKeyboardLayout = getString(R.string.keybinding_settings);
        //CharSequence itemDictionaryLanguage = getString(R.string.dictionary);
        //CharSequence itemUserDictionary = getString(R.string.user_dict_settings_title);
        CharSequence[] items;
        if(mDictionaryManually)
            items = new CharSequence[] {itemSettings, itemInputMethod, itemKeyboardLayout};
        else
            items = new CharSequence[] {itemSettings, itemInputMethod, itemKeyboardLayout};
        builder.setItems(items,
                new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface di, int position) {
                di.dismiss();
                switch (position) {
                    case POS_SETTINGS:
                        launchSettings();
                        break;
                    case POS_METHOD:
                        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .showInputMethodPicker();
                        break;
                    case POS_USER_DICTIONARY:
                    	try {
                            final Intent intent = new Intent((Context)AmharicIME.this, (Class)Manual.class);
                            intent.addFlags(268435456);
                            AmharicIME.this.startActivity(intent);
                        }
                        catch (Exception ex) {}
                        break;
                    case POS_LAYOUT:
                        launchChooseLayout();
                        break;
                    /*case POS_DICTIONARY:
                        launchChooseDictionary();
                        break;*/
                }
            }
        });
        builder.setTitle(getResources().getString(R.string.english_ime_name));
        mOptionsDialog = builder.create();
        Window window = mOptionsDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.token = mInputView.getWindowToken();
        lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mOptionsDialog.show();
        
    }
    
    private void swapPunctuationAndSpace() {
        final InputConnection currentInputConnection = this.getCurrentInputConnection();
        if (currentInputConnection != null) {
            final CharSequence textBeforeCursor = currentInputConnection.getTextBeforeCursor(2, 0);
            if (textBeforeCursor != null && textBeforeCursor.length() == 2 && textBeforeCursor.charAt(0) == ' ') {
                currentInputConnection.beginBatchEdit();
                currentInputConnection.deleteSurroundingText(2, 0);
                currentInputConnection.commitText((CharSequence)(String.valueOf(textBeforeCursor.charAt(1)) + " "), 1);
                currentInputConnection.endBatchEdit();
                this.updateShiftKeyState(this.getCurrentInputEditorInfo());
            }
        }
    }
    
    private void toggleCapsLock() {
        this.mCapsLock = !this.mCapsLock;
        if (this.mKeyboardSwitcher.isAlphabetMode()) {
            this.mInputView.setShifted(this.mCapsLock);
            ((AmharicKeyboard)this.mInputView.getKeyboard()).setShiftLocked(this.mCapsLock);
        }
    }
    
    private void toggleGeezState() {
        AmharicIME.mGeezState = !AmharicIME.mGeezState;
        final InputConnection currentInputConnection = this.getCurrentInputConnection();
        if (currentInputConnection != null) {
            this.commitTyped(currentInputConnection);
        }
        this.drawGeezState();
        this.updateShiftKeyState(this.getCurrentInputEditorInfo());
    }
    
    private void updateRingerMode() {
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager)this.getSystemService("audio");
        }
        if (this.mAudioManager != null) {
            this.mSilentMode = (this.mAudioManager.getRingerMode() != 2);
        }
    }
    
    private void vibrate() {
        if (this.mVibrateOn && this.mVibrateDuration <= 100L) {
            if (this.mVibrator == null) {
                this.mVibrator = (Vibrator)this.getSystemService("vibrator");
            }
            this.mVibrator.vibrate(this.mVibrateDuration);
            if (this.mVibrateBugFix) {
                if (this.mVibrateTimer == null) {
                    this.mVibrateTimer = new Timer();
                }
                this.mVibrateStart = new Date().getTime();
                this.mVibrateTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (AmharicIME.this.mVibrateStart == 0L) {
                            this.cancel();
                            AmharicIME.this.mVibrateTimer.purge();
                        }
                        else if (new Date().getTime() > AmharicIME.this.mVibrateStart + AmharicIME.this.mVibrateDuration) {
                            AmharicIME.access$4(AmharicIME.this, 0L);
                            AmharicIME.this.mVibrator.cancel();
                            this.cancel();
                            AmharicIME.this.mVibrateTimer.purge();
                        }
                    }
                }, this.mVibrateDuration, 1L);
            }
        }
    }
    
    protected void dump(final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        super.dump(fileDescriptor, printWriter, array);
        final PrintWriterPrinter printWriterPrinter = new PrintWriterPrinter(printWriter);
        ((Printer)printWriterPrinter).println("AmharicIME state :");
        ((Printer)printWriterPrinter).println("  Keyboard mode = " + this.mKeyboardSwitcher.getKeyboardMode());
        ((Printer)printWriterPrinter).println("  mCapsLock=" + this.mCapsLock);
        ((Printer)printWriterPrinter).println("  mComposing=" + this.mComposing.toString());
        ((Printer)printWriterPrinter).println("  mPredictionOn=" + this.mPredictionOn);
        ((Printer)printWriterPrinter).println("  mCorrectionMode=" + this.mCorrectionMode);
        ((Printer)printWriterPrinter).println("  mPredicting=" + this.mPredicting);
        ((Printer)printWriterPrinter).println("  mAutoCorrectOn=" + this.mAutoCorrectOn);
        ((Printer)printWriterPrinter).println("  mAutoSpace=" + this.mAutoSpace);
        ((Printer)printWriterPrinter).println("  mCompletionOn=" + this.mCompletionOn);
        ((Printer)printWriterPrinter).println("  TextEntryState.state=" + TextEntryState.getState());
        ((Printer)printWriterPrinter).println("  mSoundOn=" + this.mSoundOn);
        ((Printer)printWriterPrinter).println("  mVibrateOn=" + this.mVibrateOn);
        ((Printer)printWriterPrinter).println("  mKeyboardLayout=" + this.mKeyboardLayout);
    }
    
    public void hideWindow() {
        if (this.mOptionsDialog != null && this.mOptionsDialog.isShowing()) {
            this.mOptionsDialog.dismiss();
            this.mOptionsDialog = null;
        }
        super.hideWindow();
        TextEntryState.endSession();
    }
    
    public void onComputeInsets(final InputMethodService.Insets inputMethodServiceInsets) {
        super.onComputeInsets(inputMethodServiceInsets);
        if (!this.isFullscreenMode()) {
            inputMethodServiceInsets.contentTopInsets = inputMethodServiceInsets.visibleTopInsets;
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (configuration.orientation != this.mOrientation) {
            this.commitTyped(this.getCurrentInputConnection());
            this.mOrientation = configuration.orientation;
        }
        if (this.mKeyboardSwitcher == null) {
            this.mKeyboardSwitcher = new KeyboardSwitcher(this);
        }
        this.mKeyboardSwitcher.makeKeyboards(true);
        super.onConfigurationChanged(configuration);
    }
    
    public void onCreate() {
        super.onCreate();
        this.mKeyboardSwitcher = new KeyboardSwitcher(this);
        this.mOrientation = this.getResources().getConfiguration().orientation;
        this.mVibrateDuration = this.getResources().getInteger(2131427328);
        this.registerReceiver(this.mReceiver, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
    }
    
    public View onCreateCandidatesView() {
        this.mKeyboardSwitcher.makeKeyboards(true);
        this.setCandidatesViewShown(false);
        return null;
    }
    
    public View onCreateInputView() {
        this.mSkin = PreferenceManager.getDefaultSharedPreferences((Context)this).getString("skin", "input_standard");
        boolean b = true;
        int n;
        if ("input_htc".equals(this.mSkin)) {
            n = 2130903049;
        }
        else if ("input_iphone".equals(this.mSkin)) {
            n = 2130903051;
        }
        else if ("input_light".equals(this.mSkin)) {
            n = 2130903053;
        }
        else {
            n = 2130903055;
            b = false;
        }
        this.mLastSkin = this.mSkin;
        this.mInputView = (AmharicKeyboardView)this.getLayoutInflater().inflate(n, (ViewGroup)null);
        this.mKeyboardSwitcher.setInputView(this.mInputView);
        this.mKeyboardSwitcher.makeKeyboards(true);
        this.mInputView.setOnKeyboardActionListener((KeyboardView.OnKeyboardActionListener)this);
        this.mKeyboardSwitcher.setKeyboardType(this.mKeyboardType);
        this.mKeyboardSwitcher.setKeyboardLayout(this.mKeyboardLayout);
        this.mKeyboardSwitcher.setKeyboardModeChangeIcons(1, 0, b);
        return (View)this.mInputView;
    }
    
    public void onDestroy() {
        this.unregisterReceiver(this.mReceiver);
        super.onDestroy();
    }
    
    public void onDisplayCompletions(final CompletionInfo[] array) {
        if (this.mCompletionOn) {
            final ArrayList<CharSequence> list = new ArrayList<CharSequence>();
            int n = 0;
            while (true) {
                int length;
                if (array != null) {
                    length = array.length;
                }
                else {
                    length = 0;
                }
                if (n >= length) {
                    break;
                }
                final CompletionInfo completionInfo = array[n];
                if (completionInfo != null) {
                    list.add(completionInfo.getText());
                }
                ++n;
            }
            this.mBestWord = null;
            while (true) {
                Label_0098: {
                    if (this.isCandidateStripVisible()) {
                        break Label_0098;
                    }
                    final boolean mCompletionOn = this.mCompletionOn;
                    final boolean candidatesViewShown = false;
                    if (mCompletionOn) {
                        break Label_0098;
                    }
                    this.setCandidatesViewShown(candidatesViewShown);
                    return;
                }
                final boolean candidatesViewShown = true;
                continue;
            }
        }
    }
    
    public void onFinishInput() {
        super.onFinishInput();
        if (this.mInputView != null) {
            this.mInputView.closing();
        }
    }
    
    public void onKey(final int n, final int[] array) {
        final long uptimeMillis = SystemClock.uptimeMillis();
        if (n != -5 || uptimeMillis > 200L + this.mLastKeyTime) {
            this.mDeleteCount = 0;
        }
        switch (n) {
            default: {
                if (AmharicIME.mGeezState) {
                    this.handleGeezCharacter(n, array);
                    this.mJustRevertedSeparator = null;
                    this.mLastKeyTime = uptimeMillis;
                    return;
                }
                this.handleCharacter(n, array);
                this.mJustRevertedSeparator = null;
                break;
            }
            case -8080: {
                this.toggleGeezState();
                break;
            }
            case -5: {
                this.handleBackspace();
                ++this.mDeleteCount;
                break;
            }
            case -1: {
                this.handleShift();
                break;
            }
            case -3: {
                if (this.mOptionsDialog == null || !this.mOptionsDialog.isShowing()) {
                    this.handleClose();
                    break;
                }
                break;
            }
            case -100: {
                this.showOptionsMenu();
                break;
            }
            case -101: {
                if (this.mCapsLock) {
                    this.handleShift();
                    break;
                }
                this.toggleCapsLock();
                break;
            }
            case -2: {
                this.changeKeyboardMode();
                break;
            }
        }
        this.mLastKeyTime = uptimeMillis;
        this.geezime.reset();
        this.mKeyboardSwitcher.onKey(n);
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        switch (n) {
            case 4: {
                if (keyEvent.getRepeatCount() == 0 && this.mInputView != null && this.mInputView.handleBack()) {
                    return true;
                }
                break;
            }
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        switch (n) {
            case 19:
            case 20:
            case 21:
            case 22: {
                if (this.mInputView != null && this.mInputView.isShown() && this.mInputView.isShifted()) {
                    final KeyEvent keyEvent2 = new KeyEvent(keyEvent.getDownTime(), keyEvent.getEventTime(), keyEvent.getAction(), keyEvent.getKeyCode(), keyEvent.getRepeatCount(), keyEvent.getDeviceId(), keyEvent.getScanCode(), 65);
                    final InputConnection currentInputConnection = this.getCurrentInputConnection();
                    if (currentInputConnection != null) {
                        currentInputConnection.sendKeyEvent(keyEvent2);
                    }
                    return true;
                }
                break;
            }
        }
        return super.onKeyUp(n, keyEvent);
    }
    
    public void onPress(final int lastKeyPressed) {
        this.vibrate();
        this.playKeyClick(lastKeyPressed);
        this.lastKeyPressed = lastKeyPressed;
    }
    
    public void onRelease(final int n) {
    }
    
    public void onStartInputView(final EditorInfo editorInfo, final boolean b) {
        boolean b2 = true;
        if (this.mInputView == null) {
            return;
        }
        this.loadSettings();
        if (this.mSkin != null && !this.mSkin.equals(this.mLastSkin)) {
            this.setInputView(this.onCreateInputView());
        }
        this.mKeyboardSwitcher.setKeyboardType(this.mKeyboardType);
        this.mKeyboardSwitcher.setKeyboardLayout(this.mKeyboardLayout);
        this.mKeyboardSwitcher.makeKeyboards(false);
        TextEntryState.newSession((Context)this);
        this.mPredictionOn = false;
        this.mCompletionOn = false;
        this.mCapsLock = false;
        switch (0xF & editorInfo.inputType) {
            default: {
                this.mKeyboardSwitcher.setKeyboardMode(b2 ? 1 : 0, editorInfo.imeOptions);
                this.updateShiftKeyState(editorInfo);
                break;
            }
            case 2:
            case 4: {
                this.mKeyboardSwitcher.setKeyboardMode(2, editorInfo.imeOptions);
                break;
            }
            case 3: {
                this.mKeyboardSwitcher.setKeyboardMode(3, editorInfo.imeOptions);
                break;
            }
            case 1: {
                this.mKeyboardSwitcher.setKeyboardMode(b2 ? 1 : 0, editorInfo.imeOptions);
                this.mPredictionOn = b2;
                final int n = 0xFF0 & editorInfo.inputType;
                if (n == 128 || n == 144) {
                    this.mPredictionOn = false;
                }
                if (n == 32 || n == 96) {
                    this.mAutoSpace = false;
                }
                else {
                    this.mAutoSpace = b2;
                }
                if (n == 32) {
                    this.mPredictionOn = false;
                    this.mKeyboardSwitcher.setKeyboardMode(5, editorInfo.imeOptions);
                }
                else if (n == 16) {
                    this.mPredictionOn = false;
                    this.mKeyboardSwitcher.setKeyboardMode(4, editorInfo.imeOptions);
                }
                else if (n == 64) {
                    this.mKeyboardSwitcher.setKeyboardMode(6, editorInfo.imeOptions);
                }
                else if (n == 176) {
                    this.mPredictionOn = false;
                }
                else if (n == 160) {
                    final int inputType = editorInfo.inputType;
                }
                if ((0x8000 & editorInfo.inputType) == 0x0) {
                    final int inputType2 = editorInfo.inputType;
                }
                if ((0x10000 & editorInfo.inputType) != 0x0) {
                    this.mPredictionOn = false;
                    this.mCompletionOn = (this.isFullscreenMode() && b2);
                }
                this.updateShiftKeyState(editorInfo);
                break;
            }
        }
        this.mInputView.closing();
        this.mComposing.setLength(0);
        this.mPredicting = false;
        this.mDeleteCount = 0;
        this.setCandidatesViewShown(false);
        this.mInputView.setProximityCorrectionEnabled(b2);
        if (!this.mPredictionOn || this.mCorrectionMode <= 0) {
            b2 = false;
        }
        this.mPredictionOn = b2;
        this.drawGeezState();
    }
    
    public void onText(final CharSequence charSequence) {
        final InputConnection currentInputConnection = this.getCurrentInputConnection();
        if (currentInputConnection == null) {
            return;
        }
        currentInputConnection.beginBatchEdit();
        if (this.mPredicting) {
            this.commitTyped(currentInputConnection);
        }
        currentInputConnection.commitText(charSequence, 1);
        currentInputConnection.endBatchEdit();
        this.updateShiftKeyState(this.getCurrentInputEditorInfo());
        this.mJustRevertedSeparator = null;
    }
    
    public void onUpdateSelection(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        super.onUpdateSelection(n, n2, n3, n4, n5, n6);
        if (this.mComposing.length() > 0 && this.mPredicting && (n3 != n6 || n4 != n6)) {
            this.mComposing.setLength(0);
            this.mPredicting = false;
            TextEntryState.reset();
            final InputConnection currentInputConnection = this.getCurrentInputConnection();
            if (currentInputConnection != null) {
                currentInputConnection.finishComposingText();
            }
        }
        else if (!this.mPredicting && !this.mJustAccepted && TextEntryState.getState() == 3) {
            TextEntryState.reset();
        }
        this.mJustAccepted = false;
        this.postUpdateShiftKeyState();
    }
    
    public boolean preferCapitalization() {
        return this.mWord.isCapitalized();
    }
    
    public void revertLastWord(final boolean b) {
        final int length = this.mComposing.length();
        if (!this.mPredicting && length > 0) {
            final InputConnection currentInputConnection = this.getCurrentInputConnection();
            this.mPredicting = true;
            currentInputConnection.beginBatchEdit();
            this.mJustRevertedSeparator = currentInputConnection.getTextBeforeCursor(1, 0);
            if (b) {
                currentInputConnection.deleteSurroundingText(1, 0);
            }
            int mCommittedLength = this.mCommittedLength;
            final CharSequence textBeforeCursor = currentInputConnection.getTextBeforeCursor(this.mCommittedLength, 0);
            if (textBeforeCursor != null && textBeforeCursor.length() > 0) {
                --mCommittedLength;
            }
            currentInputConnection.deleteSurroundingText(mCommittedLength, 0);
            currentInputConnection.setComposingText((CharSequence)this.mComposing, 1);
            TextEntryState.backspace();
            currentInputConnection.endBatchEdit();
            return;
        }
        this.sendDownUpKeyEvents(67);
        this.mJustRevertedSeparator = null;
    }
    
    public void setCandidatesViewShown(final boolean candidatesViewShown) {
        if (this.onEvaluateInputViewShown()) {
            super.setCandidatesViewShown(candidatesViewShown);
        }
    }
    
    public void swipe(final int n) {
        int n2 = -1;
        int mDictionaryManually = 1;
        if (this.mSwipeEnabled) {
            switch (n) {
                case 1: {
                    if (this.lastKeyPressed > 0) {
                       // this.mInputView.setShifted(!this.mInputView.isShifted() && mDictionaryManually);
                        final int lastKeyPressed = this.lastKeyPressed;
                        final int[] array = new int[mDictionaryManually];
                        array[0] = this.lastKeyPressed;
                        this.onKey(lastKeyPressed, array);
                        return;
                    }
                    break;
                }
                case 2: {
                    if ((96 < this.lastKeyPressed && this.lastKeyPressed < 123) || this.validKeyCodes.contains(this.lastKeyPressed)) {
                        final int intValue = this.letterSymbolArray.get(this.lastKeyPressed);
                        final int[] array2 = new int[mDictionaryManually];
                        array2[0] = this.letterSymbolArray.get(this.lastKeyPressed);
                        this.onKey(intValue, array2);
                        return;
                    }
                    break;
                }
                case 3: {
                    this.handleBackspace();
                }
                case 4: {
                    final int[] array3 = new int[mDictionaryManually];
                    this.onKey(array3[0] = 32, array3);
                }
                case 6: {
                    this.handleClose();
                }
                case 7:
                case 8: {
                    int n3 = 0;
                    final String[] storedValue = ListPreferenceMultiSelect.parseStoredValue(this.mSwipeKeyboardLayout);
                    for (int i = 0; i < storedValue.length; ++i) {
                        if (this.mKeyboardLayout == Integer.parseInt(storedValue[i].toString())) {
                            n3 = i;
                        }
                    }
                    if (n == 7) {
                        mDictionaryManually = n2;
                    }
                    int n4 = n3 + mDictionaryManually;
                    if (n4 >= storedValue.length) {
                        n4 = 0;
                    }
                    else if (n4 < 0) {
                        n4 = -1 + storedValue.length;
                    }
                    this.changePreference("keyboard_layout", storedValue[n4].toString());
                }
                case 9:
                case 10: {
                    int n5 = 0;
                    final String[] storedValue2 = ListPreferenceMultiSelect.parseStoredValue(this.mSwipeDictionary);
                    for (int j = 0; j < storedValue2.length; ++j) {
                        if (this.mDictionary.equals(storedValue2[j])) {
                            n5 = j;
                        }
                    }
                    if (n != 9) {
                        n2 = mDictionaryManually;
                    }
                    int n6 = n5 + n2;
                    if (n6 >= storedValue2.length) {
                        n6 = 0;
                    }
                    else if (n6 < 0) {
                        n6 = -1 + storedValue2.length;
                    }
                    if (!this.mDictionaryManually) {
                        this.changePreference("dictionary_manually", this.mDictionaryManually = (mDictionaryManually != 0));
                    }
                    this.changePreference("dictionary", storedValue2[n6].toString());
                }
                case 11: {
                    this.getCurrentInputConnection().sendKeyEvent(new KeyEvent(0, 21));
                }
                case 12: {
                    this.getCurrentInputConnection().sendKeyEvent(new KeyEvent(0, 22));
                }
                case 13: {
                    this.getCurrentInputConnection().sendKeyEvent(new KeyEvent(0, 19));
                }
                case 14: {
                    this.getCurrentInputConnection().sendKeyEvent(new KeyEvent(0, 20));
                }
            }
        }
    }
    
    public void swipeDown() {
        this.swipe(this.mSwipeDown);
    }
    
    public void swipeLeft() {
        this.swipe(this.mSwipeLeft);
    }
    
    public void swipeRight() {
        this.swipe(this.mSwipeRight);
    }
    
    public void swipeUp() {
        this.swipe(this.mSwipeUp);
    }
    
    public void updateShiftKeyState(final EditorInfo editorInfo) {
        final InputConnection currentInputConnection = this.getCurrentInputConnection();
        if (editorInfo != null && this.mInputView != null && this.mKeyboardSwitcher.isAlphabetMode() && currentInputConnection != null) {
            if (!AmharicIME.mGeezState) {
                final EditorInfo currentInputEditorInfo = this.getCurrentInputEditorInfo();
                final boolean mAutoCap = this.mAutoCap;
                int cursorCapsMode = 0;
                if (mAutoCap) {
                    cursorCapsMode = 0;
                    if (currentInputEditorInfo != null) {
                        final int inputType = currentInputEditorInfo.inputType;
                        cursorCapsMode = 0;
                        if (inputType != 0) {
                            cursorCapsMode = currentInputConnection.getCursorCapsMode(editorInfo.inputType);
                        }
                    }
                }
                this.mInputView.setShifted(this.mCapsLock || cursorCapsMode != 0);
                return;
            }
            this.mInputView.setShifted(this.mCapsLock);
        }
    }
}
