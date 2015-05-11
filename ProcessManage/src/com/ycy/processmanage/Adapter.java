package com.ycy.processmanage;

import java.util.List;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends BaseAdapter {

	private static final String TAG = Adapter.class.getSimpleName();

	private List<RunningAppProcessInfo> coll;

	private Context ctx;

	private LayoutInflater mInflater;

	public Adapter(Context context, List<RunningAppProcessInfo> coll) {
		ctx = context;
		this.coll = coll;

		mInflater = LayoutInflater.from(context);

	}

	public int getCount() {
		return coll.size();
	}

	public void clearAll() {
		this.coll.clear();
	}

	public void addItem(RunningAppProcessInfo item) {
		this.coll.add(item);
	}

	public void addAllItem(List<RunningAppProcessInfo> coll) {
		this.coll = coll;
	}

	public void removeItem(int position) {
		coll.remove(position);
	}

	public RunningAppProcessInfo getItem(int position) {
		return coll.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		RunningAppProcessInfo entity = coll.get(position);

		ViewHolder viewHolder = null;
		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.item, null);

			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.name.setText(entity.processName);

		return convertView;
	}

	static class ViewHolder {
		public TextView name;

	}

}
