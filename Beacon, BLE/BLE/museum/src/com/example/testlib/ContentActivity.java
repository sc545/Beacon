package com.example.testlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ContentActivity extends Activity {
	
	static WebView content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_layout);
		Intent intent = getIntent();
		String content_url = intent.getStringExtra("url");
		content = (WebView) findViewById(R.id.content_view);
		content.getSettings().setJavaScriptEnabled(true);
		content.getSettings().setLoadWithOverviewMode(true);
		content.getSettings().setUseWideViewPort(true);
		content.setInitialScale(26);
		if(content_url != null) {
			content.loadUrl(content_url);	
		}else {
			content.loadUrl(TabMainActvity.Home_URL);
		}
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		if(!content.getUrl().equals(TabMainActvity.Home_URL)) {
//			reLoadContent("file:///android_res/drawable/main2.jpg");
//		}
	}
	
	@Override
	protected void onPause() {
		if(content.getUrl() != null && !content.getUrl().equals("file:///android_res/drawable/main2.jpg")) {
			reLoadContent("file:///android_res/drawable/main2.jpg");
		}
		super.onPause();
	}
	
	public static void reLoadContent(String url) {
		if(content != null) {
			content.stopLoading();
			content.loadUrl(url);
		}
			
			
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		content = null;
	}
	
}
