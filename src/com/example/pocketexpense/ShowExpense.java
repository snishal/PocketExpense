package com.example.pocketexpense;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ShowExpense extends Fragment {

	TextView t;
	EditText et;
	String username;
	DB_Handler db;
	
	public ShowExpense() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v=inflater.inflate(R.layout.fragment_show_expense, container, false);
		et=(EditText)v.findViewById(R.id.editText1);
		t=(TextView)v.findViewById(R.id.show_amount);
		username=getActivity().getIntent().getExtras().getString("username");
		db=new DB_Handler(v.getContext(),null,null,1);
		v.findViewById(R.id.expense_view).setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				t.setText(db.getexpense(username,et.getText().toString()));
			}
			
		});
		
		return v;
	}


	
}
