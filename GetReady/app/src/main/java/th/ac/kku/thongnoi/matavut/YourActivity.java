package th.ac.kku.thongnoi.matavut;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class YourActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private Button btn_time, btn_back, btn_next;
    private TextView dateView, tv_time;
    private int year, month, day, hour, min, click_time = 0;
    private TimePicker timePicker1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        tv_time = (TextView) findViewById(R.id.tv_time);
        btn_time = (Button) findViewById(R.id.btn_time);

        dateView = (TextView) findViewById(R.id.tv_date);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_next = (Button) findViewById(R.id.btn_next);

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_time = 1;
                hour = timePicker1.getCurrentHour();
                min = timePicker1.getCurrentMinute();
                setTime(hour,min);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YourActivity.this,BookListActivity.class);
                startActivity(intent);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click_time == 0) {
                    Toast.makeText(getApplicationContext(),"Please set time",Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(YourActivity.this,FormActivity.class);
                    intent.putExtra("hour", String.valueOf(hour));
                    intent.putExtra("min", String.valueOf(min));
                    intent.putExtra("year", String.valueOf(year));
                    intent.putExtra("month", String.valueOf(month));
                    intent.putExtra("day", String.valueOf(day));
                    startActivity(intent);
                }
            }
        });

    }

    public void setTime(int hour, int min) {
        tv_time.setText("Time is " + hour + ":" + min);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
