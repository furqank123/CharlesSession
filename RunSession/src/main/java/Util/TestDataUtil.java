package Util;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class TestDataUtil {

    private static final String FILE_URL = "SessionData.json";
    private static final String CAPABILITIES = "capabilities";
    private static final String ANDROID = "android";

    public SessionData retrieveCapabilities(){
        return getDataFromJSON(CAPABILITIES, ANDROID,FILE_URL);
    }

    private String loadJSONFromResource(String fileName) {
        String json;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(fileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(resource.getPath()));
            StringBuilder stringBuilder = new StringBuilder();
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                stringBuilder.append(readLine);
                readLine = bufferedReader.readLine();
            }
            json = stringBuilder.toString();
        } catch (
                IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    private SessionData getDataFromJSON(String tag, String type,String file) {
        SessionData testData = new SessionData();
        Gson gson = new Gson();
        String data = loadJSONFromResource(file);
        try {
            JSONObject dataObject = new JSONObject(data);
            JSONObject objectTag = dataObject.getJSONObject(tag);
            String objectInfo = objectTag.optString(type);
            testData = gson.fromJson(objectInfo,SessionData.class);
        } catch(JSONException e){
            e.printStackTrace();
        }
        return testData;
    }
}
