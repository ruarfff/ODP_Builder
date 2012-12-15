package com.trust5.odpbuilder.view;

import com.trust5.odpbuilder.control.ODPBuilder;
import com.trust5.odpbuilder.model.Project;
import com.trust5.odpbuilder.model.Workspace;

import javax.swing.*;
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
public class MainFrame extends JFrame {
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
	public MainFrame() {
		super("ODP Builder");
		addMenu();


		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		mProjectpanel = new ProjectPanel();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.5;
		gridBagConstraints.weighty = 0.5;
		panel.add(mProjectpanel, gridBagConstraints);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		panel.add(createTabbedInterface(), gridBagConstraints);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;

		this.add(panel);
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// this.setSize(width/2, height/2);
		// center the jframe on screen
		this.setLocationRelativeTo(null);
		setExtendedState(Frame.MAXIMIZED_BOTH);
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
				try {
					String result = JOptionPane.showInputDialog(MainFrame.this, "Enter Project Name",
							JOptionPane.PLAIN_MESSAGE);
					if (!Workspace.addProjectToWorkspace(new Project(result, null))) {
						JOptionPane.showMessageDialog(MainFrame.this, "A project with that name already " +
								"exists", "Error Adding Project", JOptionPane.ERROR_MESSAGE);
					}
					else if (result != null && Workspace.getProjects().containsKey(result)) {
						updateComponent(ProjectPanel.ID, result);
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
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

			}
		});
		menu.add(menuItem);

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

	private JTabbedPane createTabbedInterface() {
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Deployment", new DeploymentPanel());
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Games", new GamesPanel());
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_1);
		tabbedPane.addTab("UI Config", new UIConfigPanel());
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_2);
		return tabbedPane;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
