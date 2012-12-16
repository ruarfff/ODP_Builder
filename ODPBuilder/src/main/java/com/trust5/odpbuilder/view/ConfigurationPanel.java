package com.trust5.odpbuilder.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Ruairi
 * @version 1.0
 * @since 16/12/12 - 00:17
 */
public abstract class ConfigurationPanel extends JPanel {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "ConfigurationPanel";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public ConfigurationPanel() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected abstract void setupWidgets(JPanel pPanel);

	protected static void addLabelAndCheckBox(int currentYIndex, JPanel pParent, JCheckBox pCheckBox,
											  String pLabelText) {
		addLabelAndCheckBox(currentYIndex, pParent, pCheckBox, pLabelText, true);
	}

	protected static void addLabelAndCheckBox(int currentYIndex, JPanel pParent, JCheckBox pCheckBox,
											  String pLabelText,
											  boolean pVisible) {
		addLabel(pParent, pLabelText, currentYIndex, pVisible);
		pCheckBox.setVisible(pVisible);
		GridBagConstraints checkBoxConstraints = new GridBagConstraints();
		checkBoxConstraints.anchor = GridBagConstraints.CENTER;
		checkBoxConstraints.gridx = 1;
		checkBoxConstraints.gridy = currentYIndex;
		checkBoxConstraints.fill = GridBagConstraints.NONE;
		checkBoxConstraints.weightx = 1.0;
		checkBoxConstraints.weighty = 1.0;
		checkBoxConstraints.ipadx = 40;

		pParent.add(pCheckBox, checkBoxConstraints);
	}

	protected static void addLabel(JPanel pParent, String pLabelText, int pCurrentYIndex, boolean pVisible) {
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

	protected static void addLabelAndTextField(int currentYIndex, JPanel pParent, JTextField pTextField, String pLabelText) {
		addLabelAndTextField(currentYIndex, pParent, pTextField, pLabelText, true);
	}

	protected static void addLabelAndTextField(int currentYIndex, JPanel pParent, JTextField pTextField,
											   String pLabelText,
											   boolean pVisible) {
		addLabel(pParent, pLabelText, currentYIndex, pVisible);

		GridBagConstraints textFieldConstraints = new GridBagConstraints();
		textFieldConstraints.anchor = GridBagConstraints.CENTER;
		textFieldConstraints.gridx = 1;
		textFieldConstraints.gridy = currentYIndex;
		textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
		textFieldConstraints.weightx = 1.0;
		textFieldConstraints.weighty = 1.0;
		textFieldConstraints.ipadx = 40;

		pTextField.setVisible(pVisible);
		pParent.add(pTextField, textFieldConstraints);
	}

	protected JPanel createLabelAndComponentPanel(String pLabelText, JTextField pTextField) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(pLabelText));
		panel.add(pTextField);
		return panel;
	}

	protected JPanel createLabelAndComponentPanel(String pLabelText, JCheckBox pCheckBox) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(pLabelText));
		panel.add(pCheckBox);
		return panel;
	}

	protected void addPanel(JPanel pPanel) {
		this.add(pPanel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void refresh() {
		removeAll();
		setupWidgets(this);
		validate();
		repaint();
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
