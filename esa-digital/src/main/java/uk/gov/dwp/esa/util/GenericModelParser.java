package uk.gov.dwp.esa.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import uk.gov.dwp.esa.constants.PropertyFileEnum;


@Component
public class GenericModelParser {
	
	private String location;
	
	public void parseModel(Model model){
		Properties claimantProperties = new Properties();
		try {
			claimantProperties.load(GenericModelParser.class.getClassLoader().getResourceAsStream(location));
			Set<Entry<Object, Object>> dummy = claimantProperties.entrySet();
			Map<String,String> objMap = new HashMap<String,String>();
			for(Entry<Object, Object> temp : dummy){
				objMap.put((String)temp.getKey(), (String) temp.getValue());
			}
			
			model.addAttribute(PropertyFileEnum.GENERIC_CONTENT.value(),objMap);
			claimantProperties.clear();
		
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
			
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
