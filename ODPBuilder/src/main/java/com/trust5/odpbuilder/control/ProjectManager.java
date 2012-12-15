package com.trust5.odpbuilder.control;

import com.trust5.odpbuilder.model.Deployment;
import com.trust5.odpbuilder.model.Game;
import com.trust5.odpbuilder.model.Project;
import com.trust5.odpbuilder.model.UIConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Ruairi
 * @version 1.0
 * @since 15/12/12 - 18:50
 */
public class ProjectManager {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "ProjectManager";

	private static final String ARRAYS_FILE_NAME = "arrays.xml";
	private static final String BOOLS_FILE_NAME = "bools.xml";
	private static final String INTEGERS_FILE_NAME = "integers.xml";
	private static final String STRINGS_FILE_NAME = "strings.xml";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	private ProjectManager() {
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
	public static void loadProject(Project pProject) {
		pProject.setDeployment(loadDeployment(pProject.getXMLConfigurationsPath()));
		pProject.setUIConfiguration(loadUIConfiguration(pProject.getXMLConfigurationsPath()));
		pProject.setGames(loadGames(pProject.getXMLConfigurationsPath()));
	}

	private static Deployment loadDeployment(String pXMLConfigPath) {
		Deployment deployment = null;
		try {
			deployment = DeploymentXMLHandler.parseDeploymentXML(pXMLConfigPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deployment;
	}

	private static UIConfiguration loadUIConfiguration(String pXMLConfigPath) {
		UIConfiguration uiConfiguration = null;
		try {
			uiConfiguration = UIConfigurationXMLHandler.parseUIConfigurationXML(pXMLConfigPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uiConfiguration;
	}

	private static ArrayList<Game> loadGames(String pXMLConfigPath) {
		ArrayList<Game> games = null;
		try {
			games = GamesXMLHandler.parseGamesXML(pXMLConfigPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return games;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	private static class DeploymentXMLHandler {
		public static Deployment parseDeploymentXML(String pXMLConfigPath) throws Exception {
			Deployment deployment = new Deployment();
			Document booleansDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath +
					File.separator + BOOLS_FILE_NAME);
			booleansDoc.getDocumentElement().normalize();
			Document intsDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath +
					File.separator + INTEGERS_FILE_NAME);
			intsDoc.getDocumentElement().normalize();

			Document stringsDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath +
					File.separator + STRINGS_FILE_NAME);
			stringsDoc.getDocumentElement().normalize();

			Node booleanResources = booleansDoc.getFirstChild();
			Node intResources = intsDoc.getFirstChild();
			Node stringResources = stringsDoc.getFirstChild();

			NodeList booleanList = booleanResources.getChildNodes();
			NodeList intList = intResources.getChildNodes();
			NodeList stringList = stringResources.getChildNodes();
			loadBooleans(deployment, booleanList);
			loadIntegers(deployment, intList);
			loadStrings(deployment, stringList);

			return deployment;
		}

		private static void loadBooleans(Deployment pDeployment, NodeList pBooleans) {
			for (int i = 0; i < pBooleans.getLength(); i++) {
				try {
					Node boolNode = pBooleans.item(i);

					boolean value = Boolean.valueOf(boolNode.getNodeValue());

					Element boolElement = (Element) boolNode;
					String elementName = boolElement.getAttribute("name");
					if (elementName.equalsIgnoreCase("allowDownloads")) {
						pDeployment.setAllowDownloads(value);
					}
					else if (elementName.equalsIgnoreCase("autoRefresh")) {
						pDeployment.setAutoRefresh(value);
					}
					else if (elementName.equalsIgnoreCase("refresh")) {
						pDeployment.setRefresh(value);
					}
					else if (elementName.equalsIgnoreCase("checkNonMarketSetting")) {
						pDeployment.setCheckNonMarketSettings(value);
					}
					else if (elementName.equalsIgnoreCase("useEmbed")) {
						pDeployment.setUseEmbed(value);
					}
					else if (elementName.equalsIgnoreCase("bypassUnwantedLocales")) {
						pDeployment.setBypassUnwantedLocales(value);
					}
					else if (elementName.equalsIgnoreCase("supportTraditionalChineseAssets")) {
						pDeployment.setSupportTraditionalChineseAssets(value);
					}
					else if (elementName.equalsIgnoreCase("isMarketPlace")) {
						pDeployment.setIsMarketPlace(value);
					}
					else if (elementName.equalsIgnoreCase("wifiRequiredForDownloads")) {
						pDeployment.setWifiRequiredForDownloads(value);
					}
					else if (elementName.equalsIgnoreCase("wifiRecommendedForDownloads")) {
						pDeployment.setWifiRecommendedForDownloads(value);
					}
					else if (elementName.equalsIgnoreCase("useMd5Check")) {
						pDeployment.setUseMD5Check(value);
					}
					else if (elementName.equalsIgnoreCase("verifyFileSize")) {
						pDeployment.setVerifyFileSize(value);
					}
					else if (elementName.equalsIgnoreCase("useAESEncryption")) {
						pDeployment.setUseAESEncryption(value);
					}
					else if (elementName.equalsIgnoreCase("useJSBilling")) {
						pDeployment.setUseJSBilling(value);
					}
					else if (elementName.equalsIgnoreCase("sendNativeEventsToJS")) {
						pDeployment.setSendNativeEventsToJS(value);
					}
					else if (elementName.equalsIgnoreCase("useDownloadManager")) {
						pDeployment.setUseDownloadManager(value);
					}
					else if (elementName.equalsIgnoreCase("allowDownloadCancel")) {
						pDeployment.setAllowDownloadCancel(value);
					}
					else if (elementName.equalsIgnoreCase("appendInfoToAllHttpRequests")) {
						pDeployment.setAppendInfoToAllHttpRequests(value);
					}
					else if (elementName.equalsIgnoreCase("isEmbedInstaller")) {
						pDeployment.setEmbedInstaller(value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private static void loadIntegers(Deployment pDeployment, NodeList pIntegers) {
			for (int i = 0; i < pIntegers.getLength(); i++) {
				try {
					Node intNode = pIntegers.item(i);
					int value = Integer.decode(intNode.getNodeValue());
					Element boolElement = (Element) intNode;
					String elementName = boolElement.getAttribute("name");
					if (elementName.equalsIgnoreCase("deploymentID")) {
						pDeployment.setDeploymentId(value);
					}
					else if (elementName.equalsIgnoreCase("gameID")) {
						pDeployment.setGameId(value);
					}
					else if (elementName.equalsIgnoreCase("marketplaceApplicationID")) {
						pDeployment.setMarketPlaceApplicationId(value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private static void loadStrings(Deployment pDeployment, NodeList pStrings) {
			for (int i = 0; i < pStrings.getLength(); i++) {
				try {
					Node stringNode = pStrings.item(i);
					String value = stringNode.getNodeValue();
					Element boolElement = (Element) stringNode;
					String elementName = boolElement.getAttribute("name");
					if (elementName.equalsIgnoreCase("onlineURL")) {
						pDeployment.setOnlineUrl(value);
					}
					else if (elementName.equalsIgnoreCase("refreshAPIURL")) {
						pDeployment.setRefreshAPIUrl(value);
					}
					else if (elementName.equalsIgnoreCase("campaign")) {
						pDeployment.setCampaign(value);
					}
					else if (elementName.equalsIgnoreCase("userAgentModifiedValue")) {
						pDeployment.setUserAgentModifierValue(value);
					}
					else if (elementName.equalsIgnoreCase("defaultEmbedFileName")) {
						pDeployment.setDefaultEmbedFileName(value);
					}
					else if (elementName.equalsIgnoreCase("embedURL")) {
						pDeployment.setEmbedUrl(value);
					}
					else if (elementName.equalsIgnoreCase("moreGamesUrl")) {
						pDeployment.setMoreGamesUrl(value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class UIConfigurationXMLHandler {
		public static UIConfiguration parseUIConfigurationXML(String pXMLConfigPath) throws Exception {
			UIConfiguration uiConfiguration = null;
			Document booleansDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath +
					File.separator + BOOLS_FILE_NAME);
			booleansDoc.getDocumentElement().normalize();
			Document intsDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath +
					File.separator + INTEGERS_FILE_NAME);
			intsDoc.getDocumentElement().normalize();
			Node booleanResources = booleansDoc.getFirstChild();
			Node intResources = intsDoc.getFirstChild();
			NodeList booleanList = booleanResources.getChildNodes();
			NodeList intList = intResources.getChildNodes();
			loadBooleans(uiConfiguration, booleanList);
			loadIntegers(uiConfiguration, intList);
			return uiConfiguration;
		}

		private static void loadBooleans(UIConfiguration pUIConfiguration, NodeList pBooleans) {
			for (int i = 0; i < pBooleans.getLength(); i++) {
				try {
					Node boolNode = pBooleans.item(i);
					boolean value = Boolean.valueOf(boolNode.getNodeValue());
					Element boolElement = (Element) boolNode;
					String elementName = boolElement.getAttribute("name");
					if (elementName.equalsIgnoreCase("showTopBar")) {
						pUIConfiguration.setShowTopBar(value);
					}
					else if (elementName.equalsIgnoreCase("showAppInFullScreen")) {
						pUIConfiguration.setShowAppInFullScreen(value);
					}
					else if (elementName.equalsIgnoreCase("allowPageScrolling")) {
						pUIConfiguration.setAllowPageScrolling(value);
					}
					else if (elementName.equalsIgnoreCase("showSplashScreen")) {
						pUIConfiguration.setShowSplashScreen(value);
					}
					else if (elementName.equalsIgnoreCase("showDownloadedItemsMenu")) {
						pUIConfiguration.setShowDownloadedItemsMenu(value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private static void loadIntegers(UIConfiguration pUIConfiguration, NodeList pIntegers) {
			for (int i = 0; i < pIntegers.getLength(); i++) {
				try {
					Node intNode = pIntegers.item(i);
					int value = Integer.decode(intNode.getNodeValue());
					Element boolElement = (Element) intNode;
					String elementName = boolElement.getAttribute("name");
					if (elementName.equalsIgnoreCase("splashScreenDisplayTime")) {
						pUIConfiguration.setSplashScreenDisplayTime(value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class GamesXMLHandler {
		public static ArrayList<Game> parseGamesXML(String pXMLConfigPath) throws Exception {
			ArrayList<Game> games = new ArrayList<Game>();
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath + File
					.separator + ARRAYS_FILE_NAME);
			doc.getDocumentElement().normalize();
			Node arrayResources = doc.getFirstChild();
			NodeList arrays = arrayResources.getChildNodes();

			return games;
		}

		private static void populateGameList(ArrayList<Game> pGames, NodeList pArrays) {
			NodeList gameNames = getNodeListByName("listOfGames", pArrays);
			NodeList bannerNames = getNodeListByName("bannerNames", pArrays);
			NodeList gameTypes = getNodeListByName("gameTypes", pArrays);
			NodeList downloadUrls = getNodeListByName("listOfGameDownloadUrls", pArrays);

			for (int i = 0; i < gameNames.getLength(); i++) {
				Game game = new Game();
				game.setName(gameNames.item(i).getNodeValue());
				game.setBannerName(bannerNames.item(i).getNodeValue());
				game.setType(gameTypes.item(i).getNodeValue());
				game.setDownloadUrl(downloadUrls.item(i).getNodeValue());
				pGames.add(game);
			}

		}

		private static NodeList getNodeListByName(String pName, NodeList pNodeList) {
			for (int i = 0; i < pNodeList.getLength(); i++) {
				try {
					Node arrayNode = pNodeList.item(i);
					Element arrayElement = (Element) arrayNode;
					if (arrayElement.getAttribute("name").equalsIgnoreCase(pName)) {
						return arrayElement.getChildNodes();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}

	}
}
