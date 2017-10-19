package com.picturediary;

import android.widget.TextView;

/**
 *
 */

public class TextViewInfo {

    private int textViewId = 0;
    private TextView textview = null;
    private int dayNum = 0;
    private boolean isToday = false;
    private boolean isSelect = false;

    public TextViewInfo(int controlId){
        this.setTextViewId(controlId);
    }

    public int getTextViewId() {
        return textViewId;
    }

    public void setTextViewId(int textViewId) {
        this.textViewId = textViewId;
    }

    public TextView getTextview() {
        return textview;
    }

    public void setTextview(TextView textview) {
        this.textview = textview;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
