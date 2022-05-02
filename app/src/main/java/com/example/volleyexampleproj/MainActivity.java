package com.example.volleyexampleproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static ListView listView;
    private static List<String> resultList = new ArrayList<>();
    public static ArrayAdapter<String> listViewAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultList);

        listView = findViewById(R.id.listView);
        listView.setAdapter(listViewAdapter);

        Button simpleButton = findViewById(R.id.simpleButton);
        simpleButton.setOnClickListener(v -> MakeSimpleVolleyRequest());
        simpleButton.setOnLongClickListener(v -> MakeManySimpleVolleyRequest());

        Button singleButton = findViewById(R.id.singletonButton);
        singleButton.setOnClickListener(v -> MakeSingletonVolleyRequest());
        singleButton.setOnLongClickListener(v -> MakeManySingleVolleyRequest());

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(v -> OnClickClear());
    }


    private void MakeSimpleVolleyRequest() {
        SimpleVolley.SimpleRequest(this, "https://jsonplaceholder.typicode.com/posts/1");
    }

    private boolean MakeManySimpleVolleyRequest() {
        for (int i = 1; i <= 100; i++) {
            SimpleVolley.SimpleRequest(this, "https://jsonplaceholder.typicode.com/posts/" + i);
        }
        return true;
    }

    private void MakeSingletonVolleyRequest() {

        SingletonVolley.SingletonRequest(this, "https://jsonplaceholder.typicode.com/posts/1");
    }

    private boolean MakeManySingleVolleyRequest() {
        for (int i = 1; i <= 100; i++) {
            SingletonVolley.SingletonRequest(this, "https://jsonplaceholder.typicode.com/posts/" + i);
        }
        return true;
    }

    public static void AddToListView(String item) {
        try {
            resultList.add(item);
            listViewAdapter.notifyDataSetChanged();
            scrollMyListViewToBottom();
            System.out.println("ListView Updated.");
        } catch (Exception ex) {
            System.out.println("List Update Error: " + ex.getMessage());
        }
    }

    private void OnClickClear() {
        resultList.clear();
        listViewAdapter.clear();
        System.out.println("Listview has been cleared.");
    }

    private static void scrollMyListViewToBottom() {
        listView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                listView.setSelection(listViewAdapter.getCount() - 1);
            }
        });
    }

}