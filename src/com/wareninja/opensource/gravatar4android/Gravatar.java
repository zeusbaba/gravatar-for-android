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

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;

/**
 * A gravatar is a dynamic image resource that is requested from the
 * gravatar.com server. This class calculates the gravatar url and fetches
 * gravatar images. See http://en.gravatar.com/site/implement/url .
 * 
 * This class is thread-safe, Gravatar objects can be shared.
 * 
 * Usage example:
 * 
 * <code>
 * Gravatar gravatar = new Gravatar();
 * gravatar.setSize(128);
 * gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
 * gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
 * String url = gravatar.getUrl("yg@wareninja.com");
 * 
 * -> pass request listener to get callback response (as in example app!)
 * gravatar.download("yg@wareninja.com", new GenericRequestListener() {
 * 			
 * 		});
 * </code>
 * 
 * Original base source from https://github.com/ralfebert/jgravatar
 * Adapted/extended for ANDROID by yg@wareninja.com
 */
public final class Gravatar {

	protected static final String TAG = "Gravatar";
	
	private int size = CONSTANTS.DEFAULT_SIZE;
	private GravatarRating rating = CONSTANTS.DEFAULT_RATING;
	private GravatarDefaultImage defaultImage = CONSTANTS.DEFAULT_DEFAULT_IMAGE;

	public Gravatar () {
	}
	
	/**
	 * Specify a gravatar size between 1 and 512 pixels. If you omit this, a
	 * default size of 80 pixels is used.
	 */
	public void setSize(int sizeInPixels) {
		if(sizeInPixels >= 1 && sizeInPixels <= 512)
			this.size = sizeInPixels;
	}

	/**
	 * Specify a rating to ban gravatar images with explicit content.
	 */
	public void setRating(GravatarRating rating) {
		if(rating!=null && !TextUtils.isEmpty(rating.getCode()))
			this.rating = rating;
	}

	/**
	 * Specify the default image to be produced if no gravatar image was found.
	 */
	public void setDefaultImage(GravatarDefaultImage defaultImage) {
		if(defaultImage!=null && !TextUtils.isEmpty(defaultImage.getCode()))
			this.defaultImage = defaultImage;
	}

	/**
	 * Returns the Gravatar URL for the given email address.
	 */
	public String getUrl(String email) {
		if(TextUtils.isEmpty(email))return null;

		// hexadecimal MD5 hash of the requested user's lowercased email address
		// with all whitespace trimmed
		String emailHash = Utils.md5Hex(email.toLowerCase().trim());
		String params = formatUrlParameters();
		return CONSTANTS.API_GRAVATAR_BASE_URL+CONSTANTS.API_GRAVATAR_AVATAR 
			+ emailHash //+ ".jpg"
			+ params;
	}

	public void download(String email, GenericRequestListener reqListener) {
		
		GravatarTask mGravatarTask = new GravatarTask();
		//mGravatarTask.setTaskContext(mContext);// to be added later
		Bundle params = new Bundle();
		params.putString("imageUrl", getUrl(email));
		mGravatarTask.setReqListener(reqListener);
		mGravatarTask.execute(params);
	}

	private String formatUrlParameters() {
		List<String> params = new ArrayList<String>();

		if (size != CONSTANTS.DEFAULT_SIZE)
			params.add("s=" + size);
		if (rating != CONSTANTS.DEFAULT_RATING)
			params.add("r=" + rating.getCode());
		if (defaultImage != GravatarDefaultImage.GRAVATAR_ICON)
			params.add("d=" + defaultImage.getCode());

		if (params.isEmpty())
			return "";
		else {
			return "?" + TextUtils.join("&", params.toArray());
		}
	}

}
