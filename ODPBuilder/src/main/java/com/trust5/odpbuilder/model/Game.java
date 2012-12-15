package com.trust5.odpbuilder.model;

/**
 * @author Ruairi
 * @version 1.0
 * @since 15/12/12 - 01:11
 */
public class Game {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "Game";

	// ===========================================================
	// Fields
	// ===========================================================
	private String mName;
	private String mBannerName;
	private String mType;
	private String mDownloadUrl;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Game() {
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public String getName() {
		return mName;
	}

	public void setName(String pName) {
		mName = pName;
	}

	public String getBannerName() {
		return mBannerName;
	}

	public void setBannerName(String pBannerName) {
		mBannerName = pBannerName;
	}

	public String getType() {
		return mType;
	}

	public void setType(String pType) {
		mType = pType;
	}

	public String getDownloadUrl() {
		return mDownloadUrl;
	}

	public void setDownloadUrl(String pDownloadUrl) {
		mDownloadUrl = pDownloadUrl;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
