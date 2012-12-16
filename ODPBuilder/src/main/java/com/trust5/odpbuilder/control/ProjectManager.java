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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
		String xmlPath = pProject.getXMLConfigurationsPath();
		pProject.setDeployment(loadDeployment(xmlPath));
		pProject.setUIConfiguration(loadUIConfiguration(xmlPath));
		pProject.setGames(loadGames(xmlPath));
	}

	public static void saveProject(Project pProject) {
		String xmlPath = pProject.getXMLConfigurationsPath();
		saveDeployment(xmlPath, pProject.getDeployment());
		saveUIConfiguration(xmlPath, pProject.getUIConfiguration());
		saveGames(xmlPath, pProject.getGames());
	}

	private static Deployment loadDeployment(String pXMLConfigPath) {
		Deployment deployment = null;
		try {
			deployment = DeploymentXMLHandler.parseXML(pXMLConfigPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deployment;
	}

	private static UIConfiguration loadUIConfiguration(String pXMLConfigPath) {
		UIConfiguration uiConfiguration = null;
		try {
			uiConfiguration = UIConfigurationXMLHandler.parseXML(pXMLConfigPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uiConfiguration;
	}

	private static ArrayList<Game> loadGames(String pXMLConfigPath) {
		ArrayList<Game> games = null;
		try {
			games = GamesXMLHandler.parseXML(pXMLConfigPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return games;
	}

	private static void saveDeployment(String pXMLConfigPath, Deployment pDeployment) {
		try {
			DeploymentXMLHandler.updateXML(pXMLConfigPath, pDeployment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void saveUIConfiguration(String pXMLConfigPath, UIConfiguration pUIConfiguration) {
		try {
			UIConfigurationXMLHandler.updateXML(pXMLConfigPath, pUIConfiguration);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void saveGames(String pXMLConfigPath, ArrayList<Game> pGames) {
		try {
			GamesXMLHandler.updateXML(pXMLConfigPath, pGames);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void saveXMLFile(Document pDocument, String pPath) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(pDocument);
			StreamResult result = new StreamResult(new File(pPath));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	private static class DeploymentXMLHandler {

		private static Document booleansDoc;
		private static Document intsDoc;
		private static Document stringsDoc;
		private static NodeList booleanList;
		private static NodeList intList;
		private static NodeList stringList;
		private static String xmlConfigPath;

		public static void updateXML(String pXMLConfigPath, Deployment pDeployment) throws Exception {
			try {
				xmlConfigPath = pXMLConfigPath;
				loadDocs(pXMLConfigPath);
				updateBooleans(pDeployment, booleanList);
				updateInts(pDeployment, intList);
				updateStrings(pDeployment, stringList);
			} finally {
				booleansDoc = null;
				intsDoc = null;
				stringsDoc = null;
				booleanList = null;
				intList = null;
				stringList = null;
			}
		}

		public static Deployment parseXML(String pXMLConfigPath) throws Exception {
			try {
				xmlConfigPath = pXMLConfigPath;
				loadDocs(pXMLConfigPath);
				Deployment deployment = new Deployment();
				loadBooleans(deployment, booleanList);
				loadIntegers(deployment, intList);
				loadStrings(deployment, stringList);

				return deployment;
			} finally {
				booleansDoc = null;
				intsDoc = null;
				stringsDoc = null;
				booleanList = null;
				intList = null;
				stringList = null;
			}
		}

		private static void loadDocs(String pXMLConfigPath) throws Exception {
			booleansDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath +
					File.separator + BOOLS_FILE_NAME);
			booleansDoc.getDocumentElement().normalize();
			intsDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath +
					File.separator + INTEGERS_FILE_NAME);
			intsDoc.getDocumentElement().normalize();

			stringsDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath +
					File.separator + STRINGS_FILE_NAME);
			stringsDoc.getDocumentElement().normalize();

			Node booleanResources = booleansDoc.getFirstChild();
			Node intResources = intsDoc.getFirstChild();
			Node stringResources = stringsDoc.getFirstChild();

			booleanList = booleanResources.getChildNodes();
			intList = intResources.getChildNodes();
			stringList = stringResources.getChildNodes();
		}

		private static void updateBooleans(Deployment pDeployment, NodeList pBooleans) {
			for (int i = 0; i < booleanList.getLength(); i++) {
				try {
					Node node = booleanList.item(i);
					Element element = (Element) node;
					String elementName = element.getAttribute("name");
					if (elementName.equalsIgnoreCase("allowDownloads")) {
						node.setTextContent(String.valueOf(pDeployment.isAllowDownloads()));
					}
					else if (elementName.equalsIgnoreCase("autoRefresh")) {
						node.setTextContent(String.valueOf(pDeployment.isAutoRefresh()));
					}
					else if (elementName.equalsIgnoreCase("refresh")) {
						node.setTextContent(String.valueOf(pDeployment.isRefresh()));
					}
					else if (elementName.equalsIgnoreCase("checkNonMarketSetting")) {
						node.setTextContent(String.valueOf(pDeployment.isCheckNonMarketSettings()));
					}
					else if (elementName.equalsIgnoreCase("useEmbed")) {
						node.setTextContent(String.valueOf(pDeployment.isUseEmbed()));
					}
					else if (elementName.equalsIgnoreCase("bypassUnwantedLocales")) {
						node.setTextContent(String.valueOf(pDeployment.isBypassUnwantedLocales()));
					}
					else if (elementName.equalsIgnoreCase("supportTraditionalChineseAssets")) {
						node.setTextContent(String.valueOf(pDeployment.isSupportTraditionalChineseAssets()));
					}
					else if (elementName.equalsIgnoreCase("isMarketPlace")) {
						node.setTextContent(String.valueOf(pDeployment.isIsMarketPlace()));
					}
					else if (elementName.equalsIgnoreCase("wifiRequiredForDownloads")) {
						node.setTextContent(String.valueOf(pDeployment.isWifiRequiredForDownloads()));
					}
					else if (elementName.equalsIgnoreCase("wifiRecommendedForDownloads")) {
						node.setTextContent(String.valueOf(pDeployment.isWifiRecommendedForDownloads()));
					}
					else if (elementName.equalsIgnoreCase("useMd5Check")) {
						node.setTextContent(String.valueOf(pDeployment.isUseMD5Check()));
					}
					else if (elementName.equalsIgnoreCase("verifyFileSize")) {
						node.setTextContent(String.valueOf(pDeployment.isVerifyFileSize()));
					}
					else if (elementName.equalsIgnoreCase("useAESEncryption")) {
						node.setTextContent(String.valueOf(pDeployment.isUseAESEncryption()));
					}
					else if (elementName.equalsIgnoreCase("useJSBilling")) {
						node.setTextContent(String.valueOf(pDeployment.isUseJSBilling()));
					}
					else if (elementName.equalsIgnoreCase("sendNativeEventsToJS")) {
						node.setTextContent(String.valueOf(pDeployment.isSendNativeEventsToJS()));
					}
					else if (elementName.equalsIgnoreCase("useDownloadManager")) {
						node.setTextContent(String.valueOf(pDeployment.isUseDownloadManager()));
					}
					else if (elementName.equalsIgnoreCase("allowDownloadCancel")) {
						node.setTextContent(String.valueOf(pDeployment.isAllowDownloadCancel()));
					}
					else if (elementName.equalsIgnoreCase("appendInfoToAllHttpRequests")) {
						node.setTextContent(String.valueOf(pDeployment.isAppendInfoToAllHttpRequests()));
					}
					else if (elementName.equalsIgnoreCase("isEmbedInstaller")) {
						node.setTextContent(String.valueOf(pDeployment.isEmbedInstaller()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			saveXMLFile(booleansDoc, xmlConfigPath +
					File.separator + BOOLS_FILE_NAME);
		}

		private static void updateInts(Deployment pDeployment, NodeList pInts) {
			for (int i = 0; i < intList.getLength(); i++) {
				try {
					Node node = intList.item(i);
					Element element = (Element) node;
					String elementName = element.getAttribute("name");
					if (elementName.equalsIgnoreCase("deploymentID")) {
						node.setTextContent(String.valueOf(pDeployment.getDeploymentId()));
					}
					else if (elementName.equalsIgnoreCase("gameID")) {
						node.setTextContent(String.valueOf(pDeployment.getGameId()));
					}
					else if (elementName.equalsIgnoreCase("marketplaceApplicationID")) {
						node.setTextContent(String.valueOf(pDeployment.getMarketPlaceApplicationId()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			saveXMLFile(intsDoc, xmlConfigPath + File.separator + INTEGERS_FILE_NAME);
		}

		private static void updateStrings(Deployment pDeployment, NodeList pStrings) {
			for (int i = 0; i < stringList.getLength(); i++) {
				try {
					Node node = stringList.item(i);
					Element element = (Element) node;
					String elementName = element.getAttribute("name");
					if (elementName.equalsIgnoreCase("onlineURL")) {
						node.setTextContent(pDeployment.getOnlineUrl());
					}
					else if (elementName.equalsIgnoreCase("refreshAPIURL")) {
						node.setTextContent(pDeployment.getRefreshAPIUrl());
					}
					else if (elementName.equalsIgnoreCase("campaign")) {
						node.setTextContent(pDeployment.getCampaign());
					}
					else if (elementName.equalsIgnoreCase("userAgentModifiedValue")) {
						node.setTextContent(pDeployment.getUserAgentModifierValue());
					}
					else if (elementName.equalsIgnoreCase("defaultEmbedFileName")) {
						node.setTextContent(pDeployment.getDefaultEmbedFileName());
					}
					else if (elementName.equalsIgnoreCase("embedURL")) {
						node.setTextContent(pDeployment.getEmbedUrl());
					}
					else if (elementName.equalsIgnoreCase("moreGamesUrl")) {
						node.setTextContent(pDeployment.getMoreGamesUrl());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			saveXMLFile(stringsDoc, xmlConfigPath + File.separator + STRINGS_FILE_NAME);
		}

		private static void loadBooleans(Deployment pDeployment, NodeList pBooleans) {
			for (int i = 0; i < pBooleans.getLength(); i++) {
				try {
					if (pBooleans.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Node boolNode = pBooleans.item(i);
						String val = boolNode.getFirstChild().getNodeValue();
						boolean value = Boolean.valueOf(boolNode.getFirstChild().getNodeValue());

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
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private static void loadIntegers(Deployment pDeployment, NodeList pIntegers) {
			for (int i = 0; i < pIntegers.getLength(); i++) {
				try {
					if (pIntegers.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Node intNode = pIntegers.item(i);
						int value = Integer.decode(intNode.getFirstChild().getNodeValue());
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
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private static void loadStrings(Deployment pDeployment, NodeList pStrings) {
			for (int i = 0; i < pStrings.getLength(); i++) {
				try {
					if (pStrings.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Node stringNode = pStrings.item(i);
						String value = stringNode.getFirstChild().getNodeValue();
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
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	private static class UIConfigurationXMLHandler {

		private static Document booleansDoc;
		private static Document intsDoc;
		private static NodeList booleanList;
		private static NodeList intList;
		private static String xmlConfigPath;

		public static void updateXML(String pXMLConfigPath, UIConfiguration pUIConfiguration) throws Exception {
			try {
				xmlConfigPath = pXMLConfigPath;
				loadDocs(pXMLConfigPath);
				updateBooleans(pUIConfiguration, booleanList);
				updateInts(pUIConfiguration, intList);
			} finally {
				booleansDoc = null;
				intsDoc = null;
				booleanList = null;
				intList = null;
			}

		}

		public static UIConfiguration parseXML(String pXMLConfigPath) throws Exception {
			try {
				xmlConfigPath = pXMLConfigPath;
				loadDocs(pXMLConfigPath);
				UIConfiguration uiConfiguration = new UIConfiguration();
				loadBooleans(uiConfiguration, booleanList);
				loadIntegers(uiConfiguration, intList);
				return uiConfiguration;
			} finally {
				booleansDoc = null;
				intsDoc = null;
				booleanList = null;
				intList = null;
			}
		}

		private static void loadDocs(String pXMLConfigPath) throws Exception {
			booleansDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath +
					File.separator + BOOLS_FILE_NAME);
			booleansDoc.getDocumentElement().normalize();
			intsDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath +
					File.separator + INTEGERS_FILE_NAME);
			intsDoc.getDocumentElement().normalize();
			Node booleanResources = booleansDoc.getFirstChild();
			Node intResources = intsDoc.getFirstChild();
			booleanList = booleanResources.getChildNodes();
			intList = intResources.getChildNodes();
		}

		private static void updateBooleans(UIConfiguration pUIConfiguration, NodeList pBooleans) {
			for (int i = 0; i < booleanList.getLength(); i++) {
				try {
					Node node = booleanList.item(i);
					Element element = (Element) node;
					String elementName = element.getAttribute("name");
					if (elementName.equalsIgnoreCase("showTopBar")) {
						node.setTextContent(String.valueOf(pUIConfiguration.isShowTopBar()));
					}
					else if (elementName.equalsIgnoreCase("showAppInFullScreen")) {
						node.setTextContent(String.valueOf(pUIConfiguration.isShowAppInFullScreen()));
					}
					else if (elementName.equalsIgnoreCase("allowPageScrolling")) {
						node.setTextContent(String.valueOf(pUIConfiguration.isAllowPageScrolling()));
					}
					else if (elementName.equalsIgnoreCase("showSplashScreen")) {
						node.setTextContent(String.valueOf(pUIConfiguration.isShowSplashScreen()));
					}
					else if (elementName.equalsIgnoreCase("showDownloadedItemsMenu")) {
						node.setTextContent(String.valueOf(pUIConfiguration.isShowDownloadedItemsMenu()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			saveXMLFile(booleansDoc, xmlConfigPath +
					File.separator + BOOLS_FILE_NAME);
		}

		private static void updateInts(UIConfiguration pUIConfiguration, NodeList pInts) {
			for (int i = 0; i < intList.getLength(); i++) {
				try {
					Node node = intList.item(i);
					Element element = (Element) node;
					String elementName = element.getAttribute("name");
					if (elementName.equalsIgnoreCase("splashScreenDisplayTime")) {
						node.setTextContent(String.valueOf(pUIConfiguration.getSplashScreenDisplayTime()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			saveXMLFile(intsDoc, xmlConfigPath + File.separator + INTEGERS_FILE_NAME);
		}

		private static void loadBooleans(UIConfiguration pUIConfiguration, NodeList pBooleans) {
			for (int i = 0; i < pBooleans.getLength(); i++) {
				try {
					if (pBooleans.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Node boolNode = pBooleans.item(i);
						boolean value = Boolean.valueOf(boolNode.getFirstChild().getNodeValue());
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
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private static void loadIntegers(UIConfiguration pUIConfiguration, NodeList pIntegers) {
			for (int i = 0; i < pIntegers.getLength(); i++) {
				try {
					if (pIntegers.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Node intNode = pIntegers.item(i);
						int value = Integer.decode(intNode.getFirstChild().getNodeValue());
						Element boolElement = (Element) intNode;
						String elementName = boolElement.getAttribute("name");
						if (elementName.equalsIgnoreCase("splashScreenDisplayTime")) {
							pUIConfiguration.setSplashScreenDisplayTime(value);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	private static class GamesXMLHandler {

		public static void updateXML(String pXMLConfigPath, ArrayList<Game> pGames) throws Exception {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath + File
					.separator + ARRAYS_FILE_NAME);
			doc.getDocumentElement().normalize();
			Node arrayResources = doc.getFirstChild();
			NodeList arrays = arrayResources.getChildNodes();
			removeChildNodesFromElement("listOfGames", arrays);
			removeChildNodesFromElement("bannerNames", arrays);
			removeChildNodesFromElement("gameTypes", arrays);
			removeChildNodesFromElement("listOfGameDownloadUrls", arrays);

			for (Game game : pGames) {
				Element gameName = doc.createElement("item");
				gameName.appendChild(doc.createTextNode(game.getName()));
				Element bannerName = doc.createElement("item");
				bannerName.appendChild(doc.createTextNode(game.getBannerName()));
				Element gameType = doc.createElement("item");
				gameType.appendChild(doc.createTextNode(game.getType()));
				Element downloadUrl = doc.createElement("item");
				downloadUrl.appendChild(doc.createTextNode(game.getDownloadUrl()));

				for (int i = 0; i < arrays.getLength(); i++) {
					try {
						Node arrayNode = arrays.item(i);
						Element arrayElement = (Element) arrayNode;
						if (arrayElement.getAttribute("name").equalsIgnoreCase("listOfGames")) {
							arrayElement.appendChild(gameName);
						}
						else if (arrayElement.getAttribute("name").equalsIgnoreCase("bannerNames")) {
							arrayElement.appendChild(bannerName);
						}
						else if (arrayElement.getAttribute("name").equalsIgnoreCase("gameTypes")) {
							arrayElement.appendChild(gameType);
						}
						else if (arrayElement.getAttribute("name").equalsIgnoreCase("listOfGameDownloadUrls")) {
							arrayElement.appendChild(downloadUrl);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			saveXMLFile(doc, pXMLConfigPath + File
					.separator + ARRAYS_FILE_NAME);
		}

		public static ArrayList<Game> parseXML(String pXMLConfigPath) throws Exception {
			ArrayList<Game> games = new ArrayList<Game>();
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pXMLConfigPath + File
					.separator + ARRAYS_FILE_NAME);
			doc.getDocumentElement().normalize();
			Node arrayResources = doc.getFirstChild();
			NodeList arrays = arrayResources.getChildNodes();
			populateGameList(games, arrays);
			return games;
		}

		private static void populateGameList(ArrayList<Game> pGames, NodeList pArrays) {
			NodeList gameNames = getNodeListByName("listOfGames", pArrays);
			NodeList bannerNames = getNodeListByName("bannerNames", pArrays);
			NodeList gameTypes = getNodeListByName("gameTypes", pArrays);
			NodeList downloadUrls = getNodeListByName("listOfGameDownloadUrls", pArrays);

			for (int i = 0; i < gameNames.getLength(); i++) {
				try {
					Game game = new Game();
					game.setName(gameNames.item(i).getFirstChild().getNodeValue());
					game.setBannerName(bannerNames.item(i).getFirstChild().getNodeValue());
					game.setType(gameTypes.item(i).getFirstChild().getNodeValue());
					game.setDownloadUrl(downloadUrls.item(i).getFirstChild().getNodeValue());
					pGames.add(game);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		private static void removeChildNodesFromElement(String pName, NodeList pNodeList) {
			for (int i = 0; i < pNodeList.getLength(); i++) {
				try {
					Node arrayNode = pNodeList.item(i);
					Element arrayElement = (Element) arrayNode;
					if (arrayElement.getAttribute("name").equalsIgnoreCase(pName)) {
						NodeList children = arrayElement.getChildNodes();
						for (int j = 0; j < children.getLength(); j++) {
							arrayElement.removeChild(children.item(j));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
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
