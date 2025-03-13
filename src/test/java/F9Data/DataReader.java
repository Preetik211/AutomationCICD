package F9Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class DataReader {

	public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException
	{
		// read json to string 
		String jsonContent =  FileUtils.readFileToString(new File("C:\\Users\\keshr\\eclipse-workspace\\E25\\SeleniumFramework\\src\\test\\java\\F9Data\\PurchaseOrder.json")
				,StandardCharsets.UTF_8); // encoding format to convert to string
		
		// string to hash map
	    // dependecy: jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data;
		
		// {map, map1}
		// and now finally we have list of hash maps
	}
}
