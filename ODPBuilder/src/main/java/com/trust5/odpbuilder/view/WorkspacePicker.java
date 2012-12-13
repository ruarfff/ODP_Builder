package com.trust5.odpbuilder.view;

import com.trust5.odpbuilder.Constants;
import com.trust5.odpbuilder.model.Workspace;
import org.apache.commons.lang3.SystemUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Ruairi
 * @version 1.0
 * @since 07/12/12 - 15:47
 */
public class WorkspacePicker extends JFrame {
    // ===========================================================
    // Constants
    // ===========================================================
    private static final String TAG = "WorkspacePicker";

    // ===========================================================
    // Fields
    // ===========================================================
    private String mLocation;

    // ===========================================================
    // Constructors
    // ===========================================================

    public WorkspacePicker() {
        super("Select Workspace");

        loadInitialLocation();

        setupWidgets();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(width/3, height/4);
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
    private void loadInitialLocation() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SystemUtils.USER_HOME).append(File.separator)
                .append(Constants.APP_NAME).append(File.separator)
                .append("Workspace");


        mLocation = stringBuilder.toString();
    }

    private void setupWidgets() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();

        final JTextField workspacePathField = new JTextField(mLocation);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(workspacePathField, gbc);


        final JButton chooseWorkSpaceButton = new JButton("Choose Workspace");
        chooseWorkSpaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
				final JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Select Workspace Directory");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);
                int returnVal = fc.showOpenDialog(WorkspacePicker.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    if(file.isDirectory()){
                        workspacePathField.setText(file.getPath());
                    } else{
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(WorkspacePicker.this,
								"Please verify your selection is a directory and can " +
										"be written to.", "Could not initialize Workspace. ",
								JOptionPane.WARNING_MESSAGE);
					}

                }

            }
        });
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(chooseWorkSpaceButton, gbc);

        final JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mLocation = workspacePathField.getText();
                try{
                    File workspace = new File(mLocation);
					if(!workspace.exists()){
						if(!workspace.mkdirs()){
							throw new Exception("Could not create workspace directory");
						}
					}
					if(!workspace.isDirectory()){
						throw new Exception("Workspace is not a directory");
					}
					Workspace.initialize(mLocation);
					dispose();
					new MainFrame();

                } catch(Exception ex){
					System.out.println(ex.toString());
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(WorkspacePicker.this,
							"Please verify your selection is a directory and can " +
									"be written to.", "Unable to create Workspace. ",
							JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        panel.add(okButton, gbc);

        final JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkspacePicker.this.dispose();
            }
        });
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        panel.add(cancelButton, gbc);


        add(panel);
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
