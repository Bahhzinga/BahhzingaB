package net.bahhzinga.org.backend.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.bahhzinga.org.backend.enums.OutputType;
import net.bahhzinga.org.backend.utils.Console;

public class FileManager {
	
	// Create file
	public static void createFile(String path, String name, Boolean readOnly) {
		
		// Instantiate File class
		File file = new File(path, name);
		
		// Check if file already exists
		if (!file.exists()) {
			try {
				// Create file and notify console
				file.createNewFile();
				Console.print(OutputType.NORMAL, "Created new file by the name of '" + name + "' in directory '" + path + "'.");
				if (file.canWrite() == false) {
					// Notify console
					Console.print(OutputType.NORMAL, "File by the name of '" + name + "' is now Read Only.");
				}
			} catch (IOException e) {
				// Notify console of error
				Console.print(OutputType.ERROR, "Attempted to create new file and failed, please send the error below to the developer, if the problem persists.");
				e.printStackTrace();
			}
			file.setReadable(readOnly);
		} else {
			// Notify console of error
			Console.print(OutputType.ERROR, "Attempted to create new file and failed, a file by that name already exists.");
		}
		
	}
	
	// Delete file
	public static void deleteFile(String path, String name) {
		
		// Instantiate File class
		File file = new File(path, name); 
		
		// Check if file exists
		if (file.exists()) {
			
			// If file is read only, make it writable.
			if (file.canWrite()) {
				file.setReadable(true);
				file.setWritable(true);
			}
			
			file.delete();
			
			// Notify console
			Console.print(OutputType.NORMAL, "Deleted file by the name of '" + name + "' in directory '" + path + "'.");
			
		}
	}
	
	// Set Read Only
	public static void setReadOnly(String path, String name, Boolean bool) {
		
		// Instantiate File class
		File file = new File(path, name);
		
		file.setReadable(bool);
		Console.print(OutputType.NORMAL, "File by the name of '" + name + "' is now Read Only.");
	}
	
	// Set Write Only
	public static void setWriteOnly(String path, String name, Boolean bool) {
		
		// Instantiate File class
		File file = new File(path, name);
		
		file.setWritable(bool);
		Console.print(OutputType.NORMAL, "File by the name of '" + name + "' is now Writable.");
	}
	
	// Get File
	public static File getFile(String path, String name) {
		
		// Instantiate File class
		File file = new File(path, name);
				
		return file;
		
	}
	
	// Get YML
	public static FileConfiguration getYML(File file) {
		
		FileConfiguration yml = YamlConfiguration.loadConfiguration(file);
		return yml;
		
	}
	
	// Save YML
	public static void save(String path, String name) {
		File file = new File(path, name);
		try {
			Console.print(OutputType.NORMAL, "File by the name of '" + name + "' has been saved.");
			getYML(file).save(file);
		} catch (IOException e) {
			Console.print(OutputType.ERROR, "Attempted to save file and failed.");
			e.printStackTrace();
		}
	}

	
	

}
