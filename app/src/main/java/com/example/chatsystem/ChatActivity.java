package com.example.chatsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends Activity {

    private ListView friendsListView;
    private ArrayList<Map<String, Object>> datas = null;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int myid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        datas = new ArrayList<>();
        friendsListView = (ListView) findViewById(R.id.friendsListView);
        friendsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Map<String, Object> entity = (Map<String, Object>) friendsListView
                        .getItemAtPosition(position);
                //根据你的需求把你要存放的数据存入bundle中再跳转就行了
                bundle.putInt("userid", (int)entity.get("id"));//好友id
                bundle.putString("name", (String)entity.get("name"));//好友名字
                chat(bundle);
            }
        });
        //在此初始化 myid
    }

    @Override
    public void onResume() {
        super.onResume();
        datas.clear();

        initDatas(myid);
        SimpleAdapter simpleAdapter = new SimpleAdapter(ChatActivity.this,
                datas,
                R.layout.list_friend_item,
                new String[]{"pp", "name", "msg", "date"},
                new int[]{R.id.profilePhotoImgView, R.id.nameTextView, R.id.msgContentTextView, R.id.msgDateTextView});
        friendsListView.setAdapter(simpleAdapter);
    }

    //跳转至与好友的对话框界面
    private void chat(Bundle bundle) {
        Intent intent = new Intent(ChatActivity.this, MessageActivity.class);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
    }

    //初始化列表的数据
    private void initDatas(int id) {
        //获取到的数据如果是没有排序的，可以调用sort()排个序


        //下面为案例，实际使用时删掉
        Map<String, Object> data = new HashMap<>();
        Msg msg = new Msg(1, 2, R.drawable.app_lvjian_message_background, "123", "2024.5.3");
        data.put("pp", msg.getProfilePhoto());
        data.put("id", 1);
        data.put("name", "로제");
        data.put("msg", msg.getMsg());
        data.put("date", msg.getDate());
        datas.add(data);
    }

    //按照时间为消息排序
    private void sort() {
        Collections.sort(datas, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String s1 = (String) o1.get("date");
                String s2 = (String) o2.get("date");
                try {
                    Date date1 = format.parse(s1);
                    Date date2 = format.parse(s2);
                    return date2.compareTo(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return -1;
            }
        });
    }

    //内部类，聊天对象消息实体
    private class Msg {
        int sender, receiver;
        int profilePhoto;
        String msg;
        String date;

        public Msg(int sender, int receiver, int profilePhoto, String msg, String date) {
            this.sender = sender;
            this.receiver = receiver;
            this.profilePhoto = profilePhoto;
            this.msg = msg;
            this.date = date;
        }

        public int getSender() {
            return sender;
        }

        public void setSender(int sender) {
            this.sender = sender;
        }

        public int getReceiver() {
            return receiver;
        }

        public void setReceiver(int receiver) {
            this.receiver = receiver;
        }

        public int getProfilePhoto() {
            return profilePhoto;
        }

        public void setProfilePhoto(int profilePhoto) {
            this.profilePhoto = profilePhoto;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
