package com.trust5.odpbuilder;

import com.trust5.odpbuilder.model.Workspace;
import com.trust5.odpbuilder.view.ODPImportFrame;
import com.trust5.odpbuilder.view.WorkspaceFrame;
import com.trust5.odpbuilder.view.WorkspacePicker;

import java.io.File;

public class App {
	private static void createAndShowGUI() {
		try {
			Workspace.initialize(null);
			if (Workspace.getLocation() == null) {
				new WorkspacePicker();
			}
			else {
				if (new File(Workspace.getWorkspaceConfigDirLocation() + File.separator +
						Constants.ODP_FILE_NAME).exists()) {
					new WorkspaceFrame();
				}
				else {
					new ODPImportFrame();
				}
			}
		} catch (Exception e) {
			new WorkspacePicker();
		}

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
