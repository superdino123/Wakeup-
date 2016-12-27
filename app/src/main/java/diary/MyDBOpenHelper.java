package diary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(Context context) {
        super(context, "DB_Diary", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table tb_diary(_id integer primary key autoincrement, date text, title text, remark text);";
        db.execSQL(sql);
        String sql_insert="insert into tb_diary(date,title,remark) values ('2014-5-5','1','啊啊');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2014-5-6','2','版本');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2014-5-18','3','蔡春');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2014-5-19','4','dd');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2014-6-1','5','ee');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2014-6-5','6','SQLss');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2014-6-6','7','SQLdss');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2014-6-10','8','dfcd');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2014-7-5','9','dw');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2014-7-15','Android','dsd');";
        db.execSQL(sql_insert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}