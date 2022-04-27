package com.popov.introductiontoandroidnotes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

public class Note implements Parcelable {

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    private final String name;
    @StringRes
    private final int date;
    @StringRes
    private final int description;

    public Note(String name, int date, int description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    protected Note(Parcel in) {
        name = in.readString();
        date = in.readInt();
        description = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(date);
        dest.writeInt(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public int getDate() {
        return date;
    }

    public int getDescription() {
        return description;
    }
}
