package com.adlinaputri.resep_makanan;

import com.adlinaputri.resep_makanan.R.drawable;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ResepActivity extends Activity {
	private TextView txtNama, txtResep;
	private ImageView gambar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resep);
		txtNama = (TextView) findViewById(R.id.nama);
		txtResep = (TextView) findViewById(R.id.resep);
		
		gambar = (ImageView) findViewById(R.id.imageView1);

		Bundle b = getIntent().getExtras();
		if (b != null) {
			String nama = b.getString("nama").replace(" ", "");		
			int id = getResources().getIdentifier("com.adlinaputri.resep_makanan:drawable/"+nama.toLowerCase(), null, null);
			gambar.setImageResource(id);
			txtNama.setText(b.getString("nama"));
			txtResep.setText(b.getString("resep"));
		}
	}
}