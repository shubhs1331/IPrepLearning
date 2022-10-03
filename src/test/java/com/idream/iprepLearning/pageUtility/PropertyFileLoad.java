package com.idream.iprepLearning.pageUtility;

import java.io.File;
import java.util.Properties;

public class PropertyFileLoad {
	
	public static Properties prop;
	
	static {
		try {
			File file = new File("src"+File.separator+"test"+File.separator+"resources"
		+File.separator+"config.properties");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
