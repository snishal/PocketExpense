package com.example.pocketexpense;

import com.example.pocketexpense.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

	EditText name,username,password,confirm,amount;
	DB_Handler db;
	User u;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		name=(EditText)findViewById(R.id.register_name);
		username=(EditText)findViewById(R.id.register_username);
		password=(EditText)findViewById(R.id.register_password);
		confirm=(EditText)findViewById(R.id.register_confirm);
		amount=(EditText)findViewById(R.id.register_amount);
		db=new DB_Handler(this,null,null,1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
	
	public void register(View v){
	
		if(confirm.getText().toString().equals(password.getText().toString())){
			u=new User();
			u.setName(name.getText().toString());
			u.setUsername(username.getText().toString());
			u.setPassword(password.getText().toString());
			u.setAmount(Integer.parseInt(amount.getText().toString()));
			if(db.addUser(u))
				Toast.makeText(this, "Registered Succesfully!!", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(this, "Already registered...Pls Login", Toast.LENGTH_SHORT).show();
		}
		else
			Toast.makeText(this, "Password Not Match", Toast.LENGTH_SHORT).show();
		finish();
		
	}
}
