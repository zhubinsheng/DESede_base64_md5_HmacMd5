package com.example.dialog_demo.beans;

public class Data {
    private String AseqId;
    private int tid = 3;
    private String ChanCode = "zhcaipiaoh5";
    private int clusters_id;

    public int getClusters_id() {
        return clusters_id;
    }

    public void setClusters_id(int clusters_id) {
        this.clusters_id = clusters_id;
    }

    public String getAseqId() {
        return AseqId;
    }

    public void setAseqId(String aseqId) {
        AseqId = aseqId;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getChanCode() {
        return ChanCode;
    }

    public void setChanCode(String chanCode) {
        ChanCode = chanCode;
    }
}