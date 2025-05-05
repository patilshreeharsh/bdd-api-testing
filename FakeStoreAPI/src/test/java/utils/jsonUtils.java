package utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonUtils {
	private final static ObjectMapper mapper = new ObjectMapper();
	
public static JsonNode fileToNodeConverter(File file) throws IOException {
	 JsonNode JNode = mapper.readTree(file);
	return JNode;
	
}
public static JsonNode stringToNodeConverter(String str) throws IOException {
	 JsonNode JNode = mapper.readTree(str);
	return JNode;
	
}
//public static String jsonToString(JsonNode js) {
//	String expectedJson = new String(Files.readAllBytes(Paths.get(filePath)));
//	return null;
//	
//}
}
