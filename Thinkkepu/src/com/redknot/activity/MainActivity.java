package com.redknot.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.redknot.fragment.MainFragment;
import com.redknot.thinkkepu.R;
import com.redknot.util.ClassList;
import com.redknot.util.ClassListDomain;
import com.redknot.util.UserInformation;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {

	// private static String[] CONTENT;
	private List<ClassListDomain> CONTENT = new ArrayList<ClassListDomain>();
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;

	private LinearLayout id_drawer;
	private ImageView head_img;
	private TextView login;

	private Button btn_activity;
	private Button btn_subject;
	private Button btn_setting;
	private Button btn_about;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		Resources ares = getResources();
        Drawable myDrawable = ares.getDrawable(R.drawable.actionbar);
		getActionBar().setBackgroundDrawable(myDrawable);
		
		setContentView(R.layout.activity_main);
		
		//getActionBar().setTitle("");

		init();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		ClassList list = new ClassList(MainActivity.this);
		List<ClassListDomain> listres = list.getList();

		this.CONTENT.clear();
		for (ClassListDomain res : listres) {
			this.CONTENT.add(res);
		}
		System.out.println(listres.size());

		FragmentPagerAdapter adapter = new GoogleMusicAdapter(
				getSupportFragmentManager());

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				// getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				// getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private void init() {
		MyOnClickListener click = new MyOnClickListener();

		id_drawer = (LinearLayout) findViewById(R.id.id_drawer);
		head_img = (ImageView) findViewById(R.id.head_img);
		login = (TextView) findViewById(R.id.login);
		
		id_drawer.setOnClickListener(click);
		head_img.setOnClickListener(click);
		login.setOnClickListener(click);
		
		btn_activity = (Button) findViewById(R.id.btn_activity);
		btn_about = (Button) findViewById(R.id.btn_about);
		btn_subject = (Button) findViewById(R.id.btn_subject);
		btn_setting = (Button) findViewById(R.id.btn_setting);
		
		btn_activity.setOnClickListener(click);
		btn_about.setOnClickListener(click);
		btn_subject.setOnClickListener(click);
		btn_setting.setOnClickListener(click);
	}

	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			if (view == head_img || view == login) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
			} else if (view == id_drawer) {

			} else if (view == btn_activity){
				Intent intent = new Intent(MainActivity.this,ActivityActivity.class);
				startActivity(intent);
			} else if (view == btn_about){
				
			} else if (view == btn_setting){
				
			} else if (view == btn_subject){
				Intent intent = new Intent(MainActivity.this,SubjectActivity.class);
				startActivity(intent);
			}
		}

	}
	

	@Override
	public void setTitle(CharSequence title) {
		getActionBar().setTitle(title);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	class GoogleMusicAdapter extends FragmentPagerAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// return TestFragment.newInstance(CONTENT[position %
			return new MainFragment(CONTENT.get(position % CONTENT.size())
					.getId());
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT.get(position % CONTENT.size()).getName();
		}

		@Override
		public int getCount() {
			return CONTENT.size();
		}
	}
	
	
	
	@Override
	protected void onStart(){
		super.onStart();
		System.out.println("onStart()");
		
		UserInformation user = new UserInformation(MainActivity.this);
		
		String session = user.getLogin_session();
		if(session.equals("<nano>")){
			login.setText(getText(R.string.click_to_login).toString());
			return;
		}
		else{
			login.setText(user.getUsername());
		}
	}

	protected void onRestart(){
		super.onRestart();
		System.out.println("onRestart()");
	}

	@Override
	protected void onResume(){
		super.onResume();
		System.out.println("onResume()");
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		System.out.println("onPause()");
	}

	@Override
	protected void onStop(){
		super.onStop();
		System.out.println("onStop()");
	}

	@Override
	protected void onDestroy(){
		super.onPause();
		System.out.println("onDestroy()");
	}
}
