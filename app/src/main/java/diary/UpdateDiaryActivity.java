package diary;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo.R;

/**
 * Created by Administrator on 2016/12/27.
 */

public class UpdateDiaryActivity extends Activity{
    private TextView view_date;
    private EditText txt_title,txt_remark;
    private Button Btn_update,Btn_return;
    private SQLiteDatabase dbRead,dbWrite;;
    private String dateStr,titleStr,remarkStr,selectedid;
    private MyDBOpenHelper dbHelper;
    private Cursor result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        this.setTitle("修改日记");
        view_date = (TextView) findViewById(R.id.Tview_date);
        txt_title = (EditText) findViewById(R.id.Txt_title);
        txt_remark = (EditText) findViewById(R.id.Txt_remark);
        Btn_update = (Button) findViewById(R.id.btn_update2);
        Btn_return = (Button) findViewById(R.id.btn_return2);
        selectedid=getIntent().getStringExtra("selectedID");
        dbHelper = new MyDBOpenHelper(this);
        dbRead= dbHelper.getReadableDatabase();
        result=dbRead.rawQuery( "select _id, date, title,remark from tb_diary where _id="+selectedid, null);
        result.moveToNext();
        dateStr=result.getString(result.getColumnIndex("date"));
        titleStr=result.getString(result.getColumnIndex("title"));
        remarkStr=result.getString(result.getColumnIndex("remark"));
        view_date.setText(dateStr);
        txt_title.setText(titleStr);
        txt_remark.setText(remarkStr);
        Btn_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectedid=getIntent().getStringExtra("selectedID");
                dbWrite= dbHelper.getWritableDatabase();
                String sql="update tb_diary set title='"+txt_title.getText()
                        +"',remark='"+txt_remark.getText()+"' where _id="+selectedid;
                dbWrite.execSQL(sql);
                Toast.makeText(UpdateDiaryActivity.this,"日记修改成功", Toast.LENGTH_SHORT).show();

            }
        });

        Btn_return.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //dbRead.close();
        //dbWrite.close();
    }
}
