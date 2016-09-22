package com.mocktpo.config;

public class TestStatus {

    protected String id;
    protected String name;
    protected String download;
    protected String next;
    protected String reports;

    public TestStatus(String id, String name, String download, String next, String reports) {
        this.id = id;
        this.name = name;
        this.download = download;
        this.next = next;
        this.reports = reports;
    }

    public TestStatus() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "{" + "id:" + this.getId() + ",name:" + this.getName() + ",download:" + this.getDownload() + ",next:" + this.getNext() + ",reports:" + this.getReports() + "}";
    }
}
