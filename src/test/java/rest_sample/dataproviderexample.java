package rest_sample;

import org.testng.annotations.DataProvider;

public class dataproviderexample {
	
	
	
	@DataProvider(name="apitest")
	public Object[][] data(){
		Object[][] data = new Object[2][3];
		
		data[0][0] = "address1";
		data[0][1]="city";
		data[0][2]="employeename";
		
		data[1][0] = "address2";
		data[1][1]="city2";
		data[1][2]="employeename2";
		
		return data;
		
	}

}
