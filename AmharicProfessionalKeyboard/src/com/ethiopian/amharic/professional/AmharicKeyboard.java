package com.ethiopian.amharic.professional;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;

public class AmharicKeyboard extends Keyboard
{
    private static final int SHIFT_LOCKED = 2;
    private static final int SHIFT_OFF = 0;
    private static final int SHIFT_ON = 1;
    static int sSpacebarVerticalCorrection;
    private boolean mDarkIcons;
    private Keyboard.Key mDelKey;
    private Keyboard.Key mEnterKey;
    private Keyboard.Key mGeezKey;
    private Keyboard.Key[] mNumbersKeys;
    private Drawable mOldShiftIcon;
    private Keyboard.Key mPoundKey;
    private Keyboard.Key mShiftKey;
    private Drawable mShiftLockIcon;
    private Drawable mShiftLockPreviewIcon;
    private int mShiftState;
    private Keyboard.Key mSpaceKey;
    private Keyboard.Key mStarKey;
    private Keyboard.Key mSymbolsKey;
    
    public AmharicKeyboard(final Context context, final int n, final int n2, final boolean mDarkIcons) {
        super(context, n, n2);
        this.mShiftState = 0;
        this.mDarkIcons = mDarkIcons;
        final Resources resources = context.getResources();
        int n3;
        if (mDarkIcons) {
            n3 = 2130837617;
        }
        else {
            n3 = 2130837616;
        }
        this.mShiftLockIcon = resources.getDrawable(n3);
        (this.mShiftLockPreviewIcon = resources.getDrawable(2130837582)).setBounds(0, 0, this.mShiftLockPreviewIcon.getIntrinsicWidth(), this.mShiftLockPreviewIcon.getIntrinsicHeight());
        AmharicKeyboard.sSpacebarVerticalCorrection = resources.getDimensionPixelOffset(2131296259);
    }
    
    public AmharicKeyboard(final Context context, final int n, final CharSequence charSequence, final int n2, final int n3) {
        super(context, n, charSequence, n2, n3);
        this.mShiftState = 0;
    }
    
    public AmharicKeyboard(final Context context, final int n, final boolean b) {
        this(context, n, 0, b);
    }
    
    protected Keyboard.Key createKeyFromXml(final Resources resources, final Keyboard.Row keyboardRow, final int n, final int n2, final XmlResourceParser xmlResourceParser) {
        final GeezKey mPoundKey = new GeezKey(resources, keyboardRow, n, n2, xmlResourceParser);
        if (mPoundKey.codes[0] == -8080) {
            this.mGeezKey = mPoundKey;
        }
        else {
            if (mPoundKey.codes[0] == 10) {
                return this.mEnterKey = mPoundKey;
            }
            if (mPoundKey.codes[0] == -5) {
                return this.mDelKey = mPoundKey;
            }
            if (mPoundKey.codes[0] == 32) {
                return this.mSpaceKey = mPoundKey;
            }
            if (mPoundKey.codes[0] == -2) {
                return this.mSymbolsKey = mPoundKey;
            }
            if (mPoundKey.codes[0] > 47 && mPoundKey.codes[0] < 58) {
                if (this.mNumbersKeys == null) {
                    this.mNumbersKeys = new Keyboard.Key[10];
                }
                return this.mNumbersKeys[-48 + mPoundKey.codes[0]] = mPoundKey;
            }
            if (mPoundKey.codes[0] == 42) {
                return this.mStarKey = mPoundKey;
            }
            if (mPoundKey.codes[0] == 35) {
                return this.mPoundKey = mPoundKey;
            }
        }
        return mPoundKey;
    }
    
    void enableShiftLock() {
        final int shiftKeyIndex = this.getShiftKeyIndex();
        if (shiftKeyIndex >= 0) {
            this.mShiftKey = (Keyboard.Key)this.getKeys().get(shiftKeyIndex);
            if (this.mShiftKey instanceof GeezKey) {
                ((GeezKey)this.mShiftKey).enableShiftLock();
            }
            this.mOldShiftIcon = this.mShiftKey.icon;
        }
    }
    
    boolean isShiftLocked() {
        return this.mShiftState == 2;
    }
    
    public boolean isShifted() {
        if (this.mShiftKey != null) {
            return this.mShiftState != 0;
        }
        return super.isShifted();
    }
    
    void setGeezIcon(final Drawable icon) {
        if (this.mGeezKey != null) {
            this.mGeezKey.icon = icon;
        }
    }
    
    void setImeOptions(final Resources resources, final int n, final int n2) {
        this.setImeOptions(resources, n, n2, this.mDarkIcons);
    }
    
    void setImeOptions(final Resources resources, final int n, final int n2, final boolean mDarkIcons) {
        this.mDarkIcons = mDarkIcons;
        if (this.mEnterKey != null) {
            this.mEnterKey.text = null;
            switch (0x400000FF & n2) {
                default: {
                    this.mEnterKey.iconPreview = resources.getDrawable(2130837579);
                    final Keyboard.Key mEnterKey = this.mEnterKey;
                    int n3;
                    if (mDarkIcons) {
                        n3 = 2130837611;
                    }
                    else {
                        n3 = 2130837610;
                    }
                    mEnterKey.icon = resources.getDrawable(n3);
                    this.mEnterKey.label = null;
                    if (n == 6) {
                        this.mEnterKey.text = "\n";
                        break;
                    }
                    break;
                }
                case 2: {
                    this.mEnterKey.iconPreview = null;
                    this.mEnterKey.icon = null;
                    this.mEnterKey.label = resources.getText(2131361944);
                    break;
                }
                case 5: {
                    this.mEnterKey.iconPreview = null;
                    this.mEnterKey.icon = null;
                    this.mEnterKey.label = resources.getText(2131361945);
                    break;
                }
                case 6: {
                    this.mEnterKey.iconPreview = null;
                    this.mEnterKey.icon = null;
                    this.mEnterKey.label = resources.getText(2131361946);
                    break;
                }
                case 3: {
                    this.mEnterKey.iconPreview = resources.getDrawable(2130837580);
                    final Keyboard.Key mEnterKey2 = this.mEnterKey;
                    int n4;
                    if (mDarkIcons) {
                        n4 = 2130837613;
                    }
                    else {
                        n4 = 2130837612;
                    }
                    mEnterKey2.icon = resources.getDrawable(n4);
                    this.mEnterKey.label = null;
                    break;
                }
                case 4: {
                    this.mEnterKey.iconPreview = null;
                    this.mEnterKey.icon = null;
                    this.mEnterKey.label = resources.getText(2131361947);
                    break;
                }
            }
            if (this.mEnterKey.iconPreview != null) {
                this.mEnterKey.iconPreview.setBounds(0, 0, this.mEnterKey.iconPreview.getIntrinsicWidth(), this.mEnterKey.iconPreview.getIntrinsicHeight());
            }
        }
        if (mDarkIcons) {
            if (this.mShiftKey != null) {
                final Keyboard.Key mShiftKey = this.mShiftKey;
                int n5;
                if (this.isShifted()) {
                    n5 = 2130837617;
                }
                else {
                    n5 = 2130837615;
                }
                mShiftKey.icon = resources.getDrawable(n5);
            }
            if (this.mDelKey != null) {
                this.mDelKey.icon = resources.getDrawable(2130837574);
            }
            if (this.mSpaceKey != null) {
                this.mSpaceKey.icon = resources.getDrawable(2130837619);
            }
            this.mOldShiftIcon = resources.getDrawable(2130837615);
            this.mShiftLockIcon = resources.getDrawable(2130837617);
            if (this.mSymbolsKey != null) {
                this.mSymbolsKey.icon = resources.getDrawable(2130837605);
            }
            if (this.mNumbersKeys != null) {
                if (this.mNumbersKeys[0] != null) {
                    this.mNumbersKeys[0].icon = resources.getDrawable(2130837585);
                }
                if (this.mNumbersKeys[1] != null) {
                    this.mNumbersKeys[1].icon = resources.getDrawable(2130837587);
                }
                if (this.mNumbersKeys[2] != null) {
                    this.mNumbersKeys[2].icon = resources.getDrawable(2130837589);
                }
                if (this.mNumbersKeys[3] != null) {
                    this.mNumbersKeys[3].icon = resources.getDrawable(2130837591);
                }
                if (this.mNumbersKeys[4] != null) {
                    this.mNumbersKeys[4].icon = resources.getDrawable(2130837593);
                }
                if (this.mNumbersKeys[5] != null) {
                    this.mNumbersKeys[5].icon = resources.getDrawable(2130837595);
                }
                if (this.mNumbersKeys[6] != null) {
                    this.mNumbersKeys[6].icon = resources.getDrawable(2130837597);
                }
                if (this.mNumbersKeys[7] != null) {
                    this.mNumbersKeys[7].icon = resources.getDrawable(2130837599);
                }
                if (this.mNumbersKeys[8] != null) {
                    this.mNumbersKeys[8].icon = resources.getDrawable(2130837601);
                }
                if (this.mNumbersKeys[9] != null) {
                    this.mNumbersKeys[9].icon = resources.getDrawable(2130837603);
                }
            }
            if (this.mStarKey != null) {
                this.mStarKey.icon = resources.getDrawable(2130837609);
            }
            if (this.mPoundKey != null) {
                this.mPoundKey.icon = resources.getDrawable(2130837607);
            }
        }
    }
    
    void setShiftLocked(final boolean b) {
        if (this.mShiftKey != null) {
            if (!b) {
                this.mShiftKey.on = false;
                this.mShiftKey.icon = this.mShiftLockIcon;
                this.mShiftState = 1;
                return;
            }
            this.mShiftKey.on = true;
            this.mShiftKey.icon = this.mShiftLockIcon;
            this.mShiftState = 2;
        }
    }
    
    public boolean setShifted(final boolean shifted) {
        if (this.mShiftKey != null) {
            boolean b;
            if (!shifted) {
                b = (this.mShiftState != 0);
                this.mShiftState = 0;
                this.mShiftKey.on = false;
                this.mShiftKey.icon = this.mOldShiftIcon;
            }
            else {
                final int mShiftState = this.mShiftState;
                b = false;
                if (mShiftState == 0) {
                    b = (this.mShiftState == 0);
                    this.mShiftState = 1;
                    this.mShiftKey.icon = this.mShiftLockIcon;
                }
            }
            return b;
        }
        return super.setShifted(shifted);
    }
    
    static class GeezKey extends Keyboard.Key
    {
        private boolean mShiftLockEnabled;
        
        public GeezKey(final Resources resources, final Keyboard.Row keyboard$Row, final int n, final int n2, final XmlResourceParser xmlResourceParser) {
            super(resources, keyboard$Row, n, n2, xmlResourceParser);
            if (this.popupCharacters != null && this.popupCharacters.length() == 0) {
                this.popupResId = 0;
            }
        }
        
        void enableShiftLock() {
            this.mShiftLockEnabled = true;
        }
        
        public boolean isInside(int n, int n2) {
            final int n3 = this.codes[0];
            if (n3 == -1 || n3 == -5) {
                n2 -= this.height / 10;
                if (n3 == -1) {
                    n += this.width / 6;
                }
                if (n3 == -5) {
                    n -= this.width / 6;
                }
            }
            else if (n3 == 32) {
                n2 += AmharicKeyboard.sSpacebarVerticalCorrection;
            }
            return super.isInside(n, n2);
        }
        
        public void onReleased(final boolean b) {
            if (!this.mShiftLockEnabled) {
                super.onReleased(b);
                return;
            }
            this.pressed = !this.pressed;
        }
    }
}
