package com.trust5.odpbuilder.view;

import com.trust5.odpbuilder.model.UIConfiguration;

import javax.swing.*;

/**
 * @author Ruairi
 * @version 1.0
 * @since 15/12/12 - 00:06
 */
public class UIConfigPanel extends ConfigurationPanel {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "UIConfigPanel";

	// ===========================================================
	// Fields
	// ===========================================================

	private static UIConfiguration sUIConfiguration;

	JCheckBox mShowTopBarCheckBox;
	JCheckBox mShowAppInFullScreenCheckBox;
	JCheckBox mAllowPageScrollingCheckBox;
	JCheckBox mShowSplashScreenCheckBox;
	JTextField mSplashScreenDisplayTimeTextField;
	JCheckBox mShowDownloadedItemsMenuCheckBox;

	// ===========================================================
	// Constructors
	// ===========================================================
	public UIConfigPanel(UIConfiguration pUIConfiguration) {
		super();
		sUIConfiguration = pUIConfiguration;
		setupWidgets(this);
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void setupWidgets(JPanel panel) {
		mShowTopBarCheckBox = new JCheckBox("",
				sUIConfiguration.isShowTopBar());
		mShowAppInFullScreenCheckBox = new JCheckBox("",
				sUIConfiguration.isShowAppInFullScreen());
		mAllowPageScrollingCheckBox = new JCheckBox("",
				sUIConfiguration.isAllowPageScrolling());
		mShowSplashScreenCheckBox = new JCheckBox("",
				sUIConfiguration.isShowSplashScreen());
		mSplashScreenDisplayTimeTextField = new JTextField(String.valueOf(sUIConfiguration.getSplashScreenDisplayTime()));
		mShowDownloadedItemsMenuCheckBox = new JCheckBox("", sUIConfiguration.isShowDownloadedItemsMenu());

		JPanel showTopBarPanel = createLabelAndComponentPanel("Show top bar", mShowTopBarCheckBox);
		JPanel showAppInFullScreenPanel = createLabelAndComponentPanel("Show App in Full Screen", mShowAppInFullScreenCheckBox);
		JPanel allowPageScrollingPanel = createLabelAndComponentPanel("Allow Page Scrolling",
				mAllowPageScrollingCheckBox);
		JPanel showSplashScreenPanel = createLabelAndComponentPanel("Show Splash Screen", mShowSplashScreenCheckBox);
		JPanel splashScreenDisplayTimePanel = createLabelAndComponentPanel("Splash Screen Display Time",
				mSplashScreenDisplayTimeTextField);
		JPanel showDownloadedItemsMenuPanel = createLabelAndComponentPanel("Show Downloaded Items Menu",
				mShowDownloadedItemsMenuCheckBox);

		add(showTopBarPanel);
		add(showAppInFullScreenPanel);
		add(allowPageScrollingPanel);
		add(showSplashScreenPanel);
		add(splashScreenDisplayTimePanel);
		add(showDownloadedItemsMenuPanel);
	}

	@Override
	public void loadInputs() {
		try {
			sUIConfiguration.setShowTopBar(mShowTopBarCheckBox.isSelected());
			sUIConfiguration.setAllowPageScrolling(mAllowPageScrollingCheckBox.isSelected());
			sUIConfiguration.setShowAppInFullScreen(mShowAppInFullScreenCheckBox.isSelected());
			sUIConfiguration.setShowSplashScreen(mShowSplashScreenCheckBox.isSelected());
			sUIConfiguration.setShowDownloadedItemsMenu(mShowDownloadedItemsMenuCheckBox.isSelected());
			sUIConfiguration.setSplashScreenDisplayTime(mSplashScreenDisplayTimeTextField.getText() == null ? 0 : Integer
					.decode(mSplashScreenDisplayTimeTextField.getText()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
