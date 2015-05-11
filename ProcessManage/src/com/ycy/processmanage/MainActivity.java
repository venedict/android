package com.ycy.processmanage;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

public class MainActivity extends Activity {

	@ViewInject(R.id.ps)
	private ListView ps;

	private Adapter adapter;

	private ActivityManager activityManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ViewUtils.inject(this);

		adapter = new Adapter(MainActivity.this, new ArrayList<RunningAppProcessInfo>());
		ps.setAdapter(adapter);

		activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);

		// 获取进程信息***************************************************
		List<RunningAppProcessInfo> infos = activityManager.getRunningAppProcesses();

		adapter.addAllItem(infos);
		adapter.notifyDataSetChanged();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@OnItemClick(R.id.ps)
	public void itemClick(AdapterView<?> parent, View view, int position, long id) {

		RunningAppProcessInfo runningAppProcessInfo = this.adapter.getItem(position);

		if (runningAppProcessInfo.processName.indexOf("android") == -1 && runningAppProcessInfo.processName.indexOf(this.getPackageName()) == -1) {
			// 关闭进程
			activityManager.killBackgroundProcesses(runningAppProcessInfo.processName);
			Toast.makeText(MainActivity.this, "删除成功！", Toast.LENGTH_LONG);
		} else {
			Toast.makeText(MainActivity.this, "不允许删除！", Toast.LENGTH_LONG);
		}

	}

}
