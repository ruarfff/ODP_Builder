package com.trust5.odpbuilder.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ruairi
 * @version 1.0
 * @since 10/12/12 - 10:26
 */
public class DeploymentPanel extends JPanel {
	private static int currentYIndex;

	private static JTextField mOnlineURLTextField;
	private static JTextField mRefreshAPIURLTextField;
	private static JTextField mCampaignTextField;
	private static JTextField mUserAgentModifierTextField;

	private static JTextField mDeploymentIdTextField;
	private static JTextField mGameIdTextField;
	private static JTextField mMarketAppIdTextField;

	private static JCheckBox mShowTopBarCheckBox;
	private static JCheckBox mShowAppInFullScreenCheckBox;
	private static JCheckBox mShowSplashScreenCheckBox;
	private static JTextField mSplashScreenDisplayTimeTextField;
	private static JCheckBox mShowDownloadedItemsMenuCheckBox;

	private static JCheckBox mAllowDownloadsCheckBox;
	private static JCheckBox mAutoRefreshCheckBox;
	private static JCheckBox mRefreshCheckBox;
	private static JCheckBox mCheckNonMarketAppSettingsCheckBox;

	private static JCheckBox mUseEmbedCheckBox;
	private static JTextField mDefaultEmbedFileNameTextField;
	private static JTextField mEmbedURLTextField;

	private static JCheckBox mBypassUnwantedLocalesCheckBox;
	private static JCheckBox mSupportTraditionalChineseCheckBox;
	private static JCheckBox mWifiRequiredForDownloadsCheckBox;
	private static JCheckBox mWifiRecommendedForDownloadsCheckBox;
	private static JCheckBox mUseMD5CheckBox;
	private static JCheckBox mVerifyFileSizeCheckBox;
	private static JCheckBox mUseAESEncryptionCheckBox;
	private static JCheckBox mAppendAllInfoToHttpRequestsCheckBox;

	private static JCheckBox mIsMarketPlaceCheckBox;
	private static JCheckBox mUseJSBillingCheckBox;
	private static JCheckBox mSendNativeEventsToJSCheckBox;

	private static JCheckBox mUseDownloadManagerCheckBox;


	public DeploymentPanel() {
		super(new GridBagLayout());
		setupWidgets(this);
	}

	private void setupWidgets(JPanel panel) {
		addOnlineBuyUrlInput(panel);
		addRefreshAPIURLInput(panel);
		addCampaignInput(panel);
		addUserAgentModifierInput(panel);
		addDeploymentIdInput(panel);
		addGameIdInput(panel);
		addMarketAppIdUrlInput(panel);
		addShowTopBarCheckBox(panel);
		addShowAppInFullScreenCheckBox(panel);
		addShowSplashScreenCheckBox(panel);
		addSplashScreenDisplayTimeTextField(panel);
		addShowDownloadedItemsMenuCheckBox(panel);
		addAllowDownloadsCheckBox(panel);
		addRefreshCheckBox(panel);
		addAutoRefreshCheckBox(panel);
		addCheckNonMarketAppSettingsCheckBox(panel);
		addUseEmbedCheckBox(panel);
		addEmbedFileNameInput(panel);
		addEmbedURLInput(panel);
		addBypassUnwantedLocalesCheckBox(panel);
		addSupportTraditionalChineseCheckBox(panel);
		addWifiRequiredForDownloadsCheckBox(panel);
		addWifiRecommendedForDownloadsCheckBox(panel);
		addUseMD5CheckBox(panel);
		addVerifyFileSizeCheckBox(panel);
		addUseAESEncryptionCheckBox(panel);
		addAppendAllInfoToHttpRequestsCheckBox(panel);
		addIsMarketPlaceCheckBox(panel);
		addUseJSBillingCheckBox(panel);
		addSendNativeEventsToJSCheckBox(panel);
		addUseDownloadManagerCheckBox(panel);
	}

	private void addOnlineBuyUrlInput(JPanel panel) {
		mOnlineURLTextField = new JTextField();
		addLabelAndTextField(panel, mOnlineURLTextField, "Online URL");
	}

	private void addRefreshAPIURLInput(JPanel panel) {
		mRefreshAPIURLTextField = new JTextField();
		addLabelAndTextField(panel, mRefreshAPIURLTextField, "Refresh API URL");
	}

	private void addCampaignInput(JPanel panel) {
		mCampaignTextField = new JTextField();
		addLabelAndTextField(panel, mCampaignTextField, "Campaign");
	}

	private void addUserAgentModifierInput(JPanel panel) {
		mUserAgentModifierTextField = new JTextField();
		addLabelAndTextField(panel, mUserAgentModifierTextField, "UA Modifier");
	}

	private void addDeploymentIdInput(JPanel panel) {
		mDeploymentIdTextField = new JTextField();
		addLabelAndTextField(panel, mDeploymentIdTextField, "Deployment ID");
	}

	private void addGameIdInput(JPanel panel) {
		mGameIdTextField = new JTextField();
		addLabelAndTextField(panel, mGameIdTextField, "Game ID");
	}

	private void addMarketAppIdUrlInput(JPanel panel) {
		mMarketAppIdTextField = new JTextField();
		addLabelAndTextField(panel, mMarketAppIdTextField, "Market App ID");
	}

	private void addShowTopBarCheckBox(JPanel panel) {
		mShowTopBarCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mShowTopBarCheckBox, "Show Top Bar");
	}

	private void addShowAppInFullScreenCheckBox(JPanel panel) {
		mShowAppInFullScreenCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mShowAppInFullScreenCheckBox, "Full Screen");
	}

	private void addShowSplashScreenCheckBox(JPanel panel) {
		mShowSplashScreenCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mShowSplashScreenCheckBox, "Show Splash");
	}

	private void addSplashScreenDisplayTimeTextField(JPanel panel) {
		mSplashScreenDisplayTimeTextField = new JTextField();
		addLabelAndTextField(panel, mSplashScreenDisplayTimeTextField, "Splash Screen Display Time", false);
	}

	private void addShowDownloadedItemsMenuCheckBox(JPanel panel) {
		mShowDownloadedItemsMenuCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mShowDownloadedItemsMenuCheckBox, "Show Downloads Menu");
	}

	private void addAllowDownloadsCheckBox(JPanel panel) {
		mAllowDownloadsCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mAllowDownloadsCheckBox, "Allow Downloads");
	}

	private void addRefreshCheckBox(JPanel panel) {
		mRefreshCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mRefreshCheckBox, "Refresh");
	}

	private void addAutoRefreshCheckBox(JPanel panel) {
		mAutoRefreshCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mAutoRefreshCheckBox, "Auto Refresh", false);
	}

	private void addCheckNonMarketAppSettingsCheckBox(JPanel panel) {
		mCheckNonMarketAppSettingsCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mCheckNonMarketAppSettingsCheckBox, "Non Market Alert");
	}

	private void addUseEmbedCheckBox(JPanel panel) {
		mUseEmbedCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mUseEmbedCheckBox, "Use Embed");
	}

	private void addEmbedFileNameInput(JPanel panel) {
		mDefaultEmbedFileNameTextField = new JTextField();
		addLabelAndTextField(panel, mDefaultEmbedFileNameTextField, "Embed File Name", false);
	}

	private void addEmbedURLInput(JPanel panel) {
		mEmbedURLTextField = new JTextField();
		addLabelAndTextField(panel, mEmbedURLTextField, "Embed URL", false);
	}

	private void addBypassUnwantedLocalesCheckBox(JPanel panel) {
		mBypassUnwantedLocalesCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mBypassUnwantedLocalesCheckBox, "Bypass Locales");
	}

	private void addSupportTraditionalChineseCheckBox(JPanel panel) {
		mSupportTraditionalChineseCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mSupportTraditionalChineseCheckBox, "Support Trad Chinese");
	}

	private void addWifiRequiredForDownloadsCheckBox(JPanel panel) {
		mWifiRequiredForDownloadsCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mWifiRequiredForDownloadsCheckBox, "Wifi Required");
	}

	private void addWifiRecommendedForDownloadsCheckBox(JPanel panel) {
		mWifiRecommendedForDownloadsCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mWifiRecommendedForDownloadsCheckBox, "Wifi Recommended");
	}

	private void addUseMD5CheckBox(JPanel panel) {
		mUseMD5CheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mUseMD5CheckBox, "Use MD5");
	}

	private void addVerifyFileSizeCheckBox(JPanel panel) {
		mVerifyFileSizeCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mVerifyFileSizeCheckBox, "Verify File Size");
	}

	private void addUseAESEncryptionCheckBox(JPanel panel) {
		mUseAESEncryptionCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mUseAESEncryptionCheckBox, "Use AES");
	}

	private void addAppendAllInfoToHttpRequestsCheckBox(JPanel panel) {
		mAppendAllInfoToHttpRequestsCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mAppendAllInfoToHttpRequestsCheckBox, "Append All Info to Request");
	}

	private void addIsMarketPlaceCheckBox(JPanel panel) {
		mIsMarketPlaceCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mIsMarketPlaceCheckBox, "Is MarketPlace");
	}

	private void addUseJSBillingCheckBox(JPanel panel) {
		mUseJSBillingCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mUseJSBillingCheckBox, "Use JS Billing", false);
	}

	private void addSendNativeEventsToJSCheckBox(JPanel panel) {
		mSendNativeEventsToJSCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mSendNativeEventsToJSCheckBox, "Native JS Events", false);
	}

	private void addUseDownloadManagerCheckBox(JPanel panel) {
		mUseDownloadManagerCheckBox = new JCheckBox("", false);
		addLabelAndCheckBox(panel, mUseDownloadManagerCheckBox, "Use Download Manager");
	}

	private static void addLabelAndTextField(JPanel pParent, JTextField pTextField, String pLabelText) {
		addLabelAndTextField(pParent, pTextField, pLabelText, true);
	}

	private static void addLabelAndTextField(JPanel pParent, JTextField pTextField, String pLabelText, boolean pVisible) {
		addLabel(pParent, pLabelText, currentYIndex, pVisible);

		GridBagConstraints textFieldConstraints = new GridBagConstraints();
		textFieldConstraints.anchor = GridBagConstraints.CENTER;
		textFieldConstraints.gridx = 1;
		textFieldConstraints.gridy = currentYIndex++;
		textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
		textFieldConstraints.weightx = 1.0;
		textFieldConstraints.weighty = 1.0;
		textFieldConstraints.ipadx = 40;

		pTextField.setVisible(pVisible);
		pParent.add(pTextField, textFieldConstraints);
	}

	private static void addLabelAndCheckBox(JPanel pParent, JCheckBox pCheckBox, String pLabelText) {
		addLabelAndCheckBox(pParent, pCheckBox, pLabelText, true);
	}

	private static void addLabelAndCheckBox(JPanel pParent, JCheckBox pCheckBox, String pLabelText, boolean pVisible) {
		addLabel(pParent, pLabelText, currentYIndex, pVisible);
		pCheckBox.setVisible(pVisible);
		GridBagConstraints checkBoxConstraints = new GridBagConstraints();
		checkBoxConstraints.anchor = GridBagConstraints.CENTER;
		checkBoxConstraints.gridx = 1;
		checkBoxConstraints.gridy = currentYIndex++;
		checkBoxConstraints.fill = GridBagConstraints.NONE;
		checkBoxConstraints.weightx = 1.0;
		checkBoxConstraints.weighty = 1.0;
		checkBoxConstraints.ipadx = 40;

		pParent.add(pCheckBox, checkBoxConstraints);
	}

	private static void addLabel(JPanel pParent, String pLabelText, int pCurrentYIndex, boolean pVisible) {
		JLabel label = new JLabel(pLabelText);
		label.setVisible(pVisible);
		GridBagConstraints labelConstraints = new GridBagConstraints();
		labelConstraints.anchor = GridBagConstraints.CENTER;
		labelConstraints.gridx = 0;
		labelConstraints.gridy = pCurrentYIndex;
		labelConstraints.fill = GridBagConstraints.HORIZONTAL;
		labelConstraints.weightx = 1.0;
		labelConstraints.weighty = 0.5;

		pParent.add(label, labelConstraints);
	}

}
