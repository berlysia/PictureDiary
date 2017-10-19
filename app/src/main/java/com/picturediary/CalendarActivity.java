package com.picturediary;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private Button nMonthButton = null;
    private Button pMonthButton = null;

    private int displayYear;
    private int displayMonth;

    private int nowYear;
    private int nowMonth;
    private int nowDay;

    private ArrayList<TextViewInfo> calInfoList = new ArrayList<TextViewInfo>();

    private static final int DATE_OF_WEEK = 7;
    private static final int MAX_WEEK_OF_MONTH = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initializeCalendar();
    }

    public void initializeCalendar(){
        this.pMonthButton = (Button)findViewById(R.id.pMonthButton);
        this.pMonthButton.setOnClickListener(this);
        this.nMonthButton = (Button)findViewById(R.id.nMonthButton);
        this.nMonthButton.setOnClickListener(this);

        Calendar c = Calendar.getInstance();
        this.displayYear = c.get(Calendar.YEAR);
        this.displayMonth = c.get(Calendar.MONTH) + 1;

        this.nowYear = this.displayYear;
        this.nowMonth = this.displayMonth;
        this.nowDay = c.get(Calendar.DATE);


        int llId = 0;
        String llName = null;
        LinearLayout ll = null;
        TextView tv = null;
        TextViewInfo tvInfo = null;
        Resources res = getResources();
        View view = null;
        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                llName = "diaryItem" + i + "_" + j;
                llId = res.getIdentifier(llName, "id", getPackageName());
                ll = (LinearLayout) findViewById(llId);
                ll.setOnClickListener(this);
                tv = (TextView) ll.getChildAt(1);
                tvInfo = new TextViewInfo(tv.getId());
                tvInfo.setTextview(tv);
                this.calInfoList.add(tvInfo);
            }
        }
        setCalendar(0);
    }

    public void setCalendar(int offset){
        this.displayMonth += offset;
        if(this.displayMonth > 12){
            this.displayMonth = 1;
            this.displayYear++;
        }else if(this.displayMonth < 1){
            this.displayMonth = 12;
            this.displayYear--;
        }

        CalendarInfo cInfo = new CalendarInfo(this.displayYear, this.displayMonth);
        String[][] calendarMatrix = cInfo.getCalendarMatrix();

        int listCnt = 0;
        for(int i=0; i<MAX_WEEK_OF_MONTH; i++){
            for(int j=0; j<DATE_OF_WEEK; j++){
                this.calInfoList.get(listCnt).getTextview().setText(calendarMatrix[i][j]);
                listCnt++;
            }
        }
        TextView tv = null;
        tv = (TextView)findViewById(R.id.title);
        tv.setText(displayYear + "年 " + displayMonth + "月");
    }

    @Override
    public void onClick(View view){
        if(view != null){
            switch (view.getId()){
                // 次の月へ
                case R.id.pMonthButton:
                    setCalendar(-1);
                    break;
                // 前の月へ
                case R.id.nMonthButton:
                    setCalendar(1);
                    break;
                // 日記一覧へ
                default:
                    Intent intent = new Intent(CalendarActivity.this, DiaryActivity.class);
                    startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_carendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.menu_tv){
            Intent intent = new Intent(CalendarActivity.this, AddDiaryActivity.class);
            startActivity(intent);
        }
        return true;
    }


}
