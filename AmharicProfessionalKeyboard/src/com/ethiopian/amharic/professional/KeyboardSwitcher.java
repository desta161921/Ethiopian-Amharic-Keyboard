package com.ethiopian.amharic.professional;

import android.graphics.drawable.*;
import android.content.*;
import java.util.*;
import android.inputmethodservice.*;

public class KeyboardSwitcher
{
    public static final int KEYBOARDMODE_EMAIL = 2131492888;
    public static final int KEYBOARDMODE_IM = 2131492889;
    public static final int KEYBOARDMODE_NORMAL = 2131492886;
    public static final int KEYBOARDMODE_URL = 2131492887;
    public static final int MODE_EMAIL = 5;
    public static final int MODE_IM = 6;
    public static final int MODE_PHONE = 3;
    public static final int MODE_SYMBOLS = 2;
    public static final int MODE_TEXT = 1;
    public static final int MODE_TEXT_ALPHA = 1;
    public static final int MODE_TEXT_COUNT = 2;
    public static final int MODE_TEXT_QWERTY = 0;
    public static final int MODE_URL = 4;
    private static final int SYMBOLS_MODE_STATE_BEGIN = 1;
    private static final int SYMBOLS_MODE_STATE_NONE = 0;
    private static final int SYMBOLS_MODE_STATE_SYMBOL = 2;
    public static final int TYPE_COMPACT = 1;
    public static final int TYPE_PHONE = 2;
    public static final int TYPE_QWERTY=0;
    Drawable geez_icon;
    private boolean mChangeIcons;
    AmharicIME mContext;
    private KeyboardId mCurrentId;
    private int mImeOptions;
    AmharicKeyboardView mInputView;
    private boolean mIsSymbols;
    private int mKeyboardLayout;
    private int mKeyboardType;
    private Map<KeyboardId, AmharicKeyboard> mKeyboards;
    private int mLastDisplayWidth;
    private int mMode;
    private boolean mPreferSymbols;
    private KeyboardId mSymbolsId;
    private int mSymbolsModeState;
    private KeyboardId mSymbolsShiftedId;
    private int mTextMode;
    
    KeyboardSwitcher(final AmharicIME mContext) {
        super();
        this.mTextMode = 0;
        this.mSymbolsModeState = 0;
        this.geez_icon = null;
        this.mContext = mContext;
        this.mKeyboards = new HashMap<KeyboardId, AmharicKeyboard>();
        this.mSymbolsId = new KeyboardId(2130968590);
        this.mSymbolsShiftedId = new KeyboardId(2130968591);
    }
    
    private AmharicKeyboard getKeyboard(final KeyboardId keyboardId) {
        if (!this.mKeyboards.containsKey(keyboardId)) {
            final AmharicKeyboard amharicKeyboard = new AmharicKeyboard((Context)this.mContext, keyboardId.mXml, keyboardId.mMode, this.mChangeIcons);
            if (keyboardId.mEnableShiftLock) {
                amharicKeyboard.enableShiftLock();
            }
            if (this.geez_icon != null) {
                amharicKeyboard.setGeezIcon(this.geez_icon);
            }
            this.mKeyboards.put(keyboardId, amharicKeyboard);
        }
        return this.mKeyboards.get(keyboardId);
    }
    
    private KeyboardId getKeyboardId(final int n, final int n2, final boolean b) {
        if (b) {
            if (n == 3) {
                return new KeyboardId(2130968579);
            }
            return new KeyboardId(2130968590);
        }
        else {
            if (this.mKeyboardType == 2) {
                return new KeyboardId(2130968577, 2131492886, true);
            }
            int n3 = 0;
            switch (this.mKeyboardLayout) {
                default: {
                    n3 = 2130968587;
                    break;
                }
                case 0:
                case 1:
                case 2:
                case 3: {
                    n3 = 2130968583;
                    break;
                }
            }
            switch (n) {
                case 1: {
                    if (this.mTextMode == 0) {
                        return new KeyboardId(n3, 2131492886, true);
                    }
                    if (this.mTextMode == 1) {
                        return new KeyboardId(2130968576, 2131492886, true);
                    }
                    break;
                }
                case 2: {
                    return new KeyboardId(2130968590);
                }
                case 3: {
                    return new KeyboardId(2130968578);
                }
                case 4: {
                    return new KeyboardId(n3, 2131492887, true);
                }
                case 5: {
                    return new KeyboardId(n3, 2131492888, true);
                }
                case 6: {
                    return new KeyboardId(n3, 2131492889, true);
                }
            }
            return null;
        }
    }
    
    public void drawGeezState(final Drawable geez_icon) {
        this.geez_icon = geez_icon;
        final Iterator<AmharicKeyboard> iterator = this.mKeyboards.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().setGeezIcon(this.geez_icon);
        }
    }
    
    int getKeyboardMode() {
        return this.mMode;
    }
    
    int getTextMode() {
        return this.mTextMode;
    }
    
    int getTextModeCount() {
        return 2;
    }
    
    boolean isAlphabetMode() {
        final KeyboardId mCurrentId = this.mCurrentId;
        return mCurrentId.mMode == 2131492886 || mCurrentId.mMode == 2131492887 || mCurrentId.mMode == 2131492888 || mCurrentId.mMode == 2131492889;
    }
    
    boolean isTextMode() {
        return this.mMode == 1;
    }
    
    void makeKeyboards(final boolean b) {
        if (b) {
            this.mKeyboards.clear();
        }
        final int maxWidth = this.mContext.getMaxWidth();
        if (maxWidth == this.mLastDisplayWidth) {
            return;
        }
        this.mLastDisplayWidth = maxWidth;
        if (!b) {
            this.mKeyboards.clear();
        }
        this.mSymbolsId = new KeyboardId(2130968590);
        this.mSymbolsShiftedId = new KeyboardId(2130968591);
    }
    
    boolean onKey(final int n) {
        switch (this.mSymbolsModeState) {
            case 1: {
                if (n != 32 && n != 10 && n > 0) {
                    this.mSymbolsModeState = 2;
                    break;
                }
                break;
            }
            case 2: {
                if (n == 10 || n == 32) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    void setInputView(final AmharicKeyboardView mInputView) {
        this.mInputView = mInputView;
    }
    
    void setKeyboardLayout(final int mKeyboardLayout) {
        this.mKeyboardLayout = mKeyboardLayout;
    }
    
    void setKeyboardMode(final int n, final int n2) {
        this.setKeyboardModeChangeIcons(n, n2, this.mChangeIcons);
    }
    
    void setKeyboardMode(final int n, final int n2, final boolean b) {
        this.setKeyboardMode(n, n2, b, this.mChangeIcons);
    }
    
    void setKeyboardMode(final int mMode, final int mImeOptions, final boolean mIsSymbols, final boolean mChangeIcons) {
        this.mChangeIcons = mChangeIcons;
        this.mMode = mMode;
        this.mImeOptions = mImeOptions;
        this.mIsSymbols = mIsSymbols;
        this.mInputView.setPreviewEnabled(true);
        final KeyboardId keyboardId = this.getKeyboardId(mMode, mImeOptions, mIsSymbols);
        final AmharicKeyboard keyboard = this.getKeyboard(keyboardId);
        if (mMode == 3) {
            this.mInputView.setPhoneKeyboard(keyboard);
            this.mInputView.setPreviewEnabled(false);
        }
        this.mCurrentId = keyboardId;
        this.mInputView.setKeyboard(keyboard);
        keyboard.setShifted(false);
        keyboard.setShiftLocked(keyboard.isShiftLocked());
        keyboard.setImeOptions(this.mContext.getResources(), this.mMode, mImeOptions, mChangeIcons);
    }
    
    void setKeyboardModeChangeIcons(final int n, final int n2, final boolean mChangeIcons) {
        boolean b = true;
        this.mChangeIcons = mChangeIcons;
        this.mSymbolsModeState = 0;
        boolean mPreferSymbols = false;
        if (n == 2) {
            mPreferSymbols = b;
        }
        this.mPreferSymbols = mPreferSymbols;
        if (n != 2) {
            b = (n != 0);
        }
        this.setKeyboardMode(b ? 1 : 0, n2, this.mPreferSymbols, mChangeIcons);
    }
    
    void setKeyboardType(final int mKeyboardType) {
        this.mKeyboardType = mKeyboardType;
    }
    
    void setTextMode(final int mTextMode) {
        if (mTextMode < 2 && mTextMode >= 0) {
            this.mTextMode = mTextMode;
        }
        if (this.isTextMode()) {
            this.setKeyboardMode(1, this.mImeOptions);
        }
    }
    
    void toggleShift() {
        if (this.mCurrentId.equals(this.mSymbolsId)) {
            final AmharicKeyboard keyboard = this.getKeyboard(this.mSymbolsId);
            final AmharicKeyboard keyboard2 = this.getKeyboard(this.mSymbolsShiftedId);
            keyboard.setShifted(true);
            this.mCurrentId = this.mSymbolsShiftedId;
            this.mInputView.setKeyboard(keyboard2);
            keyboard2.setShifted(true);
            keyboard2.setImeOptions(this.mContext.getResources(), this.mMode, this.mImeOptions);
        }
        else if (this.mCurrentId.equals(this.mSymbolsShiftedId)) {
            final AmharicKeyboard keyboard3 = this.getKeyboard(this.mSymbolsId);
            this.getKeyboard(this.mSymbolsShiftedId).setShifted(false);
            this.mCurrentId = this.mSymbolsId;
            this.mInputView.setKeyboard(this.getKeyboard(this.mSymbolsId));
            keyboard3.setShifted(false);
            keyboard3.setImeOptions(this.mContext.getResources(), this.mMode, this.mImeOptions);
        }
    }
    
    void toggleSymbols() {
        this.setKeyboardMode(this.mMode, this.mImeOptions, !this.mIsSymbols);
        if (this.mIsSymbols && !this.mPreferSymbols) {
            this.mSymbolsModeState = 1;
            return;
        }
        this.mSymbolsModeState = 0;
    }
    
    private static class KeyboardId
    {
        public boolean mEnableShiftLock;
        public int mMode;
        public int mXml;
        
        public KeyboardId(final int n) {
            this(n, 0, false);
        }
        
        public KeyboardId(final int mXml, final int mMode, final boolean mEnableShiftLock) {
            super();
            this.mXml = mXml;
            this.mMode = mMode;
            this.mEnableShiftLock = mEnableShiftLock;
        }
        
        public boolean equals(final KeyboardId keyboardId) {
            return keyboardId.mXml == this.mXml && keyboardId.mMode == this.mMode;
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof KeyboardId && this.equals((KeyboardId)o);
        }
        
        @Override
        public int hashCode() {
            final int n = (1 + this.mXml) * (1 + this.mMode);
            int n2;
            if (this.mEnableShiftLock) {
                n2 = 2;
            }
            else {
                n2 = 1;
            }
            return n2 * n;
        }
    }
}
