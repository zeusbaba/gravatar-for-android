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
package com.wareninja.opensource.gravatar4android.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wareninja.opensource.gravatar4android.common.CONSTANTS;
import com.wareninja.opensource.gravatar4android.common.GenericException;

import java.io.IOException;
import java.util.Iterator;

public class JSONUtils {
    
	private static final String TAG = "JSONUtils";
    private static final boolean DEBUG = CONSTANTS.DEBUG;
    
    /**
     * Takes a parser, a json string, and returns a foursquare type.
     */
    public static DataType consume(Parser<? extends DataType> parser, String content)
        throws IOException {
        
        if (DEBUG)Log.d(TAG, "content: " + content);

        try {
            // Depending on the
            // type of API call, the content might be a JSONObject or a JSONArray.
            // Since JSONArray does not derive from JSONObject, we need to check for
            // either of these cases to parse correctly.
            JSONObject json = new JSONObject(content);
            
            if(DEBUG)Log.d(TAG, "json-> " + json);
    
            Iterator<String> it = (Iterator<String>)json.keys();
            if (it.hasNext()) {
                String key = (String)it.next();
                
                Object obj = json.get(key);
                if (obj instanceof JSONArray) {
                	if(DEBUG)Log.d(TAG, "obj instanceof JSONArray-> " + obj);
                	
                    return parser.parse((JSONArray)obj);
                } else {
                	
                	if(DEBUG)Log.d(TAG, "obj instanceof JSONObject-> " + obj);
                    return parser.parse((JSONObject)obj);
                }
            } else {
                throw new GenericException("Error parsing JSON response, object had no single child key.");
            }
            
        } catch (JSONException ex) {
        	
        	throw new GenericException("Error parsing JSON response: " + ex.getMessage());
        }
    }
}