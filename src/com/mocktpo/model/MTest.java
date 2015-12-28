package com.mocktpo.model;

public class MTest {

    protected String index;
    protected String name;
    protected String download;
    protected String next;
    protected String reports;

    public MTest(String index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getReports() {
        return reports;
    }
}
