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
	private static int currentYIndex;

	private static UIConfiguration sUIConfiguration;

	private static JCheckBox mShowTopBarCheckBox;
	private static JCheckBox mShowAppInFullScreenCheckBox;
	private static JCheckBox mShowSplashScreenCheckBox;
	private static JTextField mSplashScreenDisplayTimeTextField;
	private static JCheckBox mShowDownloadedItemsMenuCheckBox;

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
		/*addShowTopBarCheckBox(panel);
		addShowAppInFullScreenCheckBox(panel);
		addShowSplashScreenCheckBox(panel);
		addSplashScreenDisplayTimeTextField(panel);
		addShowDownloadedItemsMenuCheckBox(panel);*/
	}

	// ===========================================================
	// Methods
	// ===========================================================
	private void addShowTopBarCheckBox(JPanel panel) {
		mShowTopBarCheckBox = new JCheckBox("", false);
		mShowTopBarCheckBox.setEnabled(sUIConfiguration.isShowTopBar());
		addLabelAndCheckBox(currentYIndex++, panel, mShowTopBarCheckBox, "Show Top Bar");
	}

	private void addShowAppInFullScreenCheckBox(JPanel panel) {
		mShowAppInFullScreenCheckBox = new JCheckBox("", sUIConfiguration.isShowAppInFullScreen());
		addLabelAndCheckBox(currentYIndex++, panel, mShowAppInFullScreenCheckBox, "Full Screen");
	}

	private void addShowSplashScreenCheckBox(JPanel panel) {
		mShowSplashScreenCheckBox = new JCheckBox("", sUIConfiguration.isShowSplashScreen());
		addLabelAndCheckBox(currentYIndex++, panel, mShowSplashScreenCheckBox, "Show Splash");
	}

	private void addSplashScreenDisplayTimeTextField(JPanel panel) {
		mSplashScreenDisplayTimeTextField = new JTextField();
		mSplashScreenDisplayTimeTextField.setText(String.valueOf(sUIConfiguration.getSplashScreenDisplayTime()));
		addLabelAndTextField(currentYIndex++, panel, mSplashScreenDisplayTimeTextField, "Splash Screen Display Time", false);
	}

	private void addShowDownloadedItemsMenuCheckBox(JPanel panel) {
		mShowDownloadedItemsMenuCheckBox = new JCheckBox("", sUIConfiguration.isShowDownloadedItemsMenu());
		addLabelAndCheckBox(currentYIndex++, panel, mShowDownloadedItemsMenuCheckBox, "Show Downloads Menu");
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
