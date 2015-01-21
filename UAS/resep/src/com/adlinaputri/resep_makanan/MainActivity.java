package com.adlinaputri.resep_makanan;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements TextWatcher, OnItemClickListener {
	private EditText			search;
	private ListView			lv;
	private DatabaseHelper		dbHelper;
	private ArrayAdapter<Resep>	adapter;
	private List<Resep>			listKamus;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv_data);
		lv.setEmptyView(findViewById(R.id.empty));
		search = (EditText) findViewById(R.id.search);

		dbHelper = DatabaseHelper.getInstance(this);

		setData();

		search.addTextChangedListener(this);
		lv.setOnItemClickListener(this);
	}

	private void setData()
	{
		listKamus = dbHelper.getAllKamus();

		adapter = new ArrayAdapter<Resep>(this, android.R.layout.simple_expandable_list_item_1, listKamus);
		lv.setAdapter(adapter);

	}


	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id)	{
		// TODO Auto-generated method stub
		Bundle b = new Bundle();
		b.putString("nama", adapter.getItem(position).getNama());
		b.putString("resep", adapter.getItem(position).getResep());

		Intent i = new Intent(this, ResepActivity.class);
		i.putExtras(b);
		startActivity(i);

	}

	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		adapter.getFilter().filter(arg0.toString());
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		case R.id.action_tambah:
    			Intent i = new Intent(MainActivity.this, TambahActivity.class);
            	startActivity(i);
            	finish();
                return true;
                
    		default:
                return super.onOptionsItemSelected(item);
            
    	}
	}

}
