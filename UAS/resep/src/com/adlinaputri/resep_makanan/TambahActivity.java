package com.adlinaputri.resep_makanan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends Activity {
	Button back, tambah;
	EditText txtNama, txtResep;
	private DatabaseHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tambah);
		
		dbHelper = DatabaseHelper.getInstance(this);
		
		txtNama = (EditText) findViewById(R.id.textNama);
		txtResep	   = (EditText) findViewById(R.id.textResep);
		
		back = (Button) findViewById(R.id.btnBatal);
		back.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(TambahActivity.this, MainActivity.class);
            	startActivity(i);
            	finish();
			}
		});
		
		tambah = (Button) findViewById(R.id.btnTambah);
		tambah.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String istilah = txtNama.getText().toString();
				String arti    = txtResep.getText().toString();
				
				dbHelper.create(nama,resep);
                Toast.makeText(getApplicationContext(), "Resep berhasil ditambah ", Toast.LENGTH_LONG).show();
                
                Intent i = new Intent(TambahActivity.this, MainActivity.class);
            	startActivity(i);
            	finish();
			}
		});
	}
}
