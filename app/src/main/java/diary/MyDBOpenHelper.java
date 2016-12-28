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
        String sql_insert="insert into tb_diary(date,title,remark) values ('2016-12-22','开启安卓模式','安卓作业下发啦，要好好规划一下时间了');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2016-12-23','计划出炉','借书系统想法构思完成，即将实现雄伟的计划');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2016-12-24','计划变更','想法离地太远了，结合实际，想想时间，商量更换主题');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2016-12-25','主题明确','世上绝无仅有的超级闹钟要诞生了！');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2016-12-26','实施编程','代码中，写了错，错了改，改了错，错了改，好啦，完成了一个小功能，happy!');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2016-12-27','编程加一','代码中，写了错，错了改，改了错，错了改，好啦，又完成了一个小功能，happy!');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2016-12-28','有序进行','部分功能已经得到实现，计划今晚进行代码合并，开始收尾工作');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2016-12-28','测试1','测试1');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2016-12-28','测试2','测试2');";
        db.execSQL(sql_insert);
        sql_insert="insert into tb_diary(date,title,remark) values ('2016-12-28','测试3','测试3');";
        db.execSQL(sql_insert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}