package diary;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.demo.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/12/27.
 */

public class InsertDiaryActivity extends Activity{
    private Button BtnInsert,BtnCancel,BtnClear;
    private EditText TxtTitle, TxtRemark;
    private SQLiteDatabase dbWrite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert);
        this.setTitle("添加新日记");
        TxtTitle = (EditText) findViewById(R.id.Txt_title);
        TxtRemark = (EditText) findViewById(R.id.Txt_remark);
        MyDBOpenHelper dbHelper = new MyDBOpenHelper(this);
        dbWrite= dbHelper.getWritableDatabase();
        BtnInsert = (Button) findViewById(R.id.btn_insert);
        BtnClear = (Button) findViewById(R.id.btn_clear);
        BtnCancel = (Button) findViewById(R.id.btn_cancel);

        BtnInsert.setOnClickListener(new View.OnClickListener()
        {
            int year,month,day;
            @Override
            public void onClick(View v)
            {

                if(TxtTitle.getText().toString().trim().equals("")){
                    Toast.makeText(InsertDiaryActivity.this,"日记标题不能为空，请填写标题", Toast.LENGTH_SHORT).show();
                    return;
                }
                Calendar currentTime = Calendar.getInstance();
                day=currentTime.get(Calendar.DAY_OF_MONTH);
                month=currentTime.get(Calendar.MONTH);
                year= currentTime.get(Calendar.YEAR);

                String sql="insert into tb_diary(date,title,remark) values ('"+String.valueOf(year)
                        +"-"+String.valueOf(month)+"-"+String.valueOf(day)
                        +"','"+TxtTitle.getText()+"','"+TxtRemark.getText()+"');";
                dbWrite.execSQL(sql);
                Toast.makeText(InsertDiaryActivity.this,"新日记添加成功", Toast.LENGTH_SHORT).show();
                TxtRemark.setText("");
                TxtTitle.setText("");
            }
        });
        BtnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TxtRemark.setText("");
                TxtTitle.setText("");
            }
        });
        BtnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}
