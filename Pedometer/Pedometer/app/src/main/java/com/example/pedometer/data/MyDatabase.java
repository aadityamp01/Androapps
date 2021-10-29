package com.example.pedometer.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.SimpleCursorAdapter;

import com.example.pedometer.R;

public class MyDatabase {
    Context context;
    SQLiteOpenHelper helper;
    public MyDatabase(Context context){
        this.context=context;
        helper=new MyDatabaseHelper(context);
    }
    public void writeTo(String date,int dist,float calorie){
        MyTaskParams params=new MyTaskParams(date,dist,calorie);
        new writeToDB().execute(params);
    }
    public SimpleCursorAdapter getMyListAdapter(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query("STATISTICS",new String[]{"_id","DATE","DISTANCE","CALORIE"},null,null,null,null,"_id DESC");
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(context,
                R.layout.view_list_single,
                cursor,
                new String[]{"DATE","DISTANCE","CALORIE"},
                new int[]{R.id.listDate,R.id.listDistance,R.id.listCalorie},
                0);


        return adapter;
    }
    //class for supplying params for AsyncTask
    public static class MyTaskParams {
        String date;
        int dist;
        float calorie;

        MyTaskParams(String s, int i, float f) {
            this.date = s;
            this.dist = i;
            this.calorie = f;
        }
    }

    private class writeToDB extends AsyncTask<MyTaskParams,Void,Void>{

        @Override
        protected Void doInBackground(MyTaskParams... myTaskParams) {
            String date=myTaskParams[0].date;
            int dist=myTaskParams[0].dist;
            float calorie=myTaskParams[0].calorie;
            SQLiteDatabase db=helper.getWritableDatabase();
            Cursor cursor=db.query("STATISTICS",new String[]{"DATE","DISTANCE","CALORIE"},null,null,null,null,null);
            cursor.moveToLast();
            if(cursor.getCount()==0||((cursor.getCount()>0)&&(!((cursor.getString(0)).equals(date))))) {

                ContentValues cv = new ContentValues();
                cv.put("DATE", date);
                cv.put("DISTANCE", dist);
                cv.put("CALORIE", calorie);
                db.insert("STATISTICS", null, cv);
            }else{
                int initialD=cursor.getInt(1);
                int d=initialD+dist;
                float initialC=cursor.getFloat(2);
                float c=initialC+calorie;
                ContentValues cv=new ContentValues();
                cv.put("DISTANCE",d);
                cv.put("CALORIE", c);
                db.update("STATISTICS",cv,"DATE=?",new String[]{date});
            }
            db.close();
            return null;
        }
    }

}
