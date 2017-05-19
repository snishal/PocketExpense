package com.example.pocketexpense;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
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
public class InHand extends Fragment {

	TextView t,t1;
	DB_Handler db;
	String username;
	public InHand() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v=inflater.inflate(R.layout.fragment_in_hand, container, false);
		t=(TextView)v.findViewById(R.id.inhand_title);
		db=new DB_Handler(getContext(),null,null,1);
		username=getActivity().getIntent().getExtras().getString("username");
		String s=getActivity().getIntent().getExtras().getString("name");
		t.append(s);
		t1=(TextView)v.findViewById(R.id.inhand_amount);
		t1.setText("InHand : ");
		t1.append(((Integer)db.inhand(username)).toString());		
		t1.append("\nPersonal Expense : ");
		t1.append(((Integer)db.personal(username)).toString());
		t1.append("\nSharing Expense : ");
		t1.append(((Integer)db.sharing(username)).toString());
		v.findViewById(R.id.button1).setOnClickListener(new OnClickListener(){
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
				alert.setTitle("Add Amount");
				alert.setMessage("Add Money in your Pocket");
				final EditText amount = new EditText(getActivity());
				alert.setView(amount);
				alert.setPositiveButton("Add", new DialogInterface.OnClickListener(){	
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String add=amount.getText().toString();
						db.add_amount(username, Integer.parseInt(add));
						setamount();
					}
				});
				alert.show();
			}
			
		});
		return v;
	}
	
	public void setamount(){
		t1=(TextView)getView().findViewById(R.id.inhand_amount);
		t1.setText("InHand : ");
		Integer amount=(Integer)db.inhand(username);
		if(amount<200){
			NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(getActivity());
			mbuilder.setSmallIcon(R.drawable.notify);
			mbuilder.setContentTitle("Amount Low");
			mbuilder.setContentText("Refill Your Account!!!");
			NotificationManager mnotify =(NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
			mnotify.notify(1, mbuilder.build());
		}
		t1.append(amount.toString());		
		t1.append("\nPersonal Expense : ");
		t1.append(((Integer)db.personal(username)).toString());
		t1.append("\nSharing Expense : ");
		t1.append(((Integer)db.sharing(username)).toString());
	}
}
