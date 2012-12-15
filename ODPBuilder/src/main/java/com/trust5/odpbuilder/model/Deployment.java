package com.trust5.odpbuilder.model;

/**
 * @author Ruairi
 * @version 1.0
 * @since 15/12/12 - 01:11
 */
public class Deployment {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "Deployment";

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean mEmbedInstaller;
	private boolean mAllowDownloads;
	private boolean mRefresh;
	private boolean mAutoRefresh;
	private boolean mCheckNonMarketSettings;
	private boolean mUseEmbed;
	private boolean mBypassUnwantedLocales;
	private boolean mSupportTraditionalChineseAssets;
	private boolean mIsMarketPlace;
	private boolean mWifiRequiredForDownloads;
	private boolean mWifiRecommendedForDownloads;
	private boolean mUseMD5Check;
	private boolean mVerifyFileSize;
	private boolean mUseAESEncryption;
	private boolean mUseJSBilling;
	private boolean mSendNativeEventsToJS;
	private boolean mUseDownloadManager;
	private boolean mAllowDownloadCancel;
	private boolean mAppendInfoToAllHttpRequests;

	private String mOnlineUrl;
	private String mRefreshAPIUrl;
	private String mCampaign;
	private String mMoreGamesUrl;
	private String mUserAgentModifierValue;
	private String mDefaultEmbedFileName;
	private String mEmbedUrl;

	private int mDeploymentId;
	private int mGameId;
	private int mMarketPlaceApplicationId;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Deployment() {
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public boolean isEmbedInstaller() {
		return mEmbedInstaller;
	}

	public void setEmbedInstaller(boolean pEmbedInstaller) {
		mEmbedInstaller = pEmbedInstaller;
	}

	public boolean isAllowDownloads() {
		return mAllowDownloads;
	}

	public void setAllowDownloads(boolean pAllowDownloads) {
		mAllowDownloads = pAllowDownloads;
	}

	public boolean isRefresh() {
		return mRefresh;
	}

	public void setRefresh(boolean pRefresh) {
		mRefresh = pRefresh;
	}

	public boolean isAutoRefresh() {
		return mAutoRefresh;
	}

	public void setAutoRefresh(boolean pAutoRefresh) {
		mAutoRefresh = pAutoRefresh;
	}

	public boolean isCheckNonMarketSettings() {
		return mCheckNonMarketSettings;
	}

	public void setCheckNonMarketSettings(boolean pCheckNonMarketSettings) {
		mCheckNonMarketSettings = pCheckNonMarketSettings;
	}

	public boolean isUseEmbed() {
		return mUseEmbed;
	}

	public void setUseEmbed(boolean pUseEmbed) {
		mUseEmbed = pUseEmbed;
	}

	public boolean isBypassUnwantedLocales() {
		return mBypassUnwantedLocales;
	}

	public void setBypassUnwantedLocales(boolean pBypassUnwantedLocales) {
		mBypassUnwantedLocales = pBypassUnwantedLocales;
	}

	public boolean isSupportTraditionalChineseAssets() {
		return mSupportTraditionalChineseAssets;
	}

	public void setSupportTraditionalChineseAssets(boolean pSupportTraditionalChineseAssets) {
		mSupportTraditionalChineseAssets = pSupportTraditionalChineseAssets;
	}

	public boolean isIsMarketPlace() {
		return mIsMarketPlace;
	}

	public void setIsMarketPlace(boolean pIsMarketPlace) {
		mIsMarketPlace = pIsMarketPlace;
	}

	public boolean isWifiRequiredForDownloads() {
		return mWifiRequiredForDownloads;
	}

	public void setWifiRequiredForDownloads(boolean pWifiRequiredForDownloads) {
		mWifiRequiredForDownloads = pWifiRequiredForDownloads;
	}

	public boolean isWifiRecommendedForDownloads() {
		return mWifiRecommendedForDownloads;
	}

	public void setWifiRecommendedForDownloads(boolean pWifiRecommendedForDownloads) {
		mWifiRecommendedForDownloads = pWifiRecommendedForDownloads;
	}

	public boolean isUseMD5Check() {
		return mUseMD5Check;
	}

	public void setUseMD5Check(boolean pUseMD5Check) {
		mUseMD5Check = pUseMD5Check;
	}

	public boolean isVerifyFileSize() {
		return mVerifyFileSize;
	}

	public void setVerifyFileSize(boolean pVerifyFileSize) {
		mVerifyFileSize = pVerifyFileSize;
	}

	public boolean isUseAESEncryption() {
		return mUseAESEncryption;
	}

	public void setUseAESEncryption(boolean pUseAESEncryption) {
		mUseAESEncryption = pUseAESEncryption;
	}

	public boolean isUseJSBilling() {
		return mUseJSBilling;
	}

	public void setUseJSBilling(boolean pUseJSBilling) {
		mUseJSBilling = pUseJSBilling;
	}

	public boolean isSendNativeEventsToJS() {
		return mSendNativeEventsToJS;
	}

	public void setSendNativeEventsToJS(boolean pSendNativeEventsToJS) {
		mSendNativeEventsToJS = pSendNativeEventsToJS;
	}

	public boolean isUseDownloadManager() {
		return mUseDownloadManager;
	}

	public void setUseDownloadManager(boolean pUseDownloadManager) {
		mUseDownloadManager = pUseDownloadManager;
	}

	public boolean isAllowDownloadCancel() {
		return mAllowDownloadCancel;
	}

	public void setAllowDownloadCancel(boolean pAllowDownloadCancel) {
		mAllowDownloadCancel = pAllowDownloadCancel;
	}

	public boolean isAppendInfoToAllHttpRequests() {
		return mAppendInfoToAllHttpRequests;
	}

	public void setAppendInfoToAllHttpRequests(boolean pAppendInfoToAllHttpRequests) {
		mAppendInfoToAllHttpRequests = pAppendInfoToAllHttpRequests;
	}

	public String getOnlineUrl() {
		return mOnlineUrl;
	}

	public void setOnlineUrl(String pOnlineUrl) {
		mOnlineUrl = pOnlineUrl;
	}

	public String getRefreshAPIUrl() {
		return mRefreshAPIUrl;
	}

	public void setRefreshAPIUrl(String pRefreshAPIUrl) {
		mRefreshAPIUrl = pRefreshAPIUrl;
	}

	public String getCampaign() {
		return mCampaign;
	}

	public void setCampaign(String pCampaign) {
		mCampaign = pCampaign;
	}

	public String getMoreGamesUrl() {
		return mMoreGamesUrl;
	}

	public void setMoreGamesUrl(String pMoreGamesUrl) {
		mMoreGamesUrl = pMoreGamesUrl;
	}

	public String getUserAgentModifierValue() {
		return mUserAgentModifierValue;
	}

	public void setUserAgentModifierValue(String pUserAgentModifierValue) {
		mUserAgentModifierValue = pUserAgentModifierValue;
	}

	public String getDefaultEmbedFileName() {
		return mDefaultEmbedFileName;
	}

	public void setDefaultEmbedFileName(String pDefaultEmbedFileName) {
		mDefaultEmbedFileName = pDefaultEmbedFileName;
	}

	public String getEmbedUrl() {
		return mEmbedUrl;
	}

	public void setEmbedUrl(String pEmbedUrl) {
		mEmbedUrl = pEmbedUrl;
	}

	public int getDeploymentId() {
		return mDeploymentId;
	}

	public void setDeploymentId(int pDeploymentId) {
		mDeploymentId = pDeploymentId;
	}

	public int getGameId() {
		return mGameId;
	}

	public void setGameId(int pGameId) {
		mGameId = pGameId;
	}

	public int getMarketPlaceApplicationId() {
		return mMarketPlaceApplicationId;
	}

	public void setMarketPlaceApplicationId(int pMarketPlaceApplicationId) {
		mMarketPlaceApplicationId = pMarketPlaceApplicationId;
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
