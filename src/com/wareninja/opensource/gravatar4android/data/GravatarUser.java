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

import java.io.Serializable;
import java.util.Hashtable;

/* 
 * 

{"entry":
[
{"id":"17928694","hash":"68a0123a57212ee72a2c20538dce457f","requestHash":"68a0123a57212ee72a2c20538dce457f"
,"profileUrl":"http:\/\/gravatar.com\/wareninja","preferredUsername":"wareninja"
,"thumbnailUrl":"http:\/\/1.gravatar.com\/avatar\/68a0123a57212ee72a2c20538dce457f"
,"name":{"givenName":"YG","familyName":"WareNinja","formatted":"WareNinja"},"displayName":"WareNinja","aboutMe":"www.WareNinja.net\nwww.WareNinja.com"
,"currentLocation":"Dusseldorf, Germany"

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
public class GravatarUser implements DataType, Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;
	private String hash = null;
	private String requestHash = null;
	private String profileUrl = null;
	private String preferredUsername = null;
	private String thumbnailUrl = null;
	private String givenName = null;
	private String familyName = null;
	private String formatted = null;
	private String displayName = null;
	private String aboutMe = null;
	private String currentLocation = null;
	
	// TODO: photos, emails, ims, urls
	
	
	public Hashtable extraData = new Hashtable();//any extra data as key-value pair
	
    public GravatarUser() {
    }

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * @return the requestHash
	 */
	public String getRequestHash() {
		return requestHash;
	}

	/**
	 * @param requestHash the requestHash to set
	 */
	public void setRequestHash(String requestHash) {
		this.requestHash = requestHash;
	}

	/**
	 * @return the profileUrl
	 */
	public String getProfileUrl() {
		return profileUrl;
	}

	/**
	 * @param profileUrl the profileUrl to set
	 */
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	/**
	 * @return the preferredUsername
	 */
	public String getPreferredUsername() {
		return preferredUsername;
	}

	/**
	 * @param preferredUsername the preferredUsername to set
	 */
	public void setPreferredUsername(String preferredUsername) {
		this.preferredUsername = preferredUsername;
	}

	/**
	 * @return the thumbnailUrl
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	/**
	 * @param thumbnailUrl the thumbnailUrl to set
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the formatted
	 */
	public String getFormatted() {
		return formatted;
	}

	/**
	 * @param formatted the formatted to set
	 */
	public void setFormatted(String formatted) {
		this.formatted = formatted;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the aboutMe
	 */
	public String getAboutMe() {
		return aboutMe;
	}

	/**
	 * @param aboutMe the aboutMe to set
	 */
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	/**
	 * @return the currentLocation
	 */
	public String getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	@Override
	public String toString() {
		return "GravatarUser [aboutMe=" + aboutMe + ", currentLocation="
				+ currentLocation + ", displayName=" + displayName
				+ ", extraData=" + extraData + ", familyName=" + familyName
				+ ", formatted=" + formatted + ", givenName=" + givenName
				+ ", hash=" + hash + ", id=" + id + ", preferredUsername="
				+ preferredUsername + ", profileUrl=" + profileUrl
				+ ", requestHash=" + requestHash + ", thumbnailUrl="
				+ thumbnailUrl + "]";
	}
}
