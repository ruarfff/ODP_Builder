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
 * @since 11/12/12 - 11:52
 */
public class ProjectPanel extends JPanel {

    public ProjectPanel() {
        super(new GridBagLayout());

        Dimension size = getPreferredSize();
        size.width = 250;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Projects"));

		if(Workspace.getProjects() != null){
			int currentIndex = 0;
			String currentProjectName = Workspace.getCurrentProjectName();
			for(Project project : Workspace.getProjects()){
				JToggleButton projectButton = new JToggleButton(project.getName(), (currentProjectName != null &&
						currentProjectName.equals(project.getName())));
				projectButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						AbstractButton abstractButton = (AbstractButton) e.getSource();
						boolean selected = abstractButton.getModel().isSelected();
						if(selected){
							Workspace.setCurrentProject(new Project(abstractButton.getText()));
						} else{
							Workspace.setCurrentProject(new Project(""));
						}
					}
				});
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.anchor = GridBagConstraints.WEST;
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = currentIndex++;
				gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
				gridBagConstraints.weightx = 0.5;
				gridBagConstraints.weighty = 0.5;
				add(projectButton, gridBagConstraints);

			}
		}
    }
}
