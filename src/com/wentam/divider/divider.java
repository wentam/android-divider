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

import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetManager;

import android.content.Context;
import android.content.SharedPreferences;

import android.widget.RemoteViews;

import android.util.Log;

public class divider extends AppWidgetProvider {

 public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

	// loop over all widget ids
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

	    // get orientation
	    SharedPreferences prefs = context.getSharedPreferences("orientation",0);
	    int orientation = prefs.getInt(Integer.toString(appWidgetId),0);

	    // load correct views
	    RemoteViews views;
	    if (orientation == 1) {
		views = new RemoteViews(context.getPackageName(),
					R.layout.main_v);
	    } else {
		views = new RemoteViews(context.getPackageName(),
					R.layout.main);
	    }

	    // update widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

}