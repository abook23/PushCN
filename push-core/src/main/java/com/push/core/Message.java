package com.push.core;


import android.os.Parcel;
import android.os.Parcelable;

import com.push.core.util.Rom;

/**
 * 将消息整合成Message model
 */

public final class Message implements Parcelable {
    private String rgistrationId;  //这个字段用于通知的消息类型，在透传中都是默认0
    private String messageID;
    private String title;
    private String message;
    private String extra;
    private String alias;
    private long resultCode;
    private int type;
    private Rom mRom;

    public Message(){}

    public Message(String rgistrationId, long resultCode, String message, String alias) {
        this.rgistrationId = rgistrationId;
        this.resultCode = resultCode;
        this.message = message;
        this.alias = alias;
    }


    protected Message(Parcel in) {
        rgistrationId = in.readString();
        messageID = in.readString();
        title = in.readString();
        message = in.readString();
        extra = in.readString();
        alias = in.readString();
        resultCode = in.readLong();
        type = in.readInt();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public Rom getRom() {
        return mRom;
    }

    public void setRom(Rom rom) {
        mRom = rom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public long getResultCode() {
        return resultCode;
    }

    public void setResultCode(long resultCode) {
        this.resultCode = resultCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRgistrationId() {
        return rgistrationId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rgistrationId);
        dest.writeString(messageID);
        dest.writeString(title);
        dest.writeString(message);
        dest.writeString(extra);
        dest.writeString(alias);
        dest.writeLong(resultCode);
        dest.writeInt(type);
    }
}
