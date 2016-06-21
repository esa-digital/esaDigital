package uk.gov.dwp.esa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;


@Component
public class GenericModelParser {
	
	private String location;
	
	public void parseModel(Model model){
		Properties claimantProperties = new Properties();
		try {
			FileInputStream in = new FileInputStream(location);
			claimantProperties.load(in);
			in.close();
			Set<Entry<Object, Object>> dummy = claimantProperties.entrySet();
			Map<String,String> objMap = new HashMap<String,String>();
			for(Entry<Object, Object> temp : dummy){
				objMap.put((String)temp.getKey(), (String) temp.getValue());
			}
			model.addAllAttributes(objMap);
		
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
