package com.makk.fastnote.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.makk.fastnote.utils.CommonUtil;
import com.makk.fastnote.utils.CustomToast;

/**
 * Fragment的基类
 * @author Administrator
 *
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
	protected Context ct;
	public View rootView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		initData(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		ct = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = initView(inflater);
		return rootView;
	}

	public View getRootView() {
		return rootView;
	}

	protected void showToast(String msg) {
		showToast(msg, 0);
	}

	protected void showToast(String msg, int time) {
		CustomToast customToast = new CustomToast(ct, msg, time);
		customToast.show();
	}

	protected void loadData(
			com.lidroid.xutils.http.client.HttpRequest.HttpMethod method,
			String url, RequestParams params, RequestCallBack<String> callback) {
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
	public void onClick(View arg0) {

	}

	protected abstract View initView(LayoutInflater inflater);

	protected abstract void initData(Bundle savedInstanceState);

	protected abstract void processClick(View v);
}
