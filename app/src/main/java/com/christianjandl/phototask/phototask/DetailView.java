package com.christianjandl.phototask.phototask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.android.volley.toolbox.JsonObjectRequest;
import com.christianjandl.phototask.R;
import com.christianjandl.phototask.phototask.adater.CustomListAdapter;
import com.christianjandl.phototask.phototask.app.AppController;
import com.christianjandl.phototask.phototask.model.Picture;
import com.christianjandl.phototask.phototask.model.Task;

public class DetailView extends Activity {


    private static final String TAG = MainActivity.class.getSimpleName();
    public final static String  EXTRA_MESSAGE =  "com.christianjandl.phototask.MESSAGE" ;
    // tasks json url
    private static final String url = "http://dm141534.students.fhstp.ac.at/phototask_api/api/tasks/" ;
    //private ProgressDialog pDialog;

    Task task = new Task();

    private ArrayList<Picture> picList = new ArrayList<Picture>();
    private List<Log> logList = new ArrayList<Log>();

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        pDialog = new ProgressDialog(this);

        final TextView name = (TextView) findViewById(R.id.detailname);
        final TextView plate = (TextView) findViewById(R.id.detail_plate);
        final TextView date = (TextView) findViewById(R.id.detail_date);


        Intent i = getIntent();
        String taskId = i.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.d(TAG, taskId);

        // Showing progress dialog before making http request
       // pDialog.setMessage("Loading...");
       // pDialog.show();

        // Creating volley request obj
        final String urlWithId = url + taskId;
        Toast.makeText(getApplicationContext(), "API-Url: " + urlWithId, Toast.LENGTH_LONG).show();

        JsonObjectRequest taskRequest = new JsonObjectRequest(Request.Method.GET, urlWithId,null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        // Parsing json
                        pDialog.hide();
                        try {

                                task.setName(response.getString("name"));
                                task.setPlate(response.getString("plate"));
                                task.setStaff(response.getString("staff"));
                                task.setJobnumber(response.getString("jobnumber"));
                                task.setId(response.getString("id"));
                                task.setDate(response.getInt("date"));

                            name.setText(task.getName());
                            plate.setText(task.getPlate());
                            //date.setText(task.getDate());
                            Log.d(TAG, task.getName());



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
              //          adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //hidePDialog();

            }
        });
        AppController.getInstance().addToRequestQueue(taskRequest);







    }







  /*  // Pictures are in  json array
    JSONArray picArray = response.getJSONArray("pictures");
    // new Object from Picture
    Picture picture = new Picture();
    //Picture preview_pic = new Picture();


    for (int j = 0; j < picArray.length(); j++) {

        // get one object of the array
        JSONObject jsonPic = picArray.getJSONObject(j);

        picture.setID(jsonPic.getInt("ID"));
        picture.setTaskId(jsonPic.getInt("taskId"));
        picture.setPic_link(jsonPic.getString("pic_link"));
        picture.setThumb_link(jsonPic.getString("thumb_link"));
        picture.setMade_by(jsonPic.getString("made_by"));
        picture.setIs_preview(jsonPic.getInt("is_preview"));
        picture.setPic_date(jsonPic.getInt("pic_date"));

        //Preview Picture
        if (picture.getIs_preview() == 1) {
            task.setPreviewpic(picture);
            Log.d(TAG, "Vorschaubild");
        }

        picList.add(j, picture);
    }

    task.setPictures(picList);*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
