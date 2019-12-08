package fileListing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileSystem {

	protected static File myDir;
	protected static String myPath;
	protected static String mySubString;
	protected static ArrayList<File> dirsToCheck = new ArrayList<File>();
	
	public FileSystem(){
		
		super();
	}
	
	public void startFileSearch(){
		
		System.out.println("Hello. Please input a path and a string");

		getPathFromUser();
		getSubStringFromUser();
		searchAndPrint();
	}
	
	private static void getPathFromUser() {

		try {
			myPath = MyReader.reader.readLine();
			checkPathValid();
			dirsToCheck.add(myDir);
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
	}

	private static void getSubStringFromUser() {

		try {
			mySubString = MyReader.reader.readLine();
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
					checkIfFileMatchesAndPrint(dirsToCheck.get(i).listFiles()[j]);
				else
					addDirectoryToList(myDir.listFiles()[j]);
			}
		}
	}

	private static void checkIfFileMatchesAndPrint(File fileToCheck) {

		if (fileToCheck.getName().indexOf(mySubString) != -1)
			Utils.print(fileToCheck);
	}

	private static void addDirectoryToList(File dirToAddToList) {
		
		dirsToCheck.add(dirToAddToList);
	}
}
