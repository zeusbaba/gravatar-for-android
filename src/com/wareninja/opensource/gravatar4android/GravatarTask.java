/***
 * 	Copyright (c) 2011 WareNinja.com
 * 	Author: yg@wareninja.com
 *  http://www.WareNinja.net - https://github.com/wareninja	
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
*/


package com.wareninja.opensource.gravatar4android;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.commonsware.cwac.task.AsyncTaskEx;
import com.wareninja.opensource.gravatar4android.common.CONSTANTS;
import com.wareninja.opensource.gravatar4android.common.GenericRequestListener;
import com.wareninja.opensource.gravatar4android.common.Utils;
import com.wareninja.opensource.gravatar4android.data.GravatarUser;
import com.wareninja.opensource.gravatar4android.data.GravatarUserParser;
import com.wareninja.opensource.gravatar4android.data.JSONUtils;

public class GravatarTask extends AsyncTaskEx<Bundle, Void, GravatarResponseData> {
	
	protected final static String TAG = "GravatarTask";
	
	Bundle resultParams;
	GravatarResponseData gravatarResponseData;
	
	int taskAction = CONSTANTS.TASK_ACTION_IMAGE;//by default
	public int getTaskAction() {
		return taskAction;
	}
	public void setTaskAction(int taskAction) {
		this.taskAction = taskAction;
	}

	Context taskContext;
	public Context getTaskContext() {
		return taskContext;
	}
	public void setTaskContext(Context taskContext) {
		this.taskContext = taskContext;
	}
	
	GenericRequestListener mReqListener;
	public GenericRequestListener getReqListener() {
		return mReqListener;
	}
	public void setReqListener(GenericRequestListener mReqListener) {
		this.mReqListener = mReqListener;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	
		
		resultParams = new Bundle();
		gravatarResponseData = new GravatarResponseData();
	}
	@Override
	protected void onPostExecute(GravatarResponseData result) {
		super.onPostExecute(result);
		
		resultParams.putSerializable("gravatarResponseData", gravatarResponseData);
    	if(CONSTANTS.DEBUG)Log.d(TAG, "gravatarResponseData->"+gravatarResponseData);    	
    	
		mReqListener.onComplete_wBundle(resultParams);
	}
	@Override
	protected GravatarResponseData doInBackground(Bundle... params) {
		
		if ( CONSTANTS.TASK_ACTION_IMAGE==taskAction ) {
		
			String imageUrl = params[0].getString("imageUrl");
			gravatarResponseData.setImageUrl(imageUrl);
			try {
				
				//byte[] imageData = Utils.downloadImage_alternative1(imageUrl);
				byte[] imageData = Utils.downloadImage_alternative2(imageUrl);
				
				gravatarResponseData.setStatus(1);
				gravatarResponseData.setImageData(imageData);
				
			}
			catch (Exception ex) {
				gravatarResponseData.setStatus(0);
				gravatarResponseData.setErrorMessage(ex.toString());
				Log.w(TAG, ex.toString());
			}
		}
		else if ( CONSTANTS.TASK_ACTION_PROFILE==taskAction ) {
		
			String profileUrl = params[0].getString("profileUrl");
			gravatarResponseData.setProfileUrl(profileUrl);
			
			GravatarUser gravatarUser = new GravatarUser();
			try {
				
				String response = Utils.downloadProfile(profileUrl);
				
				JSONArray jsonArr = (new JSONObject(response)).getJSONArray("entry");
				if(CONSTANTS.DEBUG)Log.d(TAG, "jsonArr->"+jsonArr);
				if (jsonArr.length()>0) {
					
					if(CONSTANTS.DEBUG)Log.d(TAG, "jsonArr[0]->"+jsonArr.getJSONObject(0));
					
					gravatarUser = (GravatarUser)JSONUtils.consume(new GravatarUserParser(), "{\"items\":"+jsonArr.getJSONObject(0) + "}");
				}
				
				if (!TextUtils.isEmpty(gravatarUser.getId())) {
					gravatarResponseData.setStatus(1);
					gravatarResponseData.setGravatarUser(gravatarUser);
				}
				else {
					gravatarResponseData.setStatus(0);
					gravatarResponseData.setGravatarUser(gravatarUser);
					gravatarResponseData.setErrorMessage("Unable to parse GravatarUser!");
				}
				
			}
			catch (Exception ex) {
				gravatarResponseData.setStatus(0);
				gravatarResponseData.setErrorMessage(ex.toString());
				Log.w(TAG, ex.toString());
			}
		}
		
		return(gravatarResponseData);
	}
}
