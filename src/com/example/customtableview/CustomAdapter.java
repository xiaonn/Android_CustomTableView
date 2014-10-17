package com.example.customtableview;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class CustomAdapter extends BaseAdapter {

	private Context context;//获取使用adapter的上下文
	private List<Map<String, Object>> data;//数据源

	/**!
	 * CustomAdapter构造方法
	 * */
	public CustomAdapter(Context context, List<Map<String, Object>> data) {
		// TODO Auto-generated constructor stub
		this.context = context;//赋值
		this.data = data;
	}

	/**!
	 * 初始化adpter的时候会访问该函数，获取item的数量
	 * */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	/**!
	 * 返回索引位置的item
	 * */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	/**!
	 * 获取索引位置item的id
	 * */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/**!
	 * 首先获取item数量之后在这里绘制每条item
	 * */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		//创建CellModel统一管理
		CellModel cellModel = null;

		//判断是否convertView存在，不存在则创建convertView，存在的话重用该convertView
		if (null == convertView) {
			cellModel = new CellModel();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.cell_tableview, null);//获取item对象的布局文件
			cellModel.image = (ImageView) convertView.findViewById(R.id.image);
			cellModel.title = (TextView) convertView.findViewById(R.id.title);
			cellModel.info = (TextView) convertView.findViewById(R.id.info);
			cellModel.button = (Button) convertView.findViewById(R.id.button);
			convertView.setTag(cellModel);//将cellModel作为tag赋值给convertView以便调取该cellModel
		} else {
			cellModel = (CellModel) convertView.getTag();//convertView不为空，不需重新创建
		}
		
		//根据数据源进行赋值
		cellModel.image.setBackgroundResource((Integer) data.get(position).get("image"));
		cellModel.title.setText((String)data.get(position).get("title"));
		cellModel.info.setText((String)data.get(position).get("info"));
		cellModel.button.setTag(position);
		cellModel.button.setOnClickListener(new cellButtonOnClickListener());
		
		return convertView;
	}
	
	public class cellButtonOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			((MainActivity)context).cellButtonClick((Integer) v.getTag());
		}
		
	}
	
}
