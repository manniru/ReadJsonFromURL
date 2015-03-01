package com.tricks.readjsonfromurl;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectAll()
        .penaltyLog()
        .penaltyDialog()
        .build());
	
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll()
        .penaltyLog()
        .build());
		
		
		TextView wid = (TextView) findViewById(R.id.wid);
		TextView name = (TextView) findViewById(R.id.name);
		TextView url = (TextView) findViewById(R.id.url);

		
		JSONObject json = null;
		String str = "";
		HttpResponse response;
        HttpClient myClient = new DefaultHttpClient();
        HttpPost myConnection = new HttpPost("http://demos.tricksofit.com/files/json.php");
        
        try {
        	response = myClient.execute(myConnection);
            str = EntityUtils.toString(response.getEntity(), "UTF-8");
            
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        try{
        	JSONArray jArray = new JSONArray(str);
        	json = jArray.getJSONObject(1);
        	
        	
        	wid.setText(json.getString("id"));
        	name.setText(json.getString("name"));
        	url.setText(json.getString("url"));
        	
        	
        } catch ( JSONException e) {
        	e.printStackTrace();            	
        }
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
