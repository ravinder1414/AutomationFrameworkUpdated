package SpiraTest;

import automationFramework.LocalTC;
import utility.Constant;

public class SpiraWriter {
	
	
	
	public String buildUrl(String type, LocalTC obj )
	{
		String requestURL = null;
		switch(type)
		{
			case "testcase":
				requestURL = new String(Constant.TestCasesInSet); 
				break;
			case "teststep":
				requestURL = new String(Constant.TestSteps);
				break;
			case "project":
				requestURL = new String(Constant.ProjectDetail);
				break;
			case "release":
				requestURL = new String(Constant.ReleaseDetail);
				break;
			case "testrun":
				requestURL = new String(Constant.TestRun);
				break;
			case "status":
				requestURL = new String(Constant.TestStatus);
				break;
		}
		requestURL = requestURL.replace("{project_id}", Integer.toString(obj.getProjectID()));
		requestURL = requestURL.replace("{test_case_id}", Integer.toString(obj.getTestCaseID()));
		requestURL = requestURL.replace("{test_set_id}", Integer.toString(obj.getTestSetID()));
		requestURL = requestURL.replace("{release_id}", Integer.toString(obj.getReleaseID()));
		requestURL = requestURL + Constant.ServiceUser;
		System.out.println("Build URL: " + requestURL );
		return requestURL;
	}

}
