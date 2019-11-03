package com.example.phamduykien_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edtMSSV,edtTenSV;
    private Button btnThem,btnSua,btnXoa;
    private ListView lvSV;
    private ArrayAdapter adapter;
    private ArrayList listsinhvien = new ArrayList();
    DBSV dbsv;
    int idtam = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtMSSV= findViewById(R.id.edtMSSV);
        edtTenSV= findViewById(R.id.edtTenSV);
        btnThem= findViewById(R.id.btnThem);
        btnSua= findViewById(R.id.btnSua);
        btnXoa= findViewById(R.id.btnXoa);
        lvSV= findViewById(R.id.lvSV);
        dbsv = new DBSV(this, "SinhVien.sqlite", null, 1);
        dbsv.query("create table if not exists SinhVien(id integer primary key autoincrement, mssv integer, tenSV text)");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listsinhvien);
        loadDB();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbsv.insert(new SinhVien(Integer.parseInt(edtMSSV.getText().toString()), edtTenSV.getText().toString()));
                loadDB();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idtam != 0) {
                    dbsv.update(new SinhVien(idtam, Integer.parseInt(edtMSSV.getText().toString()), edtTenSV.getText().toString()));
                    idtam = 0;
                    Toast.makeText(MainActivity.this, "Sua", Toast.LENGTH_SHORT).show();
                    loadDB();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idtam != 0) {
                    dbsv.delete(new SinhVien(idtam, Integer.parseInt(edtMSSV.getText().toString()), edtTenSV.getText().toString()));
                    idtam = 0;
                    Toast.makeText(MainActivity.this, "Xoa", Toast.LENGTH_SHORT).show();
                    loadDB();
                }
            }
        });
        lvSV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SinhVien sinhVien = (SinhVien) listsinhvien.get(i);
                edtMSSV.setText(String.valueOf(sinhVien.getMssv()));
                edtTenSV.setText(sinhVien.getTenSV());
                idtam = sinhVien.getId();
            }
        });
    }
    public void loadDB(){
        listsinhvien.clear();
        Cursor cursor = dbsv.queryCursor("Select * from SinhVien");
        while (cursor.moveToNext()){
            listsinhvien.add(new SinhVien(cursor.getInt(0), cursor.getInt(1), cursor.getString(2)));
        }
        lvSV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        edtMSSV.setText("");
        edtTenSV.setText("");
//        Toast.makeText(this, "load ok", Toast.LENGTH_SHORT).show();
    }
}
