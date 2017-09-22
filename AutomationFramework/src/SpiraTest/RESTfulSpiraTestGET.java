package SpiraTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


public class RESTfulSpiraTestGET 
{
	URL RequestURL;
	Proxy proxy;
	StringBuilder sb;
	static HttpURLConnection conn;
	int TestCasesID[];
	String TestSteps[];

	// https://impellam.spiraservice.net/Services/v4_0/RestService.svc/projects/75/test-sets?username=bharat.sethi&api-key={D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A}
	public static void main(String[] args) 
	{
		try {
			//HttpHeaders httpHeaders;
			//httpHeaders.get
			URL url = new URL("https://impellam.spiraservice.net/Services/v4_0/RestService.svc/projects/75/test-cases/16315?username=bharat.sethi&api-key={D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A}");
			StringBuilder sb = new StringBuilder();
			File f = new File("C:\\Impellam.txt");
			//URL url = new URL("http://www.google.com");
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.21.0.25", 8080));
			if (f.exists())
				    conn = (HttpURLConnection) url.openConnection(proxy);
				else
					conn = (HttpURLConnection) url.openConnection();
			//System.out.println("Proxy? " + conn.usingProxy());
			//Proxy instance, proxy ip = 10.21.0.80 with port 8080
			//  Object obj  = url.getContent();
			System.out.println("Got connected");
			//	conn.connect();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			System.out.println("Setting request property");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			System.out.println("reading input stream");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) 
			{
				sb.append(output + "\n");
				System.out.println(output);
			}
			//JSONArray jsonArray = new JSONArray(sb.toString());
			JSONObject jsonObj = new JSONObject(sb.toString()); 
			JSONArray jsonArray = jsonObj.getJSONArray("TestSteps");
			System.out.println("\n\nTestStepsArray: " + jsonArray);
			//JSONArray jsonArray = new JSONArray(sb.toString());
			for (int i = 0; i < jsonArray.length(); i++) 
			{  // **line 2**
				JSONObject childJSONObject = jsonArray.getJSONObject(i);
				//System.out.println("\n\nChildObject: " + childJSONObject);
				String TestStep = childJSONObject.getString("TestCaseId");
				System.out.println("\n\nTestStep: " + TestStep);
				//String TestExpected = childJSONObject.getString("ExpectedResult");
				//System.out.println("\n\nTestExpected: " + TestExpected);
				//int TestStepId = childJSONObject.getInt("TestStepId");
				//System.out.println("\n\nTestStepID: " + Integer.toString(TestStepId));
			}
			System.out.println("\n\njsonArray: " + jsonArray);
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

//Map<String, String> dictionary = new HashMap<String, String>();
//dictionary.put("key", "value");
//String value = dictionary.get("key");
//API Key: D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A
//https://impellam.spiraservice.net/Services/v4_0/RestService.svc/projects/75?username=bharat.sethi&api-key={D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A}
//https://impellam.spiraservice.net/Services/v4_0/RestService.svc/projects/75/test-sets?username=bharat.sethi&api-key={D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A}
//https://impellam.spiraservice.net/Services/v4_0/RestService.svc/projects/75/releases/587/test-cases?username=bharat.sethi&api-key={D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A}
//https://impellam.spiraservice.net/Services/v4_0/RestService.svc/projects/75/test-cases/16209?username=bharat.sethi&api-key={D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A}
