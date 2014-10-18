package com.example.customtableview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity implements CellButtonClick{

	public CustomAdapter adapter;
	public ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化listView并加载数据源
		List<Map<String, Object>> list = getData();
		adapter = new CustomAdapter(this, list);
	    listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
	}

	/**
	 * 数据
	 * */
	public List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 30; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "标题" + String.valueOf(i));
			map.put("info", "详情" + String.valueOf(i));
			map.put("image", R.drawable.codefish);
			list.add(map);
		}
		return list;
	}

	@Override
	public void cellButtonClick(int position) {
		// TODO Auto-generated method stub
		//更改数据源
		HashMap<String, Object> map = (HashMap<String, Object>) adapter.getItem(position);
		map.put("info", "i love ios&android");
		
		adapter.notifyDataSetChanged();//刷新listView
	}

}
