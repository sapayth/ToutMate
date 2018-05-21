package com.nullpointers.toutmate.Model;

import android.net.Uri;

public class Moment {

    private String fileName;
    private String formatName;
    private long date;
    private long time;
    private Uri downloadLink;

    public Moment() {
        //required for firebase
    }

    public Moment(String fileName, String formatName, long date, long time, Uri downloadLink) {
        this.fileName = fileName;
        this.formatName = formatName;
        this.date = date;
        this.time = time;
        this.downloadLink = downloadLink;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFormatName() {
        return formatName;
    }

    public long getDate() {
        return date;
    }

    public long getTime() {
        return time;
    }

    public Uri getDownloadLink() {
        return downloadLink;
    }
}
