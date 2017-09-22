package com.inflectra.remotelaunch;

import com.inflectra.remotelaunch.services.SpiraImportExport.TestCaseStatus;

public class TestResult
{
	public TestResult()
	{
		this.executionStatus = TestCaseStatus.NotRun;
		this.runnerTestName = "";
		this.runnerAssertCount = 0;
		this.runnerMessage = "";
		this.runnerStackTrace = "";
	}

	public TestResult(TestCaseStatus executionStatus, String runnerTestName,
			int runnerAssertCount, String runnerMessage, String runnerStackTrace)
	{
		this.executionStatus = executionStatus;
		this.runnerTestName = runnerTestName;
		this.runnerAssertCount = runnerAssertCount;
		this.runnerMessage = runnerMessage;
		this.runnerStackTrace = runnerStackTrace;
	}
	
	private TestCaseStatus executionStatus;
	private String runnerTestName;
	private int runnerAssertCount;
	private String runnerMessage;
	private String runnerStackTrace;
	public TestCaseStatus getExecutionStatus()
	{
		return executionStatus;
	}
	public void setExecutionStatus(TestCaseStatus executionStatus)
	{
		this.executionStatus = executionStatus;
	}
	public String getRunnerTestName()
	{
		return runnerTestName;
	}
	public void setRunnerTestName(String runnerTestName)
	{
		this.runnerTestName = runnerTestName;
	}
	public int getRunnerAssertCount()
	{
		return runnerAssertCount;
	}
	public void setRunnerAssertCount(int runnerAssertCount)
	{
		this.runnerAssertCount = runnerAssertCount;
	}
	public String getRunnerMessage()
	{
		return runnerMessage;
	}
	public void setRunnerMessage(String runnerMessage)
	{
		this.runnerMessage = runnerMessage;
	}
	public String getRunnerStackTrace()
	{
		return runnerStackTrace;
	}
	public void setRunnerStackTrace(String runnerStackTrace)
	{
		this.runnerStackTrace = runnerStackTrace;
	}
	
	
}
