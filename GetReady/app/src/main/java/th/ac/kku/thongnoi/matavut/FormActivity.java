package th.ac.kku.thongnoi.matavut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FormActivity extends AppCompatActivity {
    private String year, month, day, hour, min, time, title, todo, other, item = "";
    private TextView tv_date, tv_time;
    private Button btn_back, btn_submit;
    private EditText ed_head, ed_todo, ed_other;
    private CheckBox cb_doc, cb_laptop, cb_phone, cb_costume, cb_stat, cb_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Bundle extras = getIntent().getExtras();
        year = extras.getString("year");
        month = extras.getString("month");
        day = extras.getString("day");
        hour = extras.getString("hour");
        min = extras.getString("min");
        time = day + "/" + month + "/" + year + "  " + hour + ":" + min;
        Toast.makeText(getApplicationContext(),time,Toast.LENGTH_SHORT).show();

        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        tv_date.setText(day + "/" + month + "/" + year);
        tv_time.setText(hour + ":" + min);

        btn_back = findViewById(R.id.btn_back);
        btn_submit = findViewById(R.id.btn_submit);

        ed_head = findViewById(R.id.ed_head);
        ed_todo = findViewById(R.id.ed_todo);
        ed_other = findViewById(R.id.ed_other);

        cb_doc = findViewById(R.id.cb_doc);
        cb_laptop = findViewById(R.id.cb_laptop);
        cb_phone = findViewById(R.id.cb_phone);
        cb_costume = findViewById(R.id.cb_costume);
        cb_stat = findViewById(R.id.cb_stat);
        cb_food = findViewById(R.id.cb_food);

        onClick();
    }

    public void onClick() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormActivity.this,YourActivity.class);
                startActivity(intent);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = ed_head.getText().toString();
                todo = ed_todo.getText().toString();
                other = ed_other.getText().toString();

                if (title.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter title",Toast.LENGTH_SHORT).show();
                } else if (todo.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter to do activity",Toast.LENGTH_SHORT).show();
                } else {
                    if (cb_doc.isChecked()) {item = item + " " + cb_doc.getText();}
                    if (cb_laptop.isChecked()) {item = item + " " + cb_laptop.getText();}
                    if (cb_phone.isChecked()) {item = item + " " + cb_phone.getText();}
                    if (cb_costume.isChecked()) {item = item + " " + cb_costume.getText();}
                    if (cb_stat.isChecked()) {item = item + " " + cb_stat.getText();}
                    if (cb_food.isChecked()) {item = item + " " + cb_food.getText();}
                    if (!(other.isEmpty())) {item = item + " " + other;}

                    Book book = new Book();
                    book.setTitle(title);
                    book.setItem(todo);
                    book.setTime(time);

                    new FirebaseDatabaseHelper().addBook(book, new FirebaseDatabaseHelper.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<Book> books, List<String> keys) {

                        }

                        @Override
                        public void DataIsInserted() {
                            Toast.makeText(FormActivity.this,"New Activity has been recorded", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(FormActivity.this,BookListActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void DataIsUpdated() {

                        }

                        @Override
                        public void DataisDeleted() {

                        }
                    });

                    //****
                }
            }
        });
    }
}
