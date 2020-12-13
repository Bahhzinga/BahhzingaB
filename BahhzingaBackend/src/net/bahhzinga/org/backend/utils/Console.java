package net.bahhzinga.org.backend.utils;

import net.bahhzinga.org.backend.enums.OutputType;

public class Console {
	
	public static void print(OutputType type, String string) {
		
		switch (type) {
		case ERROR:
			System.out.println("[BahhzingaBackend] Error: " + string);
			break;
		case DEBUG:
			System.out.println("[BahhzingaBackend] Debug: " + string);
			break;
		case NORMAL:
			System.out.println("[BahhzingaBackend] " + string);
			break;
		case ALERT:
			System.out.println("[BahhzingaBackend] ALERT: " + string);
			break;
		case WARN:
			System.out.println("[BahhzingaBackend] Warning: " + string);
			break;
		}
		
	}

}
