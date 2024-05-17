package com.example.chatsystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private Button sendMsgBtn;//发送按钮
    private EditText msgEditText;//消息编辑框
    private Intent mIntent = null;
    private ListView msgContainer;//聊天框容器
    private ChatAdapter chatAdapter;//聊天适配器
    private List<PersonChat> personChats = new ArrayList<PersonChat>();
    private int myid, friendid;
    private String friendName = "";
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int what = msg.what;
            switch (what) {
                case 1:
                    /*ListView条目控制在最后一行*/
                    msgContainer.setSelection(personChats.size());
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mIntent = getIntent();
        if (mIntent != null) {
            Bundle bundle = mIntent.getExtras();
            if (bundle != null) {
                //获取好友头像
                //获取好友名称
                friendName = bundle.getString("name");
                this.setTitle(friendName);
                //获取好友id
                friendid = bundle.getInt("id");
            } else return;
        } else return ;

        sendMsgBtn = (Button) findViewById(R.id.sendMsgBtn);
        sendMsgBtn.setBackgroundColor(Color.TRANSPARENT);
        msgEditText = (EditText) findViewById(R.id.msgEditText);
        msgContainer = (ListView)findViewById(R.id.msgContainer);
        msgEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当用户在编辑框中完成输入并点击软键盘上的按钮时触发该方法
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //获取输入框的文本内容
                    String input = msgEditText.getText().toString();
                    return false;//返回false，代表隐藏软键盘
                }
                return true;//返回true，代表保留软键盘
            }
        });
        msgEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //编辑框改变之前调用
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //编辑框改变的时候调用
                String input = msgEditText.getText().toString();
                if (input != null && input.length() > 500) {
                    Toast.makeText(MessageActivity.this,
                            "Please limit the number of letters up to 500.",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //编辑框改变之后调用
                String input = msgEditText.getText().toString();
                boolean hasText = input != null && !"".equals(input) && input.length() > 0;
                changeSendBtn(hasText);
            }
        });
        //从数据库中读取聊天记录
        initPersonChats();
        chatAdapter = new ChatAdapter(MessageActivity.this, personChats);
        msgContainer.setAdapter(chatAdapter);
    }

    //从数据库中读取聊天记录
    private void initPersonChats() {

    }

    /* 发送消息 */
    public void send(View view) {
        String input = msgEditText.getText().toString();
        if (input == null || "".equals(input) || input.trim().length() == 0) {
            //给出吐司提示“不可发送空消息”
            Toast.makeText(MessageActivity.this,
                    "Please don't send empty message.",
                    Toast.LENGTH_SHORT).show();
        } else if (input.length() > 500) {
            //给出吐司提示“不可发送超过500字的消息”
            Toast.makeText(MessageActivity.this,
                    "Please limit the number of letters up to 500.",
                    Toast.LENGTH_SHORT).show();
        } else {//可以发送
            PersonChat personChat = new PersonChat();
            personChat.setMeSend(true);//代表自己发送
            String msg = msgEditText.getText().toString();
            personChat.setChatMessage(msg);//得到发送内容
            Date date = new Date();
            String msgtime = format.format(date);
            personChat.setMsgtime(msgtime);//发送时间
            personChats.add(personChat);//加入集合
            sort();//给消息按照时间顺序排序
            chatAdapter.notifyDataSetChanged();//刷新ListView
            msgEditText.setText("");//发送消息后情况文本框
            handler.sendEmptyMessage(1);//消息移动到最底端
            saveMsg(msg);//将消息记录存入数据库
        }
    }

    /* 改变发送按钮背景颜色 */
    private void changeSendBtn(boolean hasText) {
        sendMsgBtn.setBackgroundColor(hasText ? Color.GREEN : Color.TRANSPARENT);
    }

    /*给消息按照时间顺序排个序*/
    private void sort() {
        Collections.sort(personChats, new Comparator<PersonChat>() {
            @Override
            public int compare(PersonChat o1, PersonChat o2) {
                String s1 = o1.getMsgtime();
                String s2 = o2.getMsgtime();
                try {
                    Date date1 = format.parse(s1);
                    Date date2 = format.parse(s2);
                    return date1.compareTo(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return -1;
            }
        });
    }

    //将消息记录存入数据库
    private void saveMsg(String msg) {

    }
}
