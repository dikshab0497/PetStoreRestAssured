package api.dataproviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import api.utilities.XLUtility;

public class UserDataProvider {
	
	@DataProvider(name = "userData")
	public Object[][] getUserData() {
	    return new Object[][] {
	        {"abc", "Mina", "Kale", "mina@gmail.com"},
	        {"xyz", "John", "Doe", "john@example.com"} // another user
	    };
	}
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl=new XLUtility(path);
	
		int rownum=xl.getRowCount("Sheet1");	
		int colcount=xl.getCellCount("Sheet1",1);
		
		String apidata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{		
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]= xl.getCellData("Sheet1",i, j);  
			}
		}
	
		return apidata;
	}
}
