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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.util.Log;


public final class Utils {
	
	protected static final String TAG = "Utils";

    public static String hex(byte[] array) {
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < array.length; ++i) {
    		sb.append(Integer.toHexString((array[i]& 0xFF) | 0x100).substring(1,3));        
    	}
    	return sb.toString();
    }
    public static String md5Hex (String message) {
    	try {
    		MessageDigest md = MessageDigest.getInstance("MD5");
    		return hex (md.digest(message.getBytes("CP1252")));
      } catch (NoSuchAlgorithmException e) {
      } catch (UnsupportedEncodingException e) {
      }
      return null;
    }
    
    private final static int TIMEOUT = 20000;
    public static String downloadProfile(String profileUrl) throws GenericException {
    	
    	String response = "";
    	
    	HttpParams myParams = new BasicHttpParams();
    	HttpConnectionParams.setConnectionTimeout(myParams, TIMEOUT);
        HttpConnectionParams.setSoTimeout(myParams, TIMEOUT);
        
        DefaultHttpClient httpClient = new DefaultHttpClient(myParams);
        
        HttpGet httpGet = new HttpGet(profileUrl);
        if(CONSTANTS.DEBUG)Log.d(TAG, "WebGetURL: "+profileUrl);
        
        HttpResponse httpResponse = null;
        try {
        	httpResponse = httpClient.execute(httpGet);
        	
        	response = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
        	Log.e(TAG, e.getMessage());
            throw new GenericException(e);
        }
        
        return response;
    }
    
    public static byte[] downloadImage_alternative1(String imageUrl) throws GenericException {
		InputStream stream = null;
		try {
			URL url = new URL(imageUrl);
			stream = url.openStream();
			return IOUtils.toByteArray(stream);
		} catch (FileNotFoundException e) {
			return null;
		} catch (Exception e) {
			throw new GenericException(e);
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}

    
    public static byte[] downloadImage_alternative2(String url) throws GenericException {
        
		byte[] imageData = null;
        try {
        	
        	URLConnection connection=new URL(url).openConnection();
        	
        	InputStream stream = connection.getInputStream();
        	
			//BufferedInputStream in=new BufferedInputStream(stream);//default 8k buffer
			BufferedInputStream in=new BufferedInputStream(stream, 10240);// 10k=10240, 2x8k=16384
			ByteArrayOutputStream out=new ByteArrayOutputStream(10240);
			int read;
			byte[] b=new byte[4096];
			
			while ((read = in.read(b)) != -1) {
					out.write(b, 0, read);
			}
			
			out.flush();
			out.close();
			
			imageData = out.toByteArray();
        }
	    catch (FileNotFoundException e) {
			return null;
		} 
        catch (Exception e) {

        	Log.w(TAG, "Exc="+e);
        	throw new GenericException(e);
        }
        
        return imageData;
    }
    
    // extra Util funcs
    public static Bitmap getBitmapWithReflection(Bitmap originalImage) {
	    
		//The gap we want between the reflection and the original image
        final int reflectionGap = 4;
		
		int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        
        //This will not scale but will flip on the Y axis
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        
        //Create a Bitmap with the flip matrix applied to it.
        //We only want the bottom half of the image
        Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, height/2, width, height/2, matrix, false);
            
        //Create a new bitmap with same width but taller to fit reflection
        Bitmap bitmapWithReflection = Bitmap.createBitmap(width 
          , (height + height/2), Config.ARGB_8888);
      
       //Create a new Canvas with the bitmap that's big enough for
       //the image plus gap plus reflection
       Canvas canvas = new Canvas(bitmapWithReflection);
       //Draw in the original image
       canvas.drawBitmap(originalImage, 0, 0, null);
       //Draw in the gap
       Paint deafaultPaint = new Paint();
       canvas.drawRect(0, height, width, height + reflectionGap, deafaultPaint);
       //Draw in the reflection
       canvas.drawBitmap(reflectionImage,0, height + reflectionGap, null);
       
       //Create a shader that is a linear gradient that covers the reflection
       Paint paint = new Paint(); 
       LinearGradient shader = new LinearGradient(0, originalImage.getHeight(), 0, 
         bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, 
         TileMode.CLAMP); 
       //Set the paint to use this shader (linear gradient)
       paint.setShader(shader); 
       //Set the Transfer mode to be porter duff and destination in
       paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); 
       //Draw a rectangle using the paint with our linear gradient
       canvas.drawRect(0, height, width, 
    		   bitmapWithReflection.getHeight() + reflectionGap, paint); 
	 
	    return bitmapWithReflection;
	}
	
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
	        bitmap.getHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);
	 
	    final int color = 0xff424242;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	    final RectF rectF = new RectF(rect);
	    final float roundPx = 12;
	 
	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
	 
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);
	 
	    return output;
	}
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
    
    
}
