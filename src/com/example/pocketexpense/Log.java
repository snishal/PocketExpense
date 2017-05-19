package com.example.pocketexpense;

import com.example.pocketexpense.R;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.ActionBar.Tab;

public class Log extends FragmentActivity implements ActionBar.TabListener{
	
	ActionBar action;
	ViewPager view;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log);
		view=(ViewPager)findViewById(R.id.viewpager);
		final MyFragmentAdapter myadapter=new MyFragmentAdapter(getSupportFragmentManager());
		
		
		action=getActionBar();
		action.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab tab1=action.newTab();
		tab1.setText("AddExpenses");
		tab1.setTabListener(this);
		
		ActionBar.Tab tab2=action.newTab();
		tab2.setText("InHand");
		tab2.setTabListener(this);
		
		ActionBar.Tab tab3=action.newTab();
		tab3.setText("ShowExpenses");
		tab3.setTabListener(this);
		
		action.addTab(tab1);
		action.addTab(tab2);
		action.addTab(tab3);
		action.selectTab(tab2);
		view.setAdapter(myadapter);
		view.setCurrentItem(1);
		view.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(final int arg0) {
				// TODO Auto-generated method stub
				action.setSelectedNavigationItem(arg0);
				android.util.Log.d("Mytag", "age Selected");
				if(arg0==1){
					InHand obj=(InHand) myadapter.instantiateItem(view,arg0);
					if(obj!=null)
						obj.setamount();
				}
			}
			
			@Override
			public void onPageScrolled(final int arg0, final float arg1, final int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(final int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id==R.id.menu_logout)
			finish();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		view.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
