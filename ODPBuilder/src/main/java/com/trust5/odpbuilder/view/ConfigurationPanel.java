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

	public abstract void loadInputs();

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
