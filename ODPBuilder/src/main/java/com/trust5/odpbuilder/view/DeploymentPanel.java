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


	public DeploymentPanel(Deployment pDeployment) {
		super();
		sDeployment = pDeployment;
		setupWidgets(this);
	}

	@Override
	protected void setupWidgets(JPanel panel) {
		JPanel onlineUrlPanel = createLabelAndComponentPanel("Online URL", new JTextField(sDeployment.getOnlineUrl()));
		JPanel refreshAPIUrlPanel = createLabelAndComponentPanel("Refresh API URL",
				new JTextField(sDeployment.getRefreshAPIUrl()));
		JPanel campaignUrlPanel = createLabelAndComponentPanel("Campaign", new JTextField(sDeployment.getCampaign()));
		JPanel userAgentPanel = createLabelAndComponentPanel("User Agent Mod", new JTextField(sDeployment.getUserAgentModifierValue()
		));
		JPanel deploymentIdPanel = createLabelAndComponentPanel("Deployment ID", new JTextField(sDeployment.getDeploymentId()
		));
		JPanel gameIDPanel = createLabelAndComponentPanel("Game ID", new JTextField(sDeployment.getGameId()));
		JPanel marketAppIDPanel = createLabelAndComponentPanel("App ID", new JTextField(sDeployment.getMarketPlaceApplicationId()));
		JPanel embedFilePanel = createLabelAndComponentPanel("Embed File Name", new JTextField(sDeployment.getDefaultEmbedFileName()));
		JPanel embedURLPanel = createLabelAndComponentPanel("Embed URL", new JTextField(sDeployment.getEmbedUrl()));
		JPanel allowDownloadsPanel = createLabelAndComponentPanel("Allow Downloads",
				new JCheckBox("", sDeployment.isAllowDownloads()));
		JPanel refreshPanel = createLabelAndComponentPanel("Refresh",
				new JCheckBox("", sDeployment.isRefresh()));
		JPanel autoRefreshPanel = createLabelAndComponentPanel("Auto Refresh",
				new JCheckBox("", sDeployment.isAutoRefresh()));
		JPanel nonMarketAppsPanel = createLabelAndComponentPanel("Check Market Settings",
				new JCheckBox("", sDeployment.isCheckNonMarketSettings()));
		JPanel useEmbedPanel = createLabelAndComponentPanel("Use Embed",
				new JCheckBox("", sDeployment.isUseEmbed()));
		JPanel bypasLocalesPanel = createLabelAndComponentPanel("Bypass Certain Locales",
				new JCheckBox("", sDeployment.isBypassUnwantedLocales()));
		JPanel traditionalChinesePanel = createLabelAndComponentPanel("Support Traditional Chinese",
				new JCheckBox("", sDeployment.isSupportTraditionalChineseAssets()));
		JPanel wifiRequiredPanel = createLabelAndComponentPanel("Wifi Required for Downloads",
				new JCheckBox("", sDeployment.isWifiRequiredForDownloads()));
		JPanel wifiRecommendedPanel = createLabelAndComponentPanel("Wifi Recommended for Downloads",
				new JCheckBox("", sDeployment.isWifiRecommendedForDownloads()));
		JPanel verifyFileSizePanel = createLabelAndComponentPanel("Verify Download File Size",
				new JCheckBox("", sDeployment.isVerifyFileSize()));
		JPanel useMD5Panel = createLabelAndComponentPanel("Use MD5 File CHeck",
				new JCheckBox("", sDeployment.isUseMD5Check()));
		JPanel useAESPanel = createLabelAndComponentPanel("Use AES Encryption",
				new JCheckBox("", sDeployment.isUseAESEncryption()));
		JPanel appendAllPanel = createLabelAndComponentPanel("Append All Info to HTTP Requests",
				new JCheckBox("", sDeployment.isAppendInfoToAllHttpRequests()));
		JPanel marketPlacePanel = createLabelAndComponentPanel("Is App Marketplace",
				new JCheckBox("", sDeployment.isIsMarketPlace()));
		JPanel useJSBillingPanel = createLabelAndComponentPanel("Use JavaScript Billing",
				new JCheckBox("", sDeployment.isUseJSBilling()));
		JPanel sendNativeToJSPanel = createLabelAndComponentPanel("Send Native Lifecycle Events to JavaScript",
				new JCheckBox("", sDeployment.isSendNativeEventsToJS()));
		JPanel useDownloadManagerPanel = createLabelAndComponentPanel("Use Download Manager",
				new JCheckBox("", sDeployment.isUseDownloadManager()));

		addPanel(onlineUrlPanel);
		addPanel(refreshAPIUrlPanel);
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
		addPanel(bypasLocalesPanel);
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

}
