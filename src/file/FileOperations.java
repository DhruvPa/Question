package file;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import exception.CustomException;

public class FileOperations {
	
	private String FilePath;
	
	private static FileOperations fileOperation= null;
	
	private FileOperations(String filePath) {
		super();
		FilePath = filePath;
	}



	public String getFilePath() {
		return FilePath;
	}
	
	public static FileOperations FileOperations(String FilePath) 
    { 
        // To ensure only one instance is created 
        if (fileOperation == null) 
        { 
        	fileOperation = new FileOperations(FilePath); 
        } 
        return fileOperation; 
    } 


	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

	public Long getNoOfLinesInFile() throws CustomException {
		Long count = 0L;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.FilePath))){
			String input;
		     while((input = bufferedReader.readLine()) != null)
		     {
		         count++;
		     }
		     System.out.println("Count : "+count);
		     
		} catch (IOException e) {
			throw new CustomException("Error occured while executing file operations, :: \n" + e);
		}
		
		return count;
	}
	

	public String getContentAtLine(Long offset) throws CustomException {
		String values=null;
		try (Stream<String> lines = Files.lines(Paths.get(this.FilePath))) {
			values = lines.skip(offset).findFirst().get();
			} catch (IOException e) {
				throw new CustomException("Could not find line at pos : " + offset +"\n" + e);
			}
		return values;
	}
}
