package com.trust5.odpbuilder.view;

import com.trust5.odpbuilder.model.Deployment;

import javax.swing.*;

/**
 * @author Ruairi
 * @version 1.0
 * @since 10/12/12 - 10:26
 */
public class DeploymentPanel extends ConfigurationPanel {
	private static Deployment sDeployment;
	JTextField mOnlineUrlTextField;
	JTextField mRefreshAPIUrlTextField;
	JTextField mCampaignTextField;
	JTextField mUserAgentTextField;
	JTextField mDeploymentIdTextField;
	JTextField mGameIDTextField;
	JTextField mMarketAppIDTextField;
	JTextField mEmbedFileTextField;
	JTextField mEmbedURLTextField;
	JTextField mMoreGamesUrlTextField;
	JCheckBox mAllowDownloadsCheckBox;
	JCheckBox mRefreshCheckBox;
	JCheckBox mAutoRefreshCheckBox;
	JCheckBox mNonMarketAppsCheckBox;
	JCheckBox mUseEmbedCheckBox;
	JCheckBox mBypassLocalesCheckBox;
	JCheckBox mTraditionalChineseCheckBox;
	JCheckBox mWifiRequiredCheckBox;
	JCheckBox mWifiRecommendedCheckBox;
	JCheckBox mVerifyFileSizeCheckBox;
	JCheckBox mUseMD5CheckBox;
	JCheckBox mUseAESCheckBox;
	JCheckBox mAppendAllCheckBox;
	JCheckBox mMarketPlaceCheckBox;
	JCheckBox mUseJSBillingCheckBox;
	JCheckBox mSendNativeToJSCheckBox;
	JCheckBox mUseDownloadManagerCheckBox;

	public DeploymentPanel(Deployment pDeployment) {
		super();
		sDeployment = pDeployment;
		setupWidgets(this);
	}

	@Override
	protected void setupWidgets(JPanel panel) {
		mOnlineUrlTextField = new JTextField(sDeployment.getOnlineUrl());
		mRefreshAPIUrlTextField = new JTextField(sDeployment.getRefreshAPIUrl());
		mCampaignTextField = new JTextField(sDeployment.getCampaign());
		mUserAgentTextField = new JTextField(sDeployment.getUserAgentModifierValue());
		mDeploymentIdTextField = new JTextField(String.valueOf(sDeployment.getDeploymentId()));
		mGameIDTextField = new JTextField(String.valueOf(sDeployment.getGameId()));
		mMarketAppIDTextField = new JTextField(String.valueOf(sDeployment.getMarketPlaceApplicationId()));
		mEmbedFileTextField = new JTextField(sDeployment.getDefaultEmbedFileName());
		mEmbedURLTextField = new JTextField(sDeployment.getEmbedUrl());
		mMoreGamesUrlTextField = new JTextField(sDeployment.getMoreGamesUrl());
		mAllowDownloadsCheckBox = new JCheckBox("", sDeployment.isAllowDownloads());
		mRefreshCheckBox = new JCheckBox("", sDeployment.isRefresh());
		mAutoRefreshCheckBox = new JCheckBox("", sDeployment.isAutoRefresh());
		mNonMarketAppsCheckBox = new JCheckBox("", sDeployment.isCheckNonMarketSettings());
		mUseEmbedCheckBox = new JCheckBox("", sDeployment.isUseEmbed());
		mBypassLocalesCheckBox = new JCheckBox("", sDeployment.isBypassUnwantedLocales());
		mTraditionalChineseCheckBox = new JCheckBox("", sDeployment.isSupportTraditionalChineseAssets());
		mWifiRequiredCheckBox = new JCheckBox("", sDeployment.isWifiRequiredForDownloads());
		mWifiRecommendedCheckBox = new JCheckBox("", sDeployment.isWifiRecommendedForDownloads());
		mVerifyFileSizeCheckBox = new JCheckBox("", sDeployment.isVerifyFileSize());
		mUseMD5CheckBox = new JCheckBox("", sDeployment.isUseMD5Check());
		mUseAESCheckBox = new JCheckBox("", sDeployment.isUseAESEncryption());
		mAppendAllCheckBox = new JCheckBox("", sDeployment.isAppendInfoToAllHttpRequests());
		mMarketPlaceCheckBox = new JCheckBox("", sDeployment.isIsMarketPlace());
		mUseJSBillingCheckBox = new JCheckBox("", sDeployment.isUseJSBilling());
		mSendNativeToJSCheckBox = new JCheckBox("", sDeployment.isSendNativeEventsToJS());
		mUseDownloadManagerCheckBox = new JCheckBox("", sDeployment.isUseDownloadManager());

		JPanel onlineUrlPanel = createLabelAndComponentPanel("Online URL", mOnlineUrlTextField);
		JPanel refreshAPIUrlPanel = createLabelAndComponentPanel("Refresh API URL",
				mRefreshAPIUrlTextField);
		JPanel campaignUrlPanel = createLabelAndComponentPanel("Campaign", mCampaignTextField);
		JPanel userAgentPanel = createLabelAndComponentPanel("User Agent Mod", mUserAgentTextField);
		JPanel deploymentIdPanel = createLabelAndComponentPanel("Deployment ID", mDeploymentIdTextField);
		JPanel gameIDPanel = createLabelAndComponentPanel("Game ID", mGameIDTextField);
		JPanel marketAppIDPanel = createLabelAndComponentPanel("App ID", mMarketAppIDTextField);
		JPanel embedFilePanel = createLabelAndComponentPanel("Embed File Name", mEmbedFileTextField);
		JPanel embedURLPanel = createLabelAndComponentPanel("Embed URL", mEmbedURLTextField);
		JPanel moreGamesUrlanel = createLabelAndComponentPanel("More Games URL", mMoreGamesUrlTextField);
		JPanel allowDownloadsPanel = createLabelAndComponentPanel("Allow Downloads", mAllowDownloadsCheckBox);
		JPanel refreshPanel = createLabelAndComponentPanel("Refresh", mRefreshCheckBox);
		JPanel autoRefreshPanel = createLabelAndComponentPanel("Auto Refresh", mAutoRefreshCheckBox);
		JPanel nonMarketAppsPanel = createLabelAndComponentPanel("Check Market Settings", mNonMarketAppsCheckBox);
		JPanel useEmbedPanel = createLabelAndComponentPanel("Use Embed", mUseEmbedCheckBox);
		JPanel bypassLocalesPanel = createLabelAndComponentPanel("Bypass Certain Locales", mBypassLocalesCheckBox);
		JPanel traditionalChinesePanel = createLabelAndComponentPanel("Support Traditional Chinese",
				mTraditionalChineseCheckBox);
		JPanel wifiRequiredPanel = createLabelAndComponentPanel("Wifi Required for Downloads",
				mWifiRequiredCheckBox);
		JPanel wifiRecommendedPanel = createLabelAndComponentPanel("Wifi Recommended for Downloads",
				mWifiRecommendedCheckBox);
		JPanel verifyFileSizePanel = createLabelAndComponentPanel("Verify Download File Size",
				mVerifyFileSizeCheckBox);
		JPanel useMD5Panel = createLabelAndComponentPanel("Use MD5 File CHeck",
				mUseMD5CheckBox);
		JPanel useAESPanel = createLabelAndComponentPanel("Use AES Encryption",
				mUseAESCheckBox);
		JPanel appendAllPanel = createLabelAndComponentPanel("Append All Info to HTTP Requests",
				mAppendAllCheckBox);
		JPanel marketPlacePanel = createLabelAndComponentPanel("Is App Marketplace",
				mMarketPlaceCheckBox);
		JPanel useJSBillingPanel = createLabelAndComponentPanel("Use JavaScript Billing",
				mUseJSBillingCheckBox);
		JPanel sendNativeToJSPanel = createLabelAndComponentPanel("Send Native Lifecycle Events to JavaScript",
				mSendNativeToJSCheckBox);
		JPanel useDownloadManagerPanel = createLabelAndComponentPanel("Use Download Manager",
				mUseDownloadManagerCheckBox);

		addPanel(onlineUrlPanel);
		addPanel(refreshAPIUrlPanel);
		addPanel(moreGamesUrlanel);
		addPanel(campaignUrlPanel);
		addPanel(userAgentPanel);
		addPanel(deploymentIdPanel);
		addPanel(gameIDPanel);
		addPanel(marketAppIDPanel);
		addPanel(embedFilePanel);
		addPanel(embedURLPanel);
		addPanel(allowDownloadsPanel);
		addPanel(refreshPanel);
		addPanel(autoRefreshPanel);
		addPanel(nonMarketAppsPanel);
		addPanel(useEmbedPanel);
		addPanel(bypassLocalesPanel);
		addPanel(traditionalChinesePanel);
		addPanel(wifiRequiredPanel);
		addPanel(wifiRecommendedPanel);
		addPanel(verifyFileSizePanel);
		addPanel(useMD5Panel);
		addPanel(useAESPanel);
		addPanel(appendAllPanel);
		addPanel(marketPlacePanel);
		addPanel(useJSBillingPanel);
		addPanel(sendNativeToJSPanel);
		addPanel(useDownloadManagerPanel);
	}

	@Override
	public void loadInputs() {
		sDeployment.setAllowDownloads(mAllowDownloadsCheckBox.isSelected());
		sDeployment.setMoreGamesUrl(mMoreGamesUrlTextField.getText());
		sDeployment.setEmbedUrl(mEmbedURLTextField.getText());
		sDeployment.setDefaultEmbedFileName(mEmbedFileTextField.getText());
		sDeployment.setAppendInfoToAllHttpRequests(mAppendAllCheckBox.isSelected());
		sDeployment.setAutoRefresh(mAutoRefreshCheckBox.isSelected());
		sDeployment.setBypassUnwantedLocales(mBypassLocalesCheckBox.isSelected());
		sDeployment.setCampaign(mCampaignTextField.getText());
		sDeployment.setCheckNonMarketSettings(mNonMarketAppsCheckBox.isSelected());
		sDeployment.setDeploymentId(Integer.decode(mDeploymentIdTextField.getText()));
		sDeployment.setEmbedInstaller();
		sDeployment.setGameId();
		sDeployment.setIsMarketPlace();
	}
}
