package com.example.addfriend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FriendActivity extends AppCompatActivity {

    private ListView friendListView;
    private ArrayList<Map<String, Object>> datas = new ArrayList<>();
    private final String[] ITEMS = {"profile", "username"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        friendListView = (ListView) findViewById(R.id.friendListView);
        initdatas();
        SimpleAdapter adapter = new SimpleAdapter(FriendActivity.this,
                datas,
                R.layout.list_new_friend_item,
                ITEMS,
                new int[] {R.id.profileImgView, R.id.usernameTextView});
        friendListView.setAdapter(adapter);
        friendListView.setOnItemClickListener((parent, view, position, id)->{
                Intent intent = new Intent(FriendActivity.this, AcceptFriendActivity.class);
                startActivity(intent);
        });
    }

    //初始化数据
    private void initdatas() {
        Map<String, Object> mp1 = new HashMap<>();
        mp1.put(ITEMS[0], R.drawable.ic_launcher_background);
        mp1.put(ITEMS[1], "이름");
        datas.add(mp1);
        }
    }