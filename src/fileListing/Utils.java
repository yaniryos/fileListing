package fileListing;

import java.io.File;

public class Utils {

	protected static final double bytesInMb = 1024;
	
	public static void print(File fileToPrint) {

		System.out.println(String.format("%-20s %-55s %f MB",fileToPrint.getName(), 
																fileToPrint.getAbsolutePath(),
																	fileToPrint.length()/bytesInMb));
	}	
}
