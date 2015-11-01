package com.mocktpo.model;

public class MTest {

    protected String index;
    protected String name;
    protected boolean download;
    protected boolean next;
    protected boolean reports;

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

    public boolean getDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public boolean getNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public boolean getReports() {
        return reports;
    }

    public void setReports(boolean reports) {
        this.reports = reports;
    }

    @Override
    public String toString() {
        return this.index;
    }
}
