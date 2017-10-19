package com.picturediary;

import java.util.Calendar;

/**
 * カレンダーフィールド作成クラス
 */
public class CalendarInfo {

    private static final int FIRST_DAY = 1;
    private static final int DATE_OF_WEEK = 7;
    private static final int MAX_WEEK_OF_MONTH = 6;

    private int year;
    private int month;
    private int startDay;
    private int lastDate;
    private String[][] calendarMatrix = new String[MAX_WEEK_OF_MONTH][DATE_OF_WEEK];

    /**
     * コンストラクタ
     * @param year 対象年
     * @param month 対象月
     */
    public CalendarInfo(int year, int month){
        this.year = year;
        this.month = month;
        this.setCalendarInfo();
    }

    /**
     * カレンダー情報をフィールドに設定する。
     */
    public void setCalendarInfo(){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        //対象月の開始曜日を取得
        calendar.set(year, month - 1, FIRST_DAY);
        this.startDay = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.MONTH, 1);
        //対象月の最終日を取得
        calendar.add(Calendar.DATE, -1);
        this.lastDate = calendar.get(Calendar.DATE);

        boolean isAddDay = false;
        int dayCount = 1;

        for(int i=0; i<MAX_WEEK_OF_MONTH; i++){
            for(int j=0; j<DATE_OF_WEEK; j++){
                calendarMatrix[i][j] = "";

                //開始曜日の位置から日付セット開始
                if(i == 0 && j == this.startDay - 1){
                    isAddDay = true;
                }

                //日付セット
                if(isAddDay){
                    calendarMatrix[i][j] = "" + dayCount;
                    dayCount++;
                }

                //日付カウンタが最終日を超えたら日付セット終了
                if(dayCount > this.lastDate){
                    isAddDay = false;
                }

            }
        }
    }

    public String[][] getCalendarMatrix() {
        return calendarMatrix;
    }

}
