package com.yan.booksearch;

import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private List<Books> booksList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.send_btn);
        final EditText sendEdit = (EditText) findViewById(R.id.input_message);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = sendEdit.getText().toString();
                HttpUtil.sendHttpRequest(msg, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String response) {
                        booksList = getBooks(response);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        intent.putExtra("extra_data", (Serializable) booksList);
                        startActivity(intent);
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 3000);
            }
        });
    }

    private List<Books> getBooks(String jsonData){
        List<Books> booksList = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            String string = jsonObject.getString("data");
            JSONArray jsonArray = new JSONArray(string);
            for(int i = 0;i<jsonArray.length();i++){
                Books books = new Books();
                books.setName(jsonArray.getString(i));
                booksList.add(books);
                Log.d("MainActivity",jsonArray.getString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booksList;
    }
}
