package com.trust5.odpbuilder.view;

import com.trust5.odpbuilder.control.ODPBuilder;
import com.trust5.odpbuilder.control.ProjectManager;
import com.trust5.odpbuilder.model.Project;
import com.trust5.odpbuilder.model.Workspace;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author Ruairi
 * @version 1.0
 * @since 07/12/12 - 15:45
 */
public class WorkspaceFrame extends JFrame {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private ProjectPanel mProjectpanel;

	// ===========================================================
	// Constructors
	// ===========================================================
	public WorkspaceFrame() {
		super("ODP Builder");

		if (Workspace.getCurrentProject() != null) {
			ProjectManager.loadProject(Workspace.getCurrentProject());
		}

		addMenu();
		addToolBar();
		addProjectPanel();
		addConfigurationPanel();


		this.pack();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public void updateComponent(int pComponentId, String data) {
		if (pComponentId == ProjectPanel.ID) {
			if (mProjectpanel != null) {
				mProjectpanel.addNewProject(data);
			}
		}

	}


	// ===========================================================
	// Methods
	// ===========================================================
	private void addToolBar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		toolbar.setBorder(BorderFactory.createBevelBorder(EtchedBorder.RAISED));

		Dimension dimension = new Dimension(50, 75);

		ImageIcon plusIcon = new ImageIcon(getClass().getResource("/images/plus.png"));//{
			/*@Override
			public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
				g.drawImage(getImage(), x, y, c.getWidth(), c.getHeight(), c);
			}
		};*/
		JButton newProjectButton = new JButton("New", plusIcon);
		newProjectButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		newProjectButton.setMaximumSize(dimension);
		newProjectButton.setToolTipText("Add a new project to workspace");
		newProjectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showNewProjectDialogue();
			}
		});
		toolbar.add(newProjectButton);

		add(toolbar, BorderLayout.NORTH);
	}

	private void addProjectPanel() {
		mProjectpanel = new ProjectPanel();
		add(mProjectpanel, BorderLayout.WEST);
	}

	private void addConfigurationPanel() {
		add(createConfigurationTabbedPane(), BorderLayout.CENTER);
	}

	private void addMenu() {
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		menuBar = new JMenuBar();
		menu = new JMenu("File");

		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(
				"Workspace actions");
		//menuBar.add(menu);

		menuItem = new JMenuItem("New Project",
				KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_N, InputEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Create a new project");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showNewProjectDialogue();
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Delete Project",
				KeyEvent.VK_D);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_D, InputEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Deletes a project");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Delete project
			}
		});
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Exit",
				KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_X, InputEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Closes application");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("ODP");

		menu.setMnemonic(KeyEvent.VK_O);
		menu.getAccessibleContext().setAccessibleDescription(
				"ODP actions");

		menuItem = new JMenuItem("Build",
				KeyEvent.VK_B);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_B, InputEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Builds ODP");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ODPBuilder().buildODPWithConfigurations();
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Update ODP File",
				KeyEvent.VK_U);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_U, InputEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Updates Stored ODP");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ODPImportFrame();
			}
		});
		menu.add(menuItem);

		menuBar.add(menu);


		setJMenuBar(menuBar);
	}

	private JTabbedPane createConfigurationTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Deployment", new DeploymentPanel(Workspace.getCurrentProject().getDeployment()));
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Games", new GamesPanel());
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_1);
		tabbedPane.addTab("UI Config", new UIConfigPanel(Workspace.getCurrentProject().getUIConfiguration()));
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_2);
		return tabbedPane;
	}

	private void showNewProjectDialogue() {
		try {
			String result = JOptionPane.showInputDialog(WorkspaceFrame.this, "Enter Project Name",
					JOptionPane.PLAIN_MESSAGE);
			if (!Workspace.addProjectToWorkspace(new Project(result, null))) {
				JOptionPane.showMessageDialog(WorkspaceFrame.this, "A project with that name already " +
						"exists", "Error Adding Project", JOptionPane.ERROR_MESSAGE);
			}
			else if (result != null && Workspace.getProjects().containsKey(result)) {
				updateComponent(ProjectPanel.ID, result);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
