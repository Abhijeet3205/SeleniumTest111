package runner;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataUtil {
	
	public Map<String, String> loadClassMethods() throws IOException, ParseException
	{
		Map<String, String> classMethodsMap = new HashMap<String, String>();
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\jsons\\classmethods.json";
		FileReader reader = new FileReader(path);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(reader);
		JSONArray classDetails = (JSONArray) json.get("classdetails");
		for(int i =0; i<classDetails.size();i++) 
		{
			JSONObject classDetail = (JSONObject)classDetails.get(i);
			String className = (String) classDetail.get("class");
			JSONArray methods = (JSONArray) classDetail.get("methods");
			for(int y =0; y<methods.size();y++) 
			{
				String methodName = (String) methods.get(y);
				classMethodsMap.put(methodName, className);
			}
		}
		return classMethodsMap;
	}
	public int getTestDataSets(String filePath, String dataFlag) throws IOException, ParseException 
	{
		int dataSets=0;
		FileReader reader = new FileReader(filePath);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(reader);
		JSONArray dataDetails = (JSONArray) json.get("testdata");
		for(int dSetId=0;dSetId<dataDetails.size();dSetId++) 
		{
			JSONObject testData = (JSONObject) dataDetails.get(dSetId);
			String flag = (String) testData.get("flag");
			if(flag.equals(dataFlag)) 
			{
				JSONArray dataSet = (JSONArray) testData.get("data");
				dataSets=dataSet.size();
				return dataSets;
			}
		}
		
		return dataSets;
	}
	
	public int getTestDataSetsXls(String xlsFilePath, String flagName, String sheetName) 
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
		int TotalRows = dataEndRowNum - colNameRowNum;
		return TotalRows;
	}
	
	public JSONObject getTestData(String filePath, String dataFlag, int iteration) throws IOException, ParseException
	{
		int dataSets=0;
		FileReader reader = new FileReader(filePath);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(reader);
		JSONArray dataDetails = (JSONArray) json.get("testdata");
		for(int dSetId=0;dSetId<dataDetails.size();dSetId++) 
		{
			JSONObject testData = (JSONObject) dataDetails.get(dSetId);
			String flag = (String) testData.get("flag");
			if(flag.equals(dataFlag)) 
			{
				JSONArray dataSet = (JSONArray) testData.get("data");
				JSONObject data = (JSONObject) dataDetails.get(iteration);
				return data;
			}
		}
		return null;
	}

}
