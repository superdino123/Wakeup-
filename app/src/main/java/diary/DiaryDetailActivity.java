package diary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demo.R;

/**
 * Created by Administrator on 2016/12/27.
 */

public class DiaryDetailActivity extends Activity{
    private TextView ID,date,title,remark;
    private Button Btn_update,Btn_delete,Btn_return;
    private SQLiteDatabase dbRead,dbWrite;;
    private String idStr,dateStr,titleStr,remarkStr,selectedid;
    private MyDBOpenHelper dbHelper;
    private Cursor result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diarytext);
        this.setTitle("查看日记详细内容");
        ID = (TextView) findViewById(R.id.Tv_id);
        date = (TextView) findViewById(R.id.Tv_date);
        title = (TextView) findViewById(R.id.Txt_title);
        remark = (TextView) findViewById(R.id.Txt_remark);
        Btn_update = (Button) findViewById(R.id.btn_update);
        Btn_delete = (Button) findViewById(R.id.btn_delete);
        Btn_return = (Button) findViewById(R.id.btn_return);
        selectedid=getIntent().getStringExtra("selectedID");
        dbHelper = new MyDBOpenHelper(this);
        dbRead= dbHelper.getReadableDatabase();

        result=dbRead.rawQuery( "select _id, date, title,remark from tb_diary where _id="+selectedid, null);
        result.moveToNext();
        idStr=String.valueOf(result.getInt(result.getColumnIndex("_id")));
        dateStr=result.getString(result.getColumnIndex("date"));
        titleStr=result.getString(result.getColumnIndex("title"));
        remarkStr=result.getString(result.getColumnIndex("remark"));
        ID.setText(idStr);
        date.setText(dateStr);
        title.setText(titleStr);
        remark.setText(remarkStr);
        Btn_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent updateIntent = new Intent(DiaryDetailActivity.this,UpdateDiaryActivity.class);
                updateIntent.putExtra("selectedID", selectedid);
                startActivity(updateIntent);
                finish();
            }
        });
        Btn_delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dbWrite= dbHelper.getWritableDatabase();
                String sql="delete from tb_diary where _id="+selectedid;
                dbWrite.execSQL(sql);
                Toast.makeText(DiaryDetailActivity.this,"日记删除成功", Toast.LENGTH_SHORT).show();

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
