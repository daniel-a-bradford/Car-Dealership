package com.cardealer.entity;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileReadWrite {
	/**
	 * Method appendToFile appends the stringToWrite to the end of the outputFile.
	 * If the file is not a valid file, it displays an access error on the console
	 * and returns false. If the append operation does not succeed, it displays a
	 * failed to append error and returns false. If successful, it returns true. a
	 * 
	 * @param outputFile
	 * @param stringToWrite
	 * @return
	 */
	public static boolean appendToFile(File outputFile, String stringToWrite) {
		if (outputFile.isFile()) {
			try {
				// Set the FileWriter to append mode using true in the constructor
				FileWriter outputFileWriter = new FileWriter(outputFile.getName(), true);
				outputFileWriter.write(stringToWrite);
				outputFileWriter.close();
				return true;
			} catch (IOException e) {
				System.out.println("Failed to append " + stringToWrite + " to the file: " + outputFile.getName());
				e.printStackTrace();
				return false;
			}
		} else {
			System.out.println("Failed to create output file.");
		}
		return false;
	}

	/**
	 * Method writeToFile overwrites the contents of the outputFile with the
	 * stringToWrite. If the file is not a valid file, it displays an access error
	 * on the console and returns false. If the write operation does not succeed, it
	 * displays a failed to write error and returns false. If successful, it returns
	 * true. a
	 * 
	 * @param outputFile
	 * @param stringToWrite
	 * @return
	 */
	public static boolean writeToFile(File outputFile, String stringToWrite) {
		if (outputFile.isFile()) {
			try {
				// The default FileWriter constructor overwrites the contents of the file.
				FileWriter outputFileWriter = new FileWriter(outputFile.getName());
				outputFileWriter.write(stringToWrite);
				outputFileWriter.close();
				return true;
			} catch (IOException e) {
				System.out.println("Failed to write " + stringToWrite + " to the file: " + outputFile.getName());
				e.printStackTrace();
				return false;
			}
		} else {
			System.out.println("Failed to access output file.");
		}
		return false;
	}

	/**
	 * Method readFileToStringList reads the text file specified by fileName. It
	 * stores each line as a string and returns an ArrayList of these Strings.
	 * 
	 * @param fileName
	 * @return
	 */
	public static ArrayList<String> readFileToStringList(String fileName) {

		ArrayList<String> inputStringList = new ArrayList<String>();
		try {
			inputStringList = (ArrayList<String>) Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		}
		// If there is a problem reading the file, return a blank List.
		catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
		// Loop through the list of strings, trimming white space and removing blank
		// strings.
		for (int index = 0; index < inputStringList.size(); index++) {
			inputStringList.set(index, inputStringList.get(index).trim());
			if (inputStringList.get(index).isBlank()) {
				inputStringList.remove(index);
			}
		}
		return inputStringList;
	}

	public static File createOutputFile(String outputFileName) {
		File outputFile = new File(outputFileName);
		try {

			String tempFileName = "";
			int fileSuffix = 1;
			boolean goodFile = outputFile.createNewFile();
			while (!goodFile) {
				System.out.print("File " + outputFileName + " already exists. ");
				fileSuffix++;
				// If the filename contains a period it likely has a file extension.
				if (outputFileName.contains(".")) {
					// Add the fileSuffix number before the file extension.
					int periodIndex = outputFileName.lastIndexOf(".");
					tempFileName = outputFileName.substring(0, periodIndex) + fileSuffix
							+ outputFileName.substring(periodIndex);
				} else {
					// No file extension, add the number to the end.
					tempFileName = outputFileName + fileSuffix;
				}
				System.out.println("Trying " + tempFileName);
				outputFile = new File(tempFileName);
				goodFile = outputFile.createNewFile();
			}
			System.out.println(outputFile.getName() + " created.");
		} catch (IOException e) {
			System.out.println("An file creation error occurred.");
			e.printStackTrace();
			return null;
		}
		return outputFile;
	}

}
