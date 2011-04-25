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

package com.wareninja.opensource.gravatar4android.common;

import com.wareninja.opensource.gravatar4android.GravatarDefaultImage;
import com.wareninja.opensource.gravatar4android.GravatarRating;

public class CONSTANTS {

	public static final boolean DEBUG = true;// enable/disable debug logging
    
	public final static int DEFAULT_SIZE = 80;
	public final static String API_GRAVATAR_BASE_URL = "http://www.gravatar.com";
	public final static String API_GRAVATAR_BASE_URL_SSL = "https://secure.gravatar.com";
	public final static String API_GRAVATAR_AVATAR = "/avatar/";
	public static final GravatarRating DEFAULT_RATING = GravatarRating.GENERAL_AUDIENCES;
	public static final GravatarDefaultImage DEFAULT_DEFAULT_IMAGE = GravatarDefaultImage.HTTP_404;
	
	public final static int TASK_ACTION_IMAGE = 1;
	public final static int TASK_ACTION_PROFILE = 2;
}