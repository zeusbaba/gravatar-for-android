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

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wareninja.opensource.gravatar4android.common.CONSTANTS;


import android.util.Log;

/**
 * Reference:
 * http://www.json.org/javadoc/org/json/JSONObject.html
 * http://www.json.org/javadoc/org/json/JSONArray.html
 * 
 * @param <T>
 */
public class GroupParser extends AbstractParser<Group> {

	private static final String TAG = "GroupParser";
	
    private Parser<? extends DataType> mSubParser;

    public GroupParser(Parser<? extends DataType> subParser) {
        mSubParser = subParser;
    }
     
    /**
     * When we encounter a JSONObject in a GroupParser, we expect one attribute
     * named 'type', and then another JSONArray attribute.
     */
    public Group<DataType> parse(JSONObject json) throws JSONException {

        Group<DataType> group = new Group<DataType>();
          
        if(CONSTANTS.DEBUG)Log.d(TAG, "json->" + json);
        
        Iterator<String> it = (Iterator<String>)json.keys();
        
        group.setType(json.has("_type")?json.getString("_type"):"");
        
        it = (Iterator<String>)json.keys();
        while (it.hasNext()) {
            String key = it.next();
            /*if (key.equals("type")) {
                group.setType(json.getString(key));
            } else {*/
            if (key.equals("items")) {
                Object obj = json.get(key);
                if (obj instanceof JSONArray) {  
                    parse(group, (JSONArray)obj);
                } else {
                    throw new JSONException(TAG+"|Could not parse data.");
                }
            }
        }
        
        return group;
    }
    
    /**
     * Here we are getting a straight JSONArray and do not expect the 'type' attribute.
     */
    @Override
    public Group parse(JSONArray array) throws JSONException {
  
        Group<DataType> group = new Group<DataType>();
        parse(group, array);
        return group;
    }
    
    private void parse(Group group, JSONArray array) throws JSONException {
        for (int i = 0, m = array.length(); i < m; i++) {
            Object element = array.get(i);
            DataType item = null;
            if (element instanceof JSONArray) {
                item = mSubParser.parse((JSONArray)element);
            } else {
                item = mSubParser.parse((JSONObject)element);
            }
            
            group.add(item);
        }
    }
}
