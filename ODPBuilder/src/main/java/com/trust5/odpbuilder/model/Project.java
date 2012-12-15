package com.trust5.odpbuilder.model;

import java.io.File;
import java.util.ArrayList;

/**
 * @author ruairi
 * @version 1.0
 * @since 12/12/12 - 00:08
 */
public class Project {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "Project";

	private static final String WORKSPACE_DIR = "workspace";
	private static final String XML_CONFIG_DIR = WORKSPACE_DIR + File.separator + "configs";
	private static final String IMAGES_DIR = WORKSPACE_DIR + File.separator + "images";
	private static final String BACKGROUND_IMAGES_DIR = IMAGES_DIR + File.separator + "background";
	private static final String MORE_GAMES_BUTTON_IMAGES_DIR = IMAGES_DIR + File.separator + "moregamesbutton";
	private static final String PLAY_FREE_IMAGES_DIR = IMAGES_DIR + File.separator + "playfree";
	private static final String TRY_FREE_IMAGES_DIR = IMAGES_DIR + File.separator + "tryfree";
	private static final String GAMES_DIR = WORKSPACE_DIR + File.separator + "place_games_here";

	// ===========================================================
	// Fields
	// ===========================================================
	private String mName;
	private String mLocation;

	private Deployment mDeployment;
	private UIConfiguration mUIConfiguration;
	private ArrayList<Game> mGames;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Project(String pName, String pLocation) {
		this.mName = pName;
		this.mLocation = pLocation;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public String getName() {
		return mName;
	}

	public void setName(String pName) {
		mName = pName;
	}

	public String getLocation() {
		return mLocation;
	}

	public void setLocation(String pLocation) {
		mLocation = pLocation;
	}

	public Deployment getDeployment() {
		return mDeployment;
	}

	public void setDeployment(Deployment pDeployment) {
		mDeployment = pDeployment;
	}

	public UIConfiguration getUIConfiguration() {
		return mUIConfiguration;
	}

	public void setUIConfiguration(UIConfiguration pUIConfiguration) {
		mUIConfiguration = pUIConfiguration;
	}

	public ArrayList<Game> getGames() {
		return mGames;
	}

	public void setGames(ArrayList<Game> pGames) {
		mGames = pGames;
	}

	public String getXMLConfigurationsPath() {
		return this.mLocation + File.separator + XML_CONFIG_DIR;
	}

	public String getBackgroundImagePath() {
		return this.mLocation + File.separator + BACKGROUND_IMAGES_DIR;
	}

	public String getMoreGamesButtonImagePath() {
		return this.mLocation + File.separator + MORE_GAMES_BUTTON_IMAGES_DIR;
	}

	public String getPlayFreeImagePath() {
		return this.mLocation + File.separator + PLAY_FREE_IMAGES_DIR;
	}

	public String getTryFreeImagePath() {
		return this.mLocation + File.separator + TRY_FREE_IMAGES_DIR;
	}

	public String getGameImportPath() {
		return this.mLocation + File.separator + GAMES_DIR;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public void addGame(Game pGame) {
		if (mGames == null) {
			mGames = new ArrayList<Game>();
		}
		if (!mGames.contains(pGame)) {
			mGames.add(pGame);
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
