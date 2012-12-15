package com.trust5.odpbuilder.model;

/**
 * @author Ruairi
 * @version 1.0
 * @since 15/12/12 - 01:11
 */
public class UIConfiguration {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "UIConfiguration";

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean mShowTopBar;
	private boolean mShowAppInFullScreen;
	private boolean mAllowPageScrolling;
	private boolean mShowDownloadedItemsMenu;
	private boolean mShowSplashScreen;
	private int mSplashScreenDisplayTime;

	// ===========================================================
	// Constructors
	// ===========================================================
	public UIConfiguration() {
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public boolean isShowTopBar() {
		return mShowTopBar;
	}

	public void setShowTopBar(boolean pShowTopBar) {
		mShowTopBar = pShowTopBar;
	}

	public boolean isShowAppInFullScreen() {
		return mShowAppInFullScreen;
	}

	public void setShowAppInFullScreen(boolean pShowAppInFullScreen) {
		mShowAppInFullScreen = pShowAppInFullScreen;
	}

	public boolean isAllowPageScrolling() {
		return mAllowPageScrolling;
	}

	public void setAllowPageScrolling(boolean pAllowPageScrolling) {
		mAllowPageScrolling = pAllowPageScrolling;
	}

	public boolean isShowDownloadedItemsMenu() {
		return mShowDownloadedItemsMenu;
	}

	public void setShowDownloadedItemsMenu(boolean pShowDownloadedItemsMenu) {
		mShowDownloadedItemsMenu = pShowDownloadedItemsMenu;
	}

	public boolean isShowSplashScreen() {
		return mShowSplashScreen;
	}

	public void setShowSplashScreen(boolean pShowSplashScreen) {
		mShowSplashScreen = pShowSplashScreen;
	}

	public int getSplashScreenDisplayTime() {
		return mSplashScreenDisplayTime;
	}

	public void setSplashScreenDisplayTime(int pSplashScreenDisplayTime) {
		mSplashScreenDisplayTime = pSplashScreenDisplayTime;
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
