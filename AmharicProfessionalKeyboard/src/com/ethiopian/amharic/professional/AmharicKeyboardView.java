package com.ethiopian.amharic.professional;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Handler;
import android.util.AttributeSet;

public class AmharicKeyboardView extends KeyboardView
{
    static final boolean DEBUG_AUTO_PLAY = false;
    static final int KEYCODE_OPTIONS = -100;
    static final int KEYCODE_SHIFT_LONGPRESS = -101;
    private static final int MSG_TOUCH_DOWN = 1;
    private static final int MSG_TOUCH_UP = 2;
    private Keyboard.Key[] mAsciiKeys;
    private boolean mDownDelivered;
    Handler mHandler2;
    private Keyboard mPhoneKeyboard;
    private boolean mPlaying;
    private int mStringIndex;
    private String mStringToPlay;
    
    public AmharicKeyboardView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mAsciiKeys = new Keyboard.Key[256];
    }
    
    public AmharicKeyboardView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mAsciiKeys = new Keyboard.Key[256];
    }
    
    private void findKeys()
    {
      List localList = getKeyboard().getKeys();
      for (int i = 0; ; i++)
      {
        if (i >= localList.size())
          return;
        int j = ((Keyboard.Key)localList.get(i)).codes[0];
        if ((j >= 0) && (j <= 255))
          this.mAsciiKeys[j] = ((Keyboard.Key)localList.get(i));
      }
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
    }
    
    protected boolean onLongPress(final Keyboard.Key keyboard$Key) {
        if (keyboard$Key.codes[0] == -8080) {
            //this.getOnKeyboardActionListener().onKey(-100, (int[])null);
            return true;
        }
        if (keyboard$Key.codes[0] == -1) {
            this.getOnKeyboardActionListener().onKey(-101, (int[])null);
            this.invalidate();
            return true;
        }
        if (keyboard$Key.codes[0] == 48 && this.getKeyboard() == this.mPhoneKeyboard) {
            this.getOnKeyboardActionListener().onKey(43, (int[])null);
            return true;
        }
        return super.onLongPress(keyboard$Key);
    }
    
    public void setKeyboard(final Keyboard keyboard) {
        super.setKeyboard(keyboard);
    }
    
    public void setPhoneKeyboard(final Keyboard mPhoneKeyboard) {
        this.mPhoneKeyboard = mPhoneKeyboard;
    }
    
    void startPlaying(final String s) {
    }
}
