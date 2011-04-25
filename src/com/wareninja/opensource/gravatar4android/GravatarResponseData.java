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

import java.io.Serializable;
import java.util.Arrays;

public class GravatarResponseData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String TAG = "GravatarResponseData";

	private int status = -1;//0:fail, 1:success
	private String errorType = "";// in case of errors
	private String errorMessage = "";// in case of errors
	
	private String imageUrl = null;
	private byte[] imageData = null;
	
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorType
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * @param errorType the errorType to set
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the imageData
	 */
	public byte[] getImageData() {
		return imageData;
	}

	/**
	 * @param imageData the imageData to set
	 */
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
	@Override
	public String toString() {
		return "GravatarResponseData [errorMessage=" + errorMessage
				+ ", errorType=" + errorType + ", imageData="
				+ Arrays.toString(imageData) + ", imageUrl=" + imageUrl
				+ ", status=" + status + "]";
	}	

}