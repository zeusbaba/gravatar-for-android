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

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ExampleApp extends Activity {
    
	protected static final String TAG = "ExampleApp";
	public Activity mActivity;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        
        setContentView(R.layout.example_app);
        
        Gravatar gravatar = new Gravatar();
        gravatar.setSize(128);
        gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
        gravatar.setDefaultImage(GravatarDefaultImage.RETRO);
        String url = gravatar.getUrl("yg@wareninja.com");
        if(CONSTANTS.DEBUG)Log.d(TAG, "url->" + url);
        
        gravatar.download("yg@wareninja.com", new GenericRequestListener() {

			@Override
			public void onComplete_wBundle(final Bundle params) {
				super.onComplete_wBundle(params);
				
				mActivity.runOnUiThread(new Runnable() {
	                public void run() {
	                	processResponse(params);
	                }
				});
			}		
        });
    }
    
    public void processResponse(Bundle response) {
    	
    	GravatarResponseData gravatarResponseData = (GravatarResponseData)response.getSerializable("gravatarResponseData");
    	
    	if(CONSTANTS.DEBUG)Log.d(TAG, "gravatarResponseData->" + gravatarResponseData);

    	ImageView gravatarImage = (ImageView)findViewById(R.id.img_gravatar);
    	TextView resultText = (TextView)findViewById(R.id.tv_result);
    	
    	if(gravatarResponseData.getStatus()==1) {
    		
    		resultText.setText( Html.fromHtml("<br/><b>SUCCESS..!</b>") );
    		gravatarImage.setImageBitmap(BitmapFactory.decodeByteArray(gravatarResponseData.getImageData(), 0, gravatarResponseData.getImageData().length));
            
    	}
    	else {
    		resultText.setText( Html.fromHtml("<br/><b>FAIL..!</b> -><br/> " + gravatarResponseData.getErrorMessage()) );
    	}
    }
}