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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import android.os.Bundle;
import android.util.Log;


public abstract class GenericRequestListener {
	
	private static final String TAG = "GenericRequestListener";
	
	public void onBegin() {
		if(CONSTANTS.DEBUG)Log.d(TAG, "onBegin()");
	}
	public void onComplete(String response) {
		if(CONSTANTS.DEBUG)Log.d(TAG, "onComplete():" + response);
	}
	public void onComplete_wBundle(Bundle params) {
		if(CONSTANTS.DEBUG)Log.d(TAG, "onComplete_wBundle():" + params);
	}
	
    public void onError(String e) {
    	if(CONSTANTS.DEBUG)Log.d(TAG, "onError():" + e);
    }
    
    public void onFileNotFoundException(FileNotFoundException e) {
        Log.e(TAG, e.getMessage());
        e.printStackTrace();
    }

    public void onIOException(IOException e) {
        Log.e(TAG, e.getMessage());
        e.printStackTrace();
    }

    public void onMalformedURLException(MalformedURLException e) {
        Log.e(TAG, e.getMessage());
        e.printStackTrace();
    }
    
    
    
}
