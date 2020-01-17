package com.ethiopian.amharic.professional.utils;

import java.util.*;
import java.io.*;

public class WordComposer
{
    private int mCapsCount;
    private ArrayList<int[]> mCodes;
    private boolean mIsCapitalized;
    private String mPreferredWord;
    private StringBuilder mTypedWord;
    
    public WordComposer() {
        super();
        this.mCodes = new ArrayList<int[]>(12);
        this.mTypedWord = new StringBuilder(20);
    }
    
    public void add(final int n, final int[] e) {
        this.mTypedWord.append((char)n);
        this.mCodes.add(e);
        if (Character.isUpperCase((char)n)) {
            ++this.mCapsCount;
        }
    }
    
    public void deleteLast() {
        this.mCodes.remove(-1 + this.mCodes.size());
        final int n = -1 + this.mTypedWord.length();
        final char char1 = this.mTypedWord.charAt(n);
        this.mTypedWord.deleteCharAt(n);
        if (Character.isUpperCase(char1)) {
            --this.mCapsCount;
        }
    }
    
    public int[] getCodesAt(final int index) {
        return this.mCodes.get(index);
    }
    
    public CharSequence getPreferredWord() {
        if (this.mPreferredWord != null) {
            return this.mPreferredWord;
        }
        return this.getTypedWord();
    }
    
    public CharSequence getTypedWord() {
        if (this.mCodes.size() == 0) {
            return null;
        }
        return this.mTypedWord;
    }
    
    public boolean isCapitalized() {
        return this.mIsCapitalized;
    }
    
    public boolean isMostlyCaps() {
        return this.mCapsCount > 1;
    }
    
    public void reset() {
        this.mCodes.clear();
        this.mIsCapitalized = false;
        this.mPreferredWord = null;
        this.mTypedWord.setLength(0);
        this.mCapsCount = 0;
    }
    
    public void setCapitalized(final boolean mIsCapitalized) {
        this.mIsCapitalized = mIsCapitalized;
    }
    
    public void setPreferredWord(final String mPreferredWord) {
        this.mPreferredWord = mPreferredWord;
    }
    
    public int size() {
        return this.mCodes.size();
    }
}
