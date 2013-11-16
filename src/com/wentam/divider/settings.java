//////////////////////////////////////////////////////////////////////////////
// Copyright 2012 Matthew Egeler
// 									       
// Licensed under the Apache License, Version 2.0 (the "License");	       
// you may not use this file except in compliance with the License.	       
// You may obtain a copy of the License at				      
// 									       
//     http://www.apache.org/licenses/LICENSE-2.0			       
// 									       
// Unless required by applicable law or agreed to in writing, software      
// distributed under the License is distributed on an "AS IS" BASIS,	       
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
// See the License for the specific language governing permissions and      
// limitations under the License.					      
//////////////////////////////////////////////////////////////////////////////


package com.wentam.divider;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.appwidget.AppWidgetManager;

import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import android.widget.ListView;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

import android.widget.ArrayAdapter;

import android.net.Uri;

public class settings extends Activity {
    private int widget_id;

    static final String[] orientations = new String[] {"Horizontal","Vertical"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	
	Intent intent = getIntent();
	Bundle extras = intent.getExtras();
	if (extras != null) {
	    widget_id = extras.getInt(
					 AppWidgetManager.EXTRA_APPWIDGET_ID,
					 AppWidgetManager.INVALID_APPWIDGET_ID);
	}

	setContentView(R.layout.orientation);

	ListView l = (ListView) findViewById(R.id.list);
    TextView ctext = (TextView) findViewById(R.id.ctext);
    TextView button = (TextView) findViewById(R.id.button);

    ctext.setTextSize(10 * getResources().getDisplayMetrics().density);
    button.setTextSize(10 * getResources().getDisplayMetrics().density);

	final ArrayAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.list_item, new ArrayList<String>(Arrays.asList(orientations)) );
	l.setAdapter(listAdapter);

	l.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick (AdapterView<?> parent, View v, int position, long id) {
		    create_widget((int) id);
		}
	    });

	// intent test
	// Intent i = new Intent("com.wentam.defcol.ACTION_SELECT_COLOR");
	// i.putExtra("startingColor",""+0xFF000000);
	// i.putExtra("color_id","-1");
	// startActivityForResult(i,0);
    }

    private void create_widget(int orientation) {
       	AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

	RemoteViews views;
	if (orientation == 1) {
	    views = new RemoteViews(this.getPackageName(),
				    R.layout.main_v);
	} else {
	    views = new RemoteViews(this.getPackageName(),
				    R.layout.main);
	}

	// save orientation with widget_id
	SharedPreferences prefs = getSharedPreferences("orientation",0);
	SharedPreferences.Editor editor = prefs.edit();
	editor.putInt(Integer.toString(widget_id),orientation);
	editor.commit();

	appWidgetManager.updateAppWidget(widget_id, views);

	Log.i("DIVIDER", "widget_id: "+Integer.toString(widget_id));

	Intent resultValue = new Intent();
	resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widget_id);
	setResult(RESULT_OK, resultValue);


	finish();
    }
    

    public void try_pro(View v) {
        final Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.wentam.dividerpro"));
        startActivity(marketIntent);
    }
}
