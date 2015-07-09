package com.example.lee.recyclerview_revised;

/**
 * Created by Lee on 2015/7/7.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;


public class TaskPage extends ActionBarActivity implements
        TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener
{
    private TextView titileTextView;
    private TextView noteTextView;
   // private Spinner colorSpinner;
    private CheckBox mode24Hours;
    private CheckBox modeDarkTime;
    private CheckBox modeDarkDate;
    int primaryColor;
    String title;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskpage);
        //接收訊息參數
        Intent intent = getIntent();
        primaryColor = intent.getIntExtra("primarycolor",0);
        title = intent.getStringExtra("title");
        //Toast.makeText(this,"color number is"+primaryColor,Toast.LENGTH_SHORT). show();
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        Button FromdateButton = (Button)findViewById(R.id.date_button);
        Button FromtimeButton = (Button)findViewById(R.id.time_button);
        Button TodateButton = (Button)findViewById(R.id.Todate_button);
        Button TotimeButton = (Button)findViewById(R.id.Totime_button);
        Button repeatButton = (Button)findViewById(R.id.repeatbutton);
        titileTextView = (EditText)findViewById(R.id.editText2);
        noteTextView =(EditText)findViewById(R.id.editText3);

        toolbar.setTitle(title);
        toolbar.setBackgroundColor(getResources().getColor(primaryColor));
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TaskPage.this, MainActivity.class);
                startActivity(intent);
                TaskPage.this.finish();
            }
        });

        // Find our View instances
       /* timeTextView = (TextView)findViewById(R.id.time_textview);
        dateTextView = (TextView)findViewById(R.id.date_textview);
        Button timeButton = (Button)findViewById(R.id.time_button);
        Button dateButton = (Button)findViewById(R.id.date_button);
        mode24Hours = (CheckBox)findViewById(R.id.mode_24_hours);
        modeDarkTime = (CheckBox)findViewById(R.id.mode_dark_time);
        modeDarkDate = (CheckBox)findViewById(R.id.mode_dark_date);
*/
        // Show a timepicker when the timeButton is clicked
        FromtimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        TaskPage.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE)
                        //mode24Hours.isChecked()
                );
                //tpd.setThemeDark(modeDarkTime.isChecked());
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        TotimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        TaskPage.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE)
                        //mode24Hours.isChecked()
                );
                //tpd.setThemeDark(modeDarkTime.isChecked());
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        // Show a datepicker when the dateButton is clicked
        FromdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        TaskPage.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                //dpd.setThemeDark(modeDarkDate.isChecked());
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        TodateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        TaskPage.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                //dpd.setThemeDark(modeDarkDate.isChecked());
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String time = "You picked the following time: "+hourString+"h"+minuteString;
        //timeTextView.setText(time);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+monthOfYear+"/"+year;
        //dateTextView.setText(date);
    }

    //ToolBar Listener
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    msg += "Click edit";
                    break;
                case R.id.action_share:
                    msg += "Click share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(TaskPage.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    public boolean dispatchKeyEvent(KeyEvent event) {
        //TODO Auto-generated method stub
        if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.setClass(TaskPage.this, MainActivity.class);
            startActivity(intent);
            TaskPage.this.finish();
            return false;
        }
        return super.dispatchKeyEvent(event);
    }

}

