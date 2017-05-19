package com.example.pocketexpense;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentAdapter extends FragmentPagerAdapter {

	public MyFragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment f=null;
		switch(arg0){
			case 0:
				f= new AddExpense();
				break;
			case 1:
				f= new InHand();
				break;
			case 2:
				f= new ShowExpense();
				break;
		}
		return f;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
