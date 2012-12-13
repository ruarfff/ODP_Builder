package com.trust5.odpbuilder.utility;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @author Ruairi
 * @version 1.0
 * @since 07/12/12 - 12:27
 */
public final class FileUtils {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "FileUtils";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	private FileUtils() {
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

	public static void copy( File source, File destination ) throws IOException {
		if( source.isDirectory() ) {
			copyDirectory( source, destination );
		} else {
			copyFile( source, destination );
		}
	}

	public static void copyDirectory( File source, File destination ) throws IOException {
		if( !source.isDirectory() ) {
			throw new IllegalArgumentException( "Source (" + source.getPath() + ") must be a directory." );
		}

		if( !source.exists() ) {
			throw new IllegalArgumentException( "Source directory (" + source.getPath() + ") doesn't exist." );
		}

		if(! destination.exists() ) {
			destination.mkdirs();
		}

		File[] files = source.listFiles();

		for( File file : files ) {
			if( file.isDirectory() ) {
				copyDirectory( file, new File( destination, file.getName() ) );
			} else {
				copyFile( file, new File( destination, file.getName() ) );
			}
		}
	}

	public static void copyFile( File source, File destination ) throws IOException {
		FileChannel sourceChannel = new FileInputStream( source ).getChannel();
		FileChannel targetChannel = new FileOutputStream( destination ).getChannel();
		sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
		sourceChannel.close();
		targetChannel.close();
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
