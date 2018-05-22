package com.nullpointers.toutmate.Model;

import android.net.Uri;

public class Moment {
    private String key;
    private String fileName;
    private String formatName;
    private long date;
    private String downloadLink;

    public Moment() {
        //required for firebase
    }

    public Moment(String key, String fileName, String formatName, long date, String downloadLink) {
        this.key = key;
        this.fileName = fileName;
        this.formatName = formatName;
        this.date = date;
        this.downloadLink = downloadLink;
    }

    public String getKey() {
        return key;
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

    public String getDownloadLink() {
        return downloadLink;
    }
}
