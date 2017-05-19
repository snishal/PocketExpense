package com.example.pocketexpense;

import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class AddExpense extends Fragment {
	Expense e;
	EditText expense;
	String username;
	DB_Handler db;
	Activity activity;
	RadioGroup r;
	RadioButton r1;
	public AddExpense() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		final View v=inflater.inflate(R.layout.fragment_add_expense, container, false);
		activity=getActivity();
		expense=(EditText)v.findViewById(R.id.editText1);
		username=activity.getIntent().getExtras().getString("username");
		db=new DB_Handler(v.getContext(),null,null,1);
		r=(RadioGroup)v.findViewById(R.id.radioGroup1);
		v.findViewById(R.id.button1).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				e=new Expense();
				e.setExpense(Integer.parseInt(expense.getText().toString()));
				e.setDate(DateFormat.getDateInstance().format(new Date()));
				r1=(RadioButton)v.findViewById(r.getCheckedRadioButtonId());
				e.setType(r1.getText().toString());
				db.addExpense(username, e);
				Toast.makeText(getContext(), "Submitted", Toast.LENGTH_SHORT).show();
			}
		});
		
		return v;
	}
	
}
