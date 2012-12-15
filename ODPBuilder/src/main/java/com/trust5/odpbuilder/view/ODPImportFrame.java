package com.trust5.odpbuilder.view;

import com.trust5.odpbuilder.Constants;
import com.trust5.odpbuilder.model.Workspace;
import com.trust5.odpbuilder.utility.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Ruairi
 * @version 1.0
 * @since 13/12/12 - 22:18
 */
public class ODPImportFrame extends JFrame {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "ODPImportFrame";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public ODPImportFrame() {
		super("Import ODP APK");

		setupWidgets();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(width / 3, height / 8);
		// center the jframe on screen
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	private void setupWidgets() {
		final JPanel panel = new JPanel(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();

		final JTextField odpPathField = new JTextField();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		panel.add(odpPathField, gbc);


		final JButton browseForODPButton = new JButton("Browse");
		browseForODPButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				final JFileChooser fc = new JFileChooser(SystemUtils.USER_HOME);
				fc.setDialogTitle("Select ODP APK File");
				fc.setFileFilter(new APKFilter());
				int returnVal = fc.showOpenDialog(ODPImportFrame.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					if (file.getName().endsWith(".apk")) {
						odpPathField.setText(file.getPath());
					}
					else {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(ODPImportFrame.this,
								"Please verify your selection is an apk file ", "Could not import ODP. ",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			}
		});
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		panel.add(browseForODPButton, gbc);

		final JButton importButton = new JButton("Import");
		importButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File ODP = new File(odpPathField.getText());
					if (ODP.exists() && ODP.getName().endsWith(".apk")) {
						File destination = new File(Workspace.getWorkspaceConfigDirLocation() + File.separator +
								Constants.ODP_FILE_NAME);
						FileUtils.copy(ODP, destination);

						dispose();
						new WorkspaceFrame();
					}
					else {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(ODPImportFrame.this,
								"Please verify your selection is an apk file ", "Could not import ODP. ",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		panel.add(importButton, gbc);

		add(panel);
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	private class APKFilter extends FileFilter {
		/**
		 * Whether the given file is accepted by this filter.
		 */
		@Override
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return true;
			}
			else if (f.getName().substring(f.getName().length() - 3).equalsIgnoreCase("apk")) {
				return true;
			}
			return false;
		}

		/**
		 * The description of this filter. For example: "JPG and GIF Images"
		 *
		 * @see javax.swing.filechooser.FileView#getName
		 */
		@Override
		public String getDescription() {
			return "APK files only";
		}
	}
}
