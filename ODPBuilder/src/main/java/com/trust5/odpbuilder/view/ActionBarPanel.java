package com.trust5.odpbuilder.view;

import com.trust5.odpbuilder.model.Project;
import com.trust5.odpbuilder.model.Workspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Ruairi
 * @version 1.0
 * @since 12/12/12 - 17:53
 */
public class ActionBarPanel extends JPanel {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "ActionBarPanel";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public ActionBarPanel() {
		super(new GridLayout(1, 8, 20, 10));

		Dimension size = getPreferredSize();
		size.height = 50;
		setPreferredSize(size);
		setBorder(BorderFactory.createEtchedBorder());

		JButton newProjectButton = new JButton("New Project");
		newProjectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				String result = JOptionPane.showInputDialog(ActionBarPanel.this, "Enter Project Name",
						JOptionPane.PLAIN_MESSAGE);
					if(!Workspace.addProjectToWorkspace(new Project(result))){
						 JOptionPane.showMessageDialog(ActionBarPanel.this, "A project with that name already " +
								 "exists", "Error Adding Project", JOptionPane.ERROR_MESSAGE);
					}
				}catch (Exception ex){
					 ex.printStackTrace();
				}
			}
		});
		add(newProjectButton);
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

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
