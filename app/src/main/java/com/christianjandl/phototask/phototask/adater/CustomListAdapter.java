package com.christianjandl.phototask.phototask.adater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import com.christianjandl.phototask.R;
import com.christianjandl.phototask.phototask.app.AppController;
import com.christianjandl.phototask.phototask.model.Task;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Task> taskItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Task> taskItems) {
		this.activity = activity;
		this.taskItems = taskItems;
	}

	@Override
	public int getCount() {
		return taskItems.size();
	}

	@Override
	public Object getItem(int location) {
		return taskItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView name = (TextView) convertView.findViewById(R.id.title);
		TextView plate = (TextView) convertView.findViewById(R.id.rating);
		TextView date = (TextView) convertView.findViewById(R.id.genre);
		TextView staff = (TextView) convertView.findViewById(R.id.releaseYear);
		//TextView jobnumber = (TextView) convertView.findViewById(R.id.jobnumber);


		// getting Task data for the row
		Task m = taskItems.get(position);

		// thumbnail image
		//thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		
		// Name
		name.setText(m.getName());
		
		// plate
		plate.setText("Rating: " + String.valueOf(m.getPlate()));
		
		// jobnumber
		//jobnumber.setText(String.valueOf(m.getJobnumber()));
		
		// release year
		staff.setText(String.valueOf(m.getDate()));

		return convertView;
	}

}