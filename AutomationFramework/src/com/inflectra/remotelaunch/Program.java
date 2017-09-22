package com.inflectra.remotelaunch;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import SpiraTest.SpiraReader;
import automationFramework.LocalTC;
import automationFramework.ReporterCommon;

//import com.inflectra.remotelaunch.License.LicenseTypeEnum;

/**
 * Main entry-point for the cross-platform RemoteLaunch-X application
 * 
 * @author Inflectra Corporation
 * @version 4.0
 * @since 8/29/2013
 * 
 */
public class Program
{
	/**
	 * Starts the RemoteLaunch-X application
	 * @param args The command-line args (use -h for help)
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		//Display the startup message
		LocalTC Vars;
		Vars= new LocalTC("88","135","173","23098","C/Temp/Testspira.xlsx","true");
		System.out.println("Starting RemoteLaunch...");
		System.out.println("========================");
		SpiraReader SpiraRead;
		SpiraRead = new SpiraReader();
		ReporterCommon reportCommonObj = new ReporterCommon();
		SpiraRead.ExtractTestSet(Vars, reportCommonObj);
		//Display the configuration settings
		System.out.println("Server URL: " + Settings.getServerUrl());
		System.out.println("Server Login: " + Settings.getServerLogin());
		System.out.println("Automation Host: " + Settings.getHostToken());
		//Check for scheduled test sets
		//Need current date/time in UTC
		Date plannedDate = new Date();
		try
		{
			TestScheduler scheduler = new TestScheduler(Settings.getServerUrl(), Settings.getServerLogin(), Settings.getServerPassword(), Settings.getHostToken());
			int testCount = scheduler.pollForTests(plannedDate);
			if (testCount > 0)
			{
				scheduler.executeTests();
			}
		}
		catch (Exception ex)
		{
			System.out.println("Error Executing Tests: " + ex.getLocalizedMessage());
		}


		System.out.println("Exiting RemoteLaunch...");
		System.out.println("========================");

	}
}
