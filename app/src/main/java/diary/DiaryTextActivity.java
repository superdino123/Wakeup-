package diary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.administrator.demo.R;

/**
 * Created by Administrator on 2016/12/27.
 */

public class DiaryTextActivity extends Activity{
    private ListView ListDiary;
    private Button btnInsert,btnRefresh,btnClose;
    private SQLiteDatabase dbRead;
    private MyDBOpenHelper dbHelper;
    private Cursor result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diarylist);
        //this.setTitle("备忘录");
        ListDiary = (ListView) findViewById(R.id.listdiary);
        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnRefresh = (Button) findViewById(R.id.btn_refresh);
        btnClose = (Button) findViewById(R.id.btn_close);
        dbHelper = new MyDBOpenHelper(this);
        dbRead= dbHelper.getReadableDatabase();
        readDiary();
        btnInsert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent insertIntent = new Intent(DiaryTextActivity.this,InsertDiaryActivity.class);
                startActivity(insertIntent);
            }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                readDiary();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
    protected void readDiary(){
        result=dbRead.rawQuery( "select _id, date, title from tb_diary", null);
        int resultCounts = result.getCount();
        if (resultCounts == 0 || !result.moveToFirst())
        {
            Toast.makeText(this,"数据库没有数据", Toast.LENGTH_SHORT).show();
        }
        else
        {
            SimpleCursorAdapter adapter=new SimpleCursorAdapter(getApplicationContext(), R.layout.listitem, result,
                    new String[]{"_id","date","title"}, new int[]{R.id.listitem_id,R.id.listitem_date,R.id.listitem_title});
            //ListDiary.setAdapter(new ArrayAdapter<String>(this, R.layout.listitem, title_list));
            ListDiary.setAdapter(adapter);
            ListDiary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //ListView listView = (ListView) parent;
                    //String itemString=((TextView)view).getText().toString();

                    Cursor cursor=(Cursor)parent.getItemAtPosition(position);
                    Intent readIntent = new Intent(DiaryTextActivity.this,DiaryDetailActivity.class);
                    readIntent.putExtra("selectedID", cursor.getString(0));
                    startActivity(readIntent);

                }
            });
        }

    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //dbRead.close();
    }
}
