package com.trust5.odpbuilder.model;

import com.trust5.odpbuilder.control.ProjectBuilder;
import com.trust5.odpbuilder.control.ProjectManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

/**
 * @author Ruairi
 * @version 1.0
 * @since 07/12/12 - 14:05
 */
public class Workspace {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "Workspace";

	private static final String PREF_KEY_LOCATION = "location";
	private static final String PREF_KEY_CURRENT_PROJECT = "current_project";

	private static final String WORKSPACE_CONFIG_DIR = ".workspace";
	private static final String WORKSPACE_XML_FILE = "workspace.xml";

	// ===========================================================
	// Fields
	// ===========================================================
	private static Preferences sPreferences;

	private static Map<String, Project> sProjects;

	// ===========================================================
	// Constructors
	// ===========================================================
	private Workspace() {
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public static String getLocation() {
		if (sPreferences != null) {
			return sPreferences.get(PREF_KEY_LOCATION, null);
		}
		return null;
	}

	public static void setLocation(String location) {
		if (sPreferences != null) {
			sPreferences.put(PREF_KEY_LOCATION, location);
		}
	}

	public static String getWorkspaceConfigDirLocation() {
		return (getLocation() != null) ? getLocation() + File.separator + WORKSPACE_CONFIG_DIR : null;
	}

	public static Map<String, Project> getProjects() {
		return sProjects;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public static void initialize(String pLocation) throws Exception {
		sPreferences = Preferences.userRoot().node("Work Space");
		if (pLocation != null) {
			setLocation(pLocation);
			createWorkspaceConfigurationDir(pLocation);
			WorkspaceXMLHandler.initializeWorkspaceXML();
		}
		if (getLocation() != null) {
			if (new File(getLocation() + File.separator +
					WORKSPACE_CONFIG_DIR + File.separator + WORKSPACE_XML_FILE).exists()) {
				sProjects = WorkspaceXMLHandler.getAllProjects();
			}
			if (!new File(getLocation()).exists()) {
				sPreferences.remove(PREF_KEY_LOCATION);
			}
		}
	}

	public static Project getCurrentProject() {
		try {
			if (sProjects != null) {
				return sProjects.get(getCurrentProjectName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setCurrentProjectName(String pCurrentProjectName) {
		if (sPreferences != null) {
			sPreferences.put(PREF_KEY_CURRENT_PROJECT, pCurrentProjectName);
		}
	}

	public static String getCurrentProjectName() {
		if (sPreferences != null) {
			return sPreferences.get(PREF_KEY_CURRENT_PROJECT, null);
		}
		return null;
	}

	public static boolean addProjectToWorkspace(Project pProject) {
		try {
			if (sProjects == null) {
				sProjects = new HashMap<String, Project>();
			}
			if (sProjects.containsKey(pProject.getName())) {
				return false;
			}

			pProject.setLocation(getLocation() + File.separator + pProject.getName());
			sProjects.put(pProject.getName(), pProject);
			WorkspaceXMLHandler.addProject(pProject);
			new ProjectBuilder(pProject).buildProjectStructure();
			ProjectManager.loadProject(pProject);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static void createWorkspaceConfigurationDir(String pRootLocation) throws Exception {
		File workspaceConfig = new File(pRootLocation + File.separator + WORKSPACE_CONFIG_DIR);
		if (!workspaceConfig.exists()) {
			if (!workspaceConfig.mkdirs()) {
				throw new Exception("Could not create workspace directory");
			}
		}
	}

	public static void dispose() {
		sPreferences = null;
		sProjects = null;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	private static class WorkspaceXMLHandler {
		private static final File workspaceXMLFile = new File(getLocation() + File.separator +
				WORKSPACE_CONFIG_DIR + File.separator + WORKSPACE_XML_FILE);

		public static void initializeWorkspaceXML() {
			try {

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				// root elements
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("workspace");
				doc.appendChild(rootElement);

				// staff elements
				Element staff = doc.createElement("projects");
				rootElement.appendChild(staff);


				// shorten way
				// staff.setAttribute("id", "1");


				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(workspaceXMLFile);

				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);

				transformer.transform(source, result);

				System.out.println("File saved!");

			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}
		}

		public static void addProjects(ArrayList<Project> pProjects) {
			try {

				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(workspaceXMLFile);

				Node workspace = doc.getFirstChild();
				Node projects = workspace.getFirstChild();
				for (Project projectObj : pProjects) {
					Element project = doc.createElement("project");
					project.setAttribute("name", projectObj.getName());
					projects.appendChild(project);
				}

				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(workspaceXMLFile);
				TransformerFactory.newInstance().newTransformer().transform(source, result);

				System.out.println("Done");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public static void addProject(Project pProject) {
			try {
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(workspaceXMLFile);

				Node workspace = doc.getFirstChild();
				Node projects = workspace.getFirstChild();

				Element project = doc.createElement("project");
				project.setAttribute("name", pProject.getName());
				project.setAttribute("location", pProject.getLocation());
				projects.appendChild(project);

				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(workspaceXMLFile);
				TransformerFactory.newInstance().newTransformer().transform(source, result);

				System.out.println("Done");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static void removeProject() {

		}

		public static void updateProject() {

		}

		public static Map<String, Project> getAllProjects() {
			Map<String, Project> projects = null;
			try {
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(workspaceXMLFile);
				doc.getDocumentElement().normalize();
				NodeList nodeList = doc.getElementsByTagName("project");
				int nodeListSize = nodeList.getLength();
				if (nodeListSize > 0) {
					projects = new HashMap<String, Project>();
					for (int i = 0; i < nodeListSize; i++) {
						Node node = nodeList.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) node;
							String projectName = element.getAttribute("name");
							String projectLocation = element.getAttribute("location");
							Project project = new Project(projectName, projectLocation);
							projects.put(project.getName(), project);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return projects;
		}

	}
}
