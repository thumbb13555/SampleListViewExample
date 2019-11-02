package com.example.samplelistviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] subject = {"國文", "英文", "數學", "物理", "地科", "基本電學", "電子學", "電工機械"};
    ArrayList<String> score = new ArrayList<>();
    ListView listView;
    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayListView();
    }

    private void displayListView() {

//        ArrayList<String> arraySubject = new ArrayList<>(Arrays.asList(subject));

        listView = findViewById(R.id.listView);
        final ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for (int i = 0; i < subject.length; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            score.add(String.valueOf(new Random().nextInt(60) + 40));
            hashMap.put("subject", subject[i]);
            hashMap.put("score", score.get(i));
            arrayList.add(hashMap);
        }
        Log.d("Test", "displayListView: "+arrayList);
        String[] from = {"subject", "score"};
        int[] value = {R.id.textView, R.id.textView2};
        simpleAdapter =
                new SimpleAdapter(this, arrayList, R.layout.custom_layout, from, value);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener
            = ((parent, view, position, id) -> {
                String getName = subject[position];
                String getValue= score.get(position);
                listView.setSelector(R.color.Sakura);
                if (Integer.parseInt(getValue)<60){
                    Toast.makeText(getBaseContext(),
                            "科目:"+getName+", 分數:不及格",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(),
                            "科目:"+getName+", 分數"+getValue,Toast.LENGTH_SHORT).show();
                }
    });
}
