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

import org.json.JSONException;
import org.json.JSONObject;

public class GravatarUserParser extends AbstractParser<GravatarUser> {
 
	private static final String TAG = "GravatarUserParser";
	
    @Override
    public GravatarUser parse(JSONObject json) throws JSONException {

/* 
{"entry":
[
	{"id":"17928694","hash":"68a0123a57212ee72a2c20538dce457f","requestHash":"68a0123a57212ee72a2c20538dce457f"
	,"profileUrl":"http:\/\/gravatar.com\/wareninja","preferredUsername":"wareninja"
	,"thumbnailUrl":"http:\/\/1.gravatar.com\/avatar\/68a0123a57212ee72a2c20538dce457f"
	
	,"displayName":"WareNinja","aboutMe":"www.WareNinja.net\nwww.WareNinja.com"
	,"currentLocation":"Dusseldorf, Germany"
	
	,"name":{"givenName":"YG","familyName":"WareNinja","formatted":"WareNinja"}
	
	,"photos":[{"value":"http:\/\/1.gravatar.com\/avatar\/68a0123a57212ee72a2c20538dce457f","type":"thumbnail"}
			,{"value":"http:\/\/2.gravatar.com\/userimage\/17928694\/65d6303b735759fda577fee3e599a8a5"}]
	,"emails":[{"primary":"true","value":"yg@wareninja.com"}]
	,"ims":[{"type":"gtalk","value":"yg@wareninja.com"}
			,{"type":"skype","value":"yilmaz.guleryuz"}]
	,"urls":[{"value":"http:\/\/","title":"www.WareNinja.com"}
			,{"value":"http:\/\/www.WareNinja.net","title":"WareNinja NetHub"}]
	}
]
}
 */
    	
    	GravatarUser obj = new GravatarUser();
        
    	obj.setId(json.has("id")?json.getString("id"):"");
    	obj.setHash(json.has("hash")?json.getString("hash"):"");
    	obj.setRequestHash(json.has("requestHash")?json.getString("requestHash"):"");
    	obj.setProfileUrl(json.has("profileUrl")?json.getString("profileUrl"):"");
    	obj.setThumbnailUrl(json.has("thumbnailUrl")?json.getString("thumbnailUrl"):"");
    	obj.setPreferredUsername(json.has("preferredUsername")?json.getString("preferredUsername"):"");
    	obj.setDisplayName(json.has("displayName")?json.getString("displayName"):"");
    	obj.setAboutMe(json.has("aboutMe")?json.getString("aboutMe"):"");
    	obj.setCurrentLocation(json.has("currentLocation")?json.getString("currentLocation"):"");
    	
    	JSONObject json1;
    	if (json.has("name")) {
    		
    		json1 = json.getJSONObject("name");
    		obj.setGivenName(json1.has("givenName")?json1.getString("givenName"):"");
    		obj.setFamilyName(json1.has("familyName")?json1.getString("familyName"):"");
    		obj.setFormatted(json1.has("formatted")?json1.getString("formatted"):"");
    	}
    	
    	// TODO: parse photos, emails, ims, urls
    
        return obj;
    }
}