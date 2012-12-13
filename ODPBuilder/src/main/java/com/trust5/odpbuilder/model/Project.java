package com.trust5.odpbuilder.model;

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

    // ===========================================================
    // Fields
    // ===========================================================
	private String mName;
	private String mLocation;

    // ===========================================================
    // Constructors
    // ===========================================================
    public Project() {
    }

	public Project(String pName, String pLocation){
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

	// ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
