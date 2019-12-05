package fileListing;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
	static File myDir;
	static String myPath;
	static String mySubString;
	static ArrayList<File> dirsToCheck = new ArrayList<File>();
	static final int mbInBytes = 1000000;

	private static void startProgramFlow() {

		System.out.println("Hello. Please input a path and a string");

		getPathFromUser();
		getSubStringFromUser();
		searchAndPrint();

	}

	private static void getPathFromUser() {

		try {
			myPath = myReader.readLine();
			checkPathValid();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void checkPathValid() {

		myDir = new File(myPath);
		if (!myDir.isDirectory() && !myDir.isFile()) {
			System.out.println("Invalid path!");
			System.exit(-1);
		}
		dirsToCheck.add(myDir);
	}

	private static void getSubStringFromUser() {

		try {
			mySubString = myReader.readLine();
			checkSubStringValid();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void checkSubStringValid() {

		if (mySubString.isEmpty()) {
			System.out.println("Invalid string!");
			System.exit(-1);
		}
	}

	private static void searchAndPrint() {

		for (int i = 0; i < dirsToCheck.size(); i++) {
			for (int j = 0; j < dirsToCheck.get(i).listFiles().length; j++) {

				if (!dirsToCheck.get(i).listFiles()[j].isDirectory())
					checkFile(dirsToCheck.get(i).listFiles()[j]);
				else
					addDirectoryToList(myDir.listFiles()[j]);
			}
		}
	}

	private static void checkFile(File fileToCheck) {

		if (fileToCheck.getName().indexOf(mySubString) != -1)
			printFileData(fileToCheck);
	}

	private static void addDirectoryToList(File dirToAddToList) {
		
		dirsToCheck.add(dirToAddToList);
	}

	private static void printFileData(File fileToPrint) {

		System.out.println(String.format("%-15s %-55s %d MB",fileToPrint.getName(), 
																fileToPrint.getAbsolutePath(),
																	fileToPrint.getTotalSpace()/mbInBytes));
	}

	public static void main(String[] args) {
		
		startProgramFlow();
	}
}
