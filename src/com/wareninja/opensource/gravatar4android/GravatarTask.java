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

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.commonsware.cwac.task.AsyncTaskEx;

public class GravatarTask extends AsyncTaskEx<Bundle, Void, GravatarResponseData> {
	
	protected final static String TAG = "GravatarTask";
	
	Bundle resultParams;
	GravatarResponseData gravatarResponseData;
	
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
		
		return(gravatarResponseData);
	}
}
