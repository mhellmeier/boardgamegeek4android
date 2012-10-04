package com.boardgamegeek.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.boardgamegeek.R;
import com.boardgamegeek.provider.BggContract;
import com.boardgamegeek.util.ForumsUtils;

public class ThreadActivity extends SimpleSinglePaneActivity {
	private String mThreadSubject;
	private String mForumId;
	private String mForumTitle;
	private int mGameId;
	private String mGameName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Intent intent = getIntent();
		mThreadSubject = intent.getStringExtra(ForumsUtils.KEY_THREAD_SUBJECT);
		mForumId = intent.getStringExtra(ForumsUtils.KEY_FORUM_ID);
		mForumTitle = intent.getStringExtra(ForumsUtils.KEY_FORUM_TITLE);
		mGameId = intent.getIntExtra(ForumsUtils.KEY_GAME_ID, BggContract.INVALID_ID);
		mGameName = intent.getStringExtra(ForumsUtils.KEY_GAME_NAME);

		final ActionBar actionBar = getSupportActionBar();
		if (TextUtils.isEmpty(mGameName)) {
			actionBar.setTitle(mForumTitle);
			actionBar.setSubtitle(mThreadSubject);
		} else {
			actionBar.setTitle(mThreadSubject + " - " + mForumTitle);
			actionBar.setSubtitle(mGameName);
		}
	}

	@Override
	protected Fragment onCreatePane() {
		return new ThreadFragment();
	}

	@Override
	protected int getOptionsMenuId() {
		return R.menu.search_only;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				Intent intent = new Intent(this, ForumActivity.class);
				intent.putExtra(ForumsUtils.KEY_FORUM_ID, mForumId);
				intent.putExtra(ForumsUtils.KEY_FORUM_TITLE, mForumTitle);
				intent.putExtra(ForumsUtils.KEY_GAME_ID, mGameId);
				intent.putExtra(ForumsUtils.KEY_GAME_NAME, mGameName);
				startActivity(intent);
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
