package com.trust5.odpbuilder.control;

import com.trust5.odpbuilder.model.Project;
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

	private static final String CONFIGS = "configs" + FILE_SEPARATOR;
	private static final String APP = "app" + FILE_SEPARATOR;

	private static final String ARRAYS = CONFIGS + "arrays.xml";
	private static final String BOOLS = CONFIGS + "bools.xml";
	private static final String INTS = CONFIGS + "integers.xml";
	private static final String STRINGS = CONFIGS + "strings.xml";

	private static final String KEY = APP + "key" + FILE_SEPARATOR + "trust5Wrapper.keystore";
	private static final String LIB = APP + "lib" + FILE_SEPARATOR + "tools.jar";

	private static final String TOOLS = APP + "tools";



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
	public void buildProjectStructure(){
		try{
			File projectDir = new File(mProject.getLocation());
			if(!projectDir.exists()){
				if(projectDir.mkdirs()){
					loadAndRunInitFile();
					addResources();
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private void loadAndRunInitFile(){
		FileInputStream in = null;
		OutputStream out = null;
		try{
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
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			try{
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception ignore){}
		}
		try{
			Runtime.getRuntime().exec("cmd /c init.bat", null, new File(mProject.getLocation()));
		} catch (Exception ignore){}
	}

	private	void addResources(){
		try{
			URL toolsDirLocation = getClass().getResource(FILE_SEPARATOR + TOOLS);
			File toolsDir = new File(toolsDirLocation.toURI());
			File toolsDestination = new File(mProject.getLocation() + File.separator + "App" + File.separator +
					"tools");
		FileUtils.copy(toolsDir, toolsDestination);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
