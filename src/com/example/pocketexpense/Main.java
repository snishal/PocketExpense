package com.example.pocketexpense;

import com.example.pocketexpense.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Main extends Activity {

	EditText username,password;
	DB_Handler db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db=new DB_Handler(this,null,null,1);
		username=(EditText)findViewById(R.id.main_username);
		password=(EditText)findViewById(R.id.main_password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void reg(View v){
		Intent i=new Intent(this,Register.class);
		startActivity(i);
	}
	
	public void login(View v){
		
		if(db.verifyUser(username.getText().toString(), password.getText().toString())){
			Intent i=new Intent(this,Log.class);
			i.putExtra("name", db.getName(username.getText().toString()));
			i.putExtra("username", username.getText().toString());
			startActivity(i);
		}
		else{
			Toast.makeText(this,"Invalid Combination",Toast.LENGTH_SHORT).show();
		}
		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		username.setText("");
		password.setText("");
	}
	
	
}
