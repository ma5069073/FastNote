package com.makk.fastnote.base;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.makk.fastnote.receiver.ExitAppReceiver;
import com.makk.fastnote.utils.CommonUtil;
import com.makk.fastnote.utils.CustomToast;

/**
 * Activity的基类
 * 
 * @author Administrator
 * 
 */
public abstract class BaseActivity extends AppCompatActivity {
	Context ct;
	private ExitAppReceiver exitReceiver = new ExitAppReceiver();
	// 自定义退出应用Action,实际应用中应该放到整个应用的Constant类中.
	public static final String EXIT_APP_ACTION = "com.makk.demo.exit";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ct = this;
		registerExitReceiver();
	}

	protected void showToast(String msg) {
		showToast(msg, 0);
	}

	protected void showToast(String msg, int time) {
		CustomToast customToast = new CustomToast(ct, msg, time);
		customToast.show();
	}

	protected void loadData(HttpMethod method, String url,
			RequestParams params, RequestCallBack<String> callback) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(0);

		LogUtils.allowD = true;
		if (params != null) {
			if (params.getQueryStringParams() != null)
				LogUtils.d(url + "?" + params.getQueryStringParams().toString());
		} else {
			params = new RequestParams();

		}
		// params.addHeader("x-deviceid", app.deviceId);
		// params.addHeader("x-channel", app.channel);
		if (0 == CommonUtil.isNetworkAvailable(ct)) {
			showToast("加载失败，请检查网络！");
		} else {
			LogUtils.d(url);
			http.send(method, url, params, callback);
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
	}

	private void registerExitReceiver() {
		IntentFilter exitFilter = new IntentFilter();
		exitFilter.addAction(EXIT_APP_ACTION);
		registerReceiver(exitReceiver, exitFilter);
	}

	private void unRegisterExitReceiver() {
		unregisterReceiver(exitReceiver);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unRegisterExitReceiver();
	}

}
