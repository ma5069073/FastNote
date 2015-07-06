package com.makk.fastnote;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.makk.fastnote.base.BaseActivity;
import com.makk.fastnote.data.Note;
import com.makk.fastnote.fragment.NotesFragment;
import com.makk.fastnote.listener.OnNoteStateChangeListener;

@SuppressLint("NewApi")
public class MainActivity extends BaseActivity implements OnNoteStateChangeListener{

	private static final String NOTES_FRAGMENT_TAG = "NotesFragment";
	private static final String EDIT_NOTE_FRAGMENT_TAG = "EditNoteFragment";

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private Toolbar mToolbar;
	private ShareActionProvider mShareActionProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initToolBar();

		initView();

		FragmentManager fm = getSupportFragmentManager();
		fm.addOnBackStackChangedListener(new MyOnBackStackChangedListener());
		// shouldDisplayHomeUp();

		Fragment fragment = fm.findFragmentById(R.id.container);

		if (fragment == null) {
			fragment = new NotesFragment();
			FragmentTransaction ft = fm.beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.add(R.id.container, fragment, NOTES_FRAGMENT_TAG);
			ft.commit();
		}

	}

	private void initView() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
		};
		 mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private void initToolBar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				switch (item.getItemId()) {
				case R.id.action_settings:
					Toast.makeText(MainActivity.this, "action_settings", 0).show();
					break;
				case R.id.action_share:
					mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/*");
					mShareActionProvider.setShareIntent(intent);
					break;
				case R.id.action_search:
					Toast.makeText(MainActivity.this, "action_search", 0).show();
					break;
				default:
					break;
				}
				return true;
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		  mDrawerToggle.onConfigurationChanged(newConfig);  
	}

	/**
	 * 
	 */
	private void shouldDisplayHomeUp() {
		//
		boolean show = getSupportFragmentManager().getBackStackEntryCount() > 0;
		// 给左上角图标的左边加上一个返回的图标 
		getSupportActionBar().setDisplayHomeAsUpEnabled(show);
	}

	/**
	 * 
	 * @author Administrator
	 * 
	 */
	class MyOnBackStackChangedListener implements FragmentManager.OnBackStackChangedListener {

		@Override
		public void onBackStackChanged() {
			shouldDisplayHomeUp();
		}
	}

	@Override
	public void onAddButtonClicked() {
		
	}

	@Override
	public void onNoteDetailsOpen(Note note) {
		
	}

	@Override
	public void onNoteAdded(String title, String content) {
		
	}

	@Override
	public void onNoteChanged(Note newNote, Note oldNote) {
		
	}
	
	

}
