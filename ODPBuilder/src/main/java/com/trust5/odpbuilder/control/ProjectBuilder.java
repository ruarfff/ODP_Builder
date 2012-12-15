package com.trust5.odpbuilder.control;

import com.trust5.odpbuilder.Constants;
import com.trust5.odpbuilder.model.Project;
import com.trust5.odpbuilder.model.Workspace;
import com.trust5.odpbuilder.utility.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author Ruairi
 * @version 1.0
 * @since 13/12/12 - 14:04
 */
public class ProjectBuilder {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "ProjectBuilder";

	private final Project mProject;

	private static final String FILE_SEPARATOR = "/";
	private static final String INIT_FILE = "init.bat";
	private static final String ODP_BUILDER_FILE = "odp_builder.bat";

	private static final String CONFIGS = "workspace" + FILE_SEPARATOR + "configs";
	private static final String APP = "app";
	private static final String ODP_APK_PROJECT_FOLDER = "odp_apk";


	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	public ProjectBuilder(Project pProject) {
		this.mProject = pProject;
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
	public void buildProjectStructure() {
		try {
			File projectDir = new File(mProject.getLocation());
			if (!projectDir.exists()) {
				if (projectDir.mkdirs()) {
					addResources();
					loadAndRunInitFile();
					copyODPToProject();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadAndRunInitFile() {
		FileInputStream in = null;
		OutputStream out = null;
		try {
			URL resource = getClass().getResource(FILE_SEPARATOR + INIT_FILE);
			File file = new File(resource.toURI());
			in = new FileInputStream(file);

			out = new FileOutputStream(mProject.getLocation() + File.separator +
					INIT_FILE);

			int read;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception ignore) {
			}
		}
		try {
			Runtime.getRuntime().exec("cmd /c init.bat", null, new File(mProject.getLocation()));
		} catch (Exception ignore) {
		}
	}

	private void addResources() {
		try {
			copyResourceToLocalStorage(APP);
			copyResourceToLocalStorage(CONFIGS);
			copyResourceToLocalStorage(ODP_BUILDER_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void copyResourceToLocalStorage(String pResourceName) throws Exception {
		URL localResourceLocation = getClass().getResource(FILE_SEPARATOR + pResourceName);
		File resource = new File(localResourceLocation.toURI());
		File resourceDestination = new File(mProject.getLocation() + File.separator + pResourceName);
		FileUtils.copy(resource, resourceDestination);
	}

	private void copyODPToProject() {
		try {
			File odpSource = new File(Workspace.getWorkspaceConfigDirLocation() + File.separator +
					Constants.ODP_FILE_NAME);
			File destDir = new File(mProject.getLocation() + File.separator + APP + File.separator +
					ODP_APK_PROJECT_FOLDER);
			if (!destDir.exists()) {
				destDir.mkdirs();
			}

			File odpDestination = new File(mProject.getLocation() + File.separator + APP + File.separator +
					ODP_APK_PROJECT_FOLDER + File.separator + Constants.ODP_FILE_NAME);

			FileUtils.copy(odpSource, odpDestination);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
