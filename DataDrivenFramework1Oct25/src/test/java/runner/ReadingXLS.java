package runner;

import org.json.simple.JSONObject;

public class ReadingXLS {

	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\jsons\\xls_data\\excelForTesting.xlsx";
		
		Xls_Reader xls = new Xls_Reader(path);
		System.out.println(xls.getColumnCount("DealsSuite"));
		
	}
	public JSONObject getTestData(String sheetName, String flagName,  int iterationNumber, String xlsFilePath)
	{
		Xls_Reader xls = new Xls_Reader(xlsFilePath);
		int FlagRowNum = 0;
		for(int i =1; i<=xls.getRowCount(sheetName);i++) 
		{
			String cellData = xls.getCellData(sheetName, 0, i);
			if(cellData.equals(flagName)) 
			{
				FlagRowNum = i;
				break;
			}
		}
//		System.out.println("FlagRowNum: "+FlagRowNum);
		int colNameRowNum = FlagRowNum + 1;
		int dataStartRowNum = FlagRowNum + 2;
		int colCount = xls.getColumnCountByRowNum(sheetName, colNameRowNum);
//		System.out.println(colCount);
		int dataEndRowNum = 0;
		int z = dataStartRowNum;
		while(true) 
		{
			String celldata = xls.getCellData(sheetName, 0, z);
			if(celldata.equals("")) 
			{
				dataEndRowNum = z;
				break;
			}
			z++;
		}
		dataEndRowNum = dataEndRowNum - 1;
//		System.out.println("dataEndRowNum: " + dataEndRowNum);
//		int iterationNumber = 1;
		int index = 1;
		for(int i = dataStartRowNum;i<=dataEndRowNum;i++) 
		{
			if(index == iterationNumber) 
			{
				JSONObject json = new JSONObject();
				for(int y = 0; y<colCount;y++) 
				{
					String colName = xls.getCellData(sheetName, y, colNameRowNum);
//					System.out.println("colName "+colName);
					String cellData = xls.getCellData(sheetName, y, i);
//					System.out.println(cellData);
					System.out.println(colName + " - " + cellData);
					json.put(colName, cellData);
				}	
				return json;
//				break;
				
			}
			else 
			{
				index++;
			}
		}
		return new JSONObject();
	}

}
