package Practice.AppiumFramework;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="inputData")
	public Object[][] nameFieldData() {
		
		Object[][] obj = new Object[][]
				{{"Hello"}, {"*#!@^&"}};
				
				return obj;
	}


}
