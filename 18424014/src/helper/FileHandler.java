package helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
	public static List<String> readAllLine(String fileName) throws IOException {
		List<String> list = new ArrayList<>();
		File file = new File(fileName);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String readLine = "";
		try {
			while ((readLine = bufferedReader.readLine()) != null){
				list.add(readLine);
			}
			return list;
		}
		finally {
			if(bufferedReader != null)
				bufferedReader.close();
		}
	}
	public static void writeToFile(String content, String fileName, boolean flag) throws IOException {
		BufferedWriter bufferedWriter =null;
		FileWriter fileWriter = null;
		File file = new File(fileName);
		if(!file.exists()) {
			file.createNewFile();
		}
		try {
			fileWriter = new FileWriter(file,flag);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(new String(content + "\n"));
		}finally {
			if(bufferedWriter != null) {
				bufferedWriter.close();
			}
			if(fileWriter != null) {
				fileWriter.close();
			}
		}
	}	
}
