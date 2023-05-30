package com.example.dbtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        Button btnRead = findViewById(R.id.btnRead);
        Button btnQuery = findViewById(R.id.btnQuery);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("delete from vertices ");
                db.execSQL("delete from triangles");

                InputStream inputStream = getApplicationContext().getResources().openRawResource(R.raw.sphere);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                int i = 0;
                try {
                    while ((line = bufferedReader.readLine()) != null)
                    {
                        String[] tokens = line.split(" ");
                        if(tokens[0].equals("v"))
                        {

                            db.execSQL("INSERT INTO vertices VALUES (null, '"+ tokens[1]+ "', '"+ tokens[2] + "', '"+ tokens[3] + "');");
                            i++;
                        }
                        else if(tokens[0].equals("f"))
                        {
                            db.execSQL("INSERT INTO triangles VALUES (null, '"+ tokens[1]+ "', '"+ tokens[2] + "', '"+ tokens[3] + "');");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor;

                cursor = db.rawQuery("SELECT x, y, z FROM vertices WHERE x < -6;",null);
                ListView listView = findViewById(R.id.listviewQuery);
                ArrayList<HashMap<String, String>> list = new ArrayList<>();
                List<String> elements = new ArrayList<>();
                String[] from  = {"key01"};
                int[] to = new int[] {android.R.id.text1};
//
                while(cursor.moveToNext())
                {
                    String x = cursor.getString(0);
                    String y = cursor.getString(1);
                    String z = cursor.getString(2);
                    Log.v("OUT","vertex pos ("+x+", " + y + ", "+z+")");
                    HashMap<String, String> item01 = new HashMap<>();
                    item01.put("key01","vertex pos ("+x+", " + y + ", "+z+")");
                    list.add(item01);
                }
                @SuppressLint("ResourceType") Adapter adapter = new SimpleAdapter(getApplicationContext(),list,android.R.layout.simple_list_item_1,from,to) ;
                listView.setAdapter((ListAdapter) adapter);
            }
        });

    }
    class DBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME= "speregeom.db";
        private static final int DATABASE_VERSION=1;

        public DBHelper(Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table vertices (  _id integer primary key autoincrement, x float, y float, z float);");
            db.execSQL("create table triangles ( _id integer primary key autoincrement, v0 integer, v1 integer, v2 integer);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("drop table if exists vertices;");
            db.execSQL("drop table if exists triangles;");

        }
    }
}