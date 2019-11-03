package com.example.phamduykien_sqlite;

public class SinhVien {
    int id;
    int mssv;
    String tenSV;

    public SinhVien(int id, int mssv, String tenSV) {
        this.id = id;
        this.mssv = mssv;
        this.tenSV = tenSV;
    }

    public SinhVien(int mssv, String tenSV) {
        this.mssv = mssv;
        this.tenSV = tenSV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    @Override
    public String toString() {
        return " ID:   "+ id + ",    MSSV:    " + mssv + ",   Tên Sinh Viên:   " + tenSV ;
    }
}
