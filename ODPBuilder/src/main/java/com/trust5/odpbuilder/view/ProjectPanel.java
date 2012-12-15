package com.trust5.odpbuilder.view;

import com.trust5.odpbuilder.model.Project;
import com.trust5.odpbuilder.model.Workspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Ruairi
 * @version 1.0
 * @since 11/12/12 - 11:52
 */
public class ProjectPanel extends JPanel {

	public static int ID = 1;

	private static int sCurrentIndex;

	private static String CurrentActiveProject;

	ArrayList<JToggleButton> mToggleButtons;

	public ProjectPanel() {
		super(new GridBagLayout());
		mToggleButtons = new ArrayList<JToggleButton>();
		//Dimension size = getPreferredSize();
		//size.width = 150;
		//setPreferredSize(size);
		setBorder(BorderFactory.createTitledBorder("Projects"));

		if (Workspace.getProjects() != null) {

			String currentProjectName = Workspace.getCurrentProjectName();
			for (Project project : Workspace.getProjects().values()) {
				addProject(project.getName(), currentProjectName);
			}
		}
	}

	public void addProject(final String projectName, final String currentActiveProjectName) {
		JToggleButton projectButton = new JToggleButton(projectName, (currentActiveProjectName != null &&
				currentActiveProjectName.equals(projectName)));
		mToggleButtons.add(projectButton);
		projectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					Workspace.setCurrentProjectName(abstractButton.getText());
				}
				else {
					Workspace.setCurrentProjectName("");
				}
				for (JToggleButton toggleButton : mToggleButtons) {
					toggleButton.getModel().setSelected((currentActiveProjectName != null &&
							Workspace.getCurrentProjectName().equals(toggleButton.getText())));
				}
			}
		});

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = sCurrentIndex++;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.5;
		gridBagConstraints.weighty = 0.5;
		add(projectButton, gridBagConstraints);
		validate();
	}

	public void addNewProject(String projectName) {
		addProject(projectName, projectName);
	}

}
