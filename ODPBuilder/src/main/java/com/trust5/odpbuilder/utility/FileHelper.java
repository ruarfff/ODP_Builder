package com.trust5.odpbuilder.utility;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @author Ruairi
 * @version 1.0
 * @since 07/12/12 - 12:27
 */
public final class FileHelper {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "FileHelper";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	private FileHelper() {
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
	public static void writeStringToFile(String filePath, String text){
		FileOutputStream fileOutputStream = null;
		try{
			fileOutputStream = new FileOutputStream(filePath);
			new PrintStream(fileOutputStream).println (text);
		} catch (Exception e){
			System.err.println (TAG + " - Unable to write to file");
		} finally {
			try{
				if(fileOutputStream != null){
					fileOutputStream.close();
				}
			}catch (Exception ignore){}
		}
	}

	public static String readStringFromFile(String filePath){
		FileInputStream fileInputStream = null;
		String result = null;
		try{
			fileInputStream = new FileInputStream(filePath);
			Scanner scanner = new Scanner(fileInputStream);

			result = scanner.toString();
		} catch (Exception e){
			System.err.println (TAG + " - Unable to read from file");
		} finally {
			try{if(fileInputStream != null) fileInputStream.close();}catch (Exception ignore){}
		}
		return result;
	}

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if(!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		}
		finally {
			if(source != null) {
				source.close();
			}
			if(destination != null) {
				destination.close();
			}
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
