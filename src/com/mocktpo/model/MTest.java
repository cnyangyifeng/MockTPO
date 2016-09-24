package com.mocktpo.model;

public class MTest {

    private String index;
    private String name;
    private String download;
    private String next;
    private String reports;

    public MTest(String index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setReports(String reports) {
        this.reports = reports;
    }
}
