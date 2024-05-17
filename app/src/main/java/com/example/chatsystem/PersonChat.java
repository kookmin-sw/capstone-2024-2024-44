package com.example.chatsystem;

public class PersonChat {
    /**
     * id
     */
    private int id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 聊天内容
     */
    private String chatMessage;
    /**
     * 是否为本人发送
     */
    private boolean isMeSend;
    /**
     * 发送时间
     */
    private String msgtime;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getChatMessage() {
        return chatMessage;
    }
    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }
    public boolean isMeSend() {
        return isMeSend;
    }
    public void setMeSend(boolean isMeSend) {
        this.isMeSend = isMeSend;
    }
    public String getMsgtime() {
        return msgtime;
    }
    public void setMsgtime(String msgtime) {
        this.msgtime = msgtime;
    }
    public PersonChat(int id, String name, String chatMessage, boolean isMeSend, String msgtime) {
        super();
        this.id = id;
        this.name = name;
        this.chatMessage = chatMessage;
        this.isMeSend = isMeSend;
        this.msgtime = msgtime;
    }
    public PersonChat() {
        super();
    }
}

