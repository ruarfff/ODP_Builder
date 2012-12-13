package com.trust5.odpbuilder;

/**
 * @author Ruairi
 * @version 1.0
 * @since 07/12/12 - 14:41
 */
public class Constants {
	// ===========================================================
	// Constants
	// ===========================================================
	public static final String APP_NAME = "ODP_Builder";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	private Constants() {

	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public static synchronized Constants getInstance() {
		return SingletonHolder.instance;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	private static class SingletonHolder {
		public static final Constants instance = new Constants();
	}
}