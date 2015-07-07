package com.christianjandl.phototask.phototask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.christianjandl.phototask.R;
import com.christianjandl.phototask.phototask.adater.CustomListAdapter;
import com.christianjandl.phototask.phototask.app.AppController;
import com.christianjandl.phototask.phototask.model.Picture;
import com.christianjandl.phototask.phototask.model.Task;

public class MainActivity extends Activity {
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();

	// Movies json url
	private static final String url = "http://dm141534.students.fhstp.ac.at/phototask_api/api/tasks";
	private ProgressDialog pDialog;
	private List<Task> taskList = new ArrayList<Task>();
	private ArrayList<Picture> picList = new ArrayList<Picture>();

	public ArrayList<Picture> preview_pics = new ArrayList();

 	private List<Log> logList = new ArrayList<Log>();
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, taskList);
		listView.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		// changing action bar color
	//	getActionBar().setBackgroundDrawable(
			//	new ColorDrawable(Color.parseColor("#1b1b1b")));

		// Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {
								JSONObject obj = response.getJSONObject(i);
								Task task = new Task();

								task.setName(obj.getString("name"));
								task.setPlate(obj.getString("plate"));
								task.setStaff(obj.getString("staff"));
								task.setJobnumber(obj.getString("jobnumber"));
								task.setId(obj.getString("id"));
								task.setDate(obj.getInt("date"));
								// Pictures are in  json array
								JSONArray pictureArray = obj.getJSONArray("pictures");
								//JSONArray previewArray = obj.getJSONArray("preview_pic");

								// new Object from Picture
								Picture picture = new Picture();
								Picture preview_pic = new Picture();



								for (int j = 0; j < pictureArray.length(); j++) {

									// get one object of the array
									JSONObject jsonPic = pictureArray.getJSONObject(j);

									picture.setID(jsonPic.getInt("ID"));
									picture.setTaskId(jsonPic.getInt("taskId"));
									picture.setPic_link(jsonPic.getString("pic_link"));
									picture.setThumb_link(jsonPic.getString("thumb_link"));
									picture.setMade_by(jsonPic.getString("made_by"));
									picture.setIs_preview(jsonPic.getInt("is_preview"));
									picture.setPic_date(jsonPic.getInt("pic_date"));

									//Preview Picture

									if(picture.getIs_preview() == 1){

										task.setPreviewpic(picture);
										Log.d(TAG, "Vorschaubild");
										//preview_pics.add(preview_pic);
									}

									picList.add(j, picture);
								}

								task.setPictures(picList);

								// adding task to tasks array
								taskList.add(task);

							} catch (JSONException e) {
								e.printStackTrace();
							}
						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
