package com.ethiopian.amharic.professional;

class CoreIME
{
    private int committed;
    private String composed;
    private byte current_kbd;
    private boolean erasePrev;
    private AmharicModel geezmodel;
    
    public CoreIME() {
        this((byte)1);
    }
    
    public CoreIME(final byte current_kbd) {
        super();
        this.composed = "";
        this.committed = 0;
        this.erasePrev = false;
        this.geezmodel = AmharicModel.getInstance(current_kbd);
        this.current_kbd = current_kbd;
    }
    
    private void MapInput(final char committed) {
        if (this.composed.length() == 0) {
            this.composed = new StringBuilder().append(committed).toString();
            final Integer value = this.geezmodel.get(this.composed, this.current_kbd);
            if (value != null) {
                this.committed = value;
                return;
            }
            this.committed = committed;
        }
        else if (committed == '\'' && this.current_kbd == 2) {
            if (this.composed.equals("'")) {
                this.composed = "";
                this.committed = 39;
                return;
            }
            this.committed = 0;
            this.composed = "'";
        }
        else {
            this.composed = String.valueOf(this.composed) + committed;
            final Integer value2 = this.geezmodel.get(this.composed, this.current_kbd);
            if (value2 != null) {
                this.committed = value2;
                this.erasePrev = true;
                return;
            }
            this.composed = new StringBuilder().append(committed).toString();
            final Integer value3 = this.geezmodel.get(this.composed, this.current_kbd);
            if (value3 != null) {
                this.committed = value3;
                return;
            }
            this.committed = committed;
        }
    }
    
    public char MapToGeez(final char c) {
        this.MapInput(this.geezmodel.checkCaseInterchange(c, this.current_kbd));
        return this.getOutput();
    }
    
    public void clearOutput() {
        this.committed = 0;
    }
    
    public char getOutput() {
        return (char)this.committed;
    }
    
    public void reset() {
        this.composed = "";
        this.erasePrev = false;
        this.clearOutput();
    }
    
    public void setKeyboardType(final byte current_kbd) {
        this.geezmodel = AmharicModel.getInstance(current_kbd);
        this.current_kbd = current_kbd;
    }
    
    public void shouldErasePrev(final boolean erasePrev) {
        this.erasePrev = erasePrev;
    }
    
    public boolean shouldErasePrev() {
        return this.erasePrev;
    }
}
