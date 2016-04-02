package com.example.wifilist;

import java.util.List;


import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class WifiListActivity extends Activity {

	private WifiManager wifiManager;
	List<WifiConfiguration> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		list = wifiManager.getConfiguredNetworks();
		ListView listView = (ListView) findViewById(R.id.listView);
		if (list == null) {
			Toast.makeText(this, "wifi未打开！", Toast.LENGTH_LONG).show();
		}else {
			listView.setAdapter(new MyAdapter(this,list));
		}

	}

	public class MyAdapter extends BaseAdapter {

		LayoutInflater inflater;
		List<WifiConfiguration> list;
		public MyAdapter(Context context, List<WifiConfiguration> list2) {
			this.inflater = LayoutInflater.from(context);
			this.list = list2;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			view = inflater.inflate(R.layout.item_wifi_list, null);
			WifiConfiguration scanResult = list.get(position);
			TextView textView = (TextView) view.findViewById(R.id.textView);
			textView.setText(scanResult.SSID);
			TextView signalStrenth = (TextView) view.findViewById(R.id.signal_strenth);
			signalStrenth.setText("wifi"/*String.valueOf(Math.abs(scanResult.level))*/);
			ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
			//判断信号强度，显示对应的指示图标
//			if (Math.abs(scanResult.level) > 100) {
//				imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_0));
//			} else if (Math.abs(scanResult.level) > 80) {
//				imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_1));
//			} else if (Math.abs(scanResult.level) > 70) {
//				imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_1));
//			} else if (Math.abs(scanResult.level) > 60) {
//				imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_2));
//			} else if (Math.abs(scanResult.level) > 50) {
//				imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_3));
//			} else {
//				imageView.setImageDrawable(getResources().getDrawable(R.drawable.stat_sys_wifi_signal_4));
//			}
			return view;
		}

	}

}
