package com.richieye.examinationsystemDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by RichieYe on 2016/4/12.
 */
public class DBHelper extends SQLiteOpenHelper
{
    private static int iVersion=1;
    private static String DBName="db_ExaminationSystem";
    SQLiteDatabase db;


    public DBHelper(Context context)
    {
        super(context, DBName, null, iVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try {
            db.execSQL(ClassesHelper.CREATE_CLASSES_TABLE);
            db.execSQL(StudentsHelper.CREATE_STUDENT_TABLE);
            db.execSQL(TestingHelper.CREATE_TESTING_TABLE);
            db.execSQL(UserTestHelper.CREATE_USERTEST_TABLE);
            db.execSQL(UserScoreHelper.CREATE_USERSCORE_TABLE);
            db.execSQL(CompletionHelper.CREATE_COMPLETION_TABLE);
            db.execSQL(ChoiceHelper.CREATE_CHOICE_TABLE);
            db.execSQL(MultiChoiceHelper.CREATE_MULTICHOICE_TABLE);
            db.execSQL(JudgmentHelper.CREATE_JUDGMENT_TABLE);
            db.execSQL(ProgramHelper.CREATE_PROGRAM_TABLE);
            Log.e("DBHelper","新建数据库成功！");
        }catch (Exception ex)
        {
            ex.printStackTrace();
            Log.e("DBHelper","出错了，错误信息："+ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Insert(String TBName,List<Map<String,String>> params)
    {

    }

    public void Delete(String TBName,int ID)
    {

    }

    public void Update(String TBName,List<Map<String,String>>params)
    {

    }

    public void Replace(String TBName,List<Map<String,String>> params)
    {
        ContentValues contentValues=null;
        if(params!=null&&!params.isEmpty())
        {
            contentValues=new ContentValues();
            for(Map<String,String> map:params) {
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    contentValues.put((String) entry.getKey(),
                            (String) entry.getValue());
                }
                this.getWritableDatabase().replace(TBName, "_id", contentValues);
            }
        }
    }

    public Cursor Select(String tbName,int ID)
    {
        String strWhere=null;
        String strArgs[]=null;

        if(ID!=0)
        {
            strWhere="_id=?";
            strArgs=new String[]{ID+""};
        }

        return this.getReadableDatabase().query(tbName,null,strWhere,strArgs,null,null,null);
    }

    public Cursor Select(String tbName,List<Map<String,String>> params)
    {
        String strWhere=makeWhere(params);
        Log.e("DBHelper",strWhere);
        return null;
    }

    public String makeWhere(List<Map<String,String>> params)
    {
        String strWhere="";
        String[] strArgs=makePremeters(params);
        for(int i=0;i<strArgs.length;i++)
        {
            Log.e("DBHelper3",strArgs[i]);
        }
        if(params!=null&&!params.isEmpty())
        {
            for(Map<String,String> map:params)
            {
                Iterator iterator=map.entrySet().iterator();
                while (iterator.hasNext())
                {
                    Map.Entry entry=(Map.Entry)iterator.next();
                    strWhere+= entry.getKey() +"=? and ";
                    Log.e("DBHelper",strWhere);
                }
            }

            strWhere=strWhere.substring(0,strWhere.lastIndexOf(" and "));
        }
        return  strWhere;
    }

    private String[] makePremeters(List<Map<String,String>> params)
    {
        //String[] strArgs;
        List<String> list=new ArrayList<String>();
        if(params!=null&&!params.isEmpty())
        {
            Log.e("DBHelper5",params.toString());
            for(Map<String,String> map:params)
            {
                Iterator iterator=map.entrySet().iterator();
                while (iterator.hasNext())
                {
                    Map.Entry entry=(Map.Entry)iterator.next();
                   list.add((String) entry.getValue());
                }
                Log.e("DBHelper2",list.toString());
            }
        }
        //Object[] objects=list.toArray();

        return new String[]{list.toString()};
    }
}
