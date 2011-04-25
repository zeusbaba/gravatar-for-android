package com.wareninja.opensource.gravatar4android;

public enum GravatarDefaultImage {

	GRAVATAR_ICON(""),

	IDENTICON("identicon"),

	MONSTERID("monsterid"),

	WAVATAR("wavatar"),
	
	RETRO("retro"),
	MM("mm"),

	HTTP_404("404");

	private String code;

	private GravatarDefaultImage(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
