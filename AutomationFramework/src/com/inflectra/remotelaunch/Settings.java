package com.inflectra.remotelaunch;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.omg.CORBA.portable.ApplicationException;

/**
 * Singleton settings class
 * @author Inflectra Corporation
 *
 */
class Settings
{
	private static String serverUrl;
	private static String serverLogin;
	private static String serverPassword;
	private static String hostToken;
	
	//license information
	private static String licenseOrganization;
	private static String licenseKey;
	
	//regex strings
	private static String passRegex;
	private static String failRegex;
	private static String cautionRegex;
	private static String blockedRegex;

	/**
	 * Loads in properties from file
	 */
	static
	{
    	Properties prop = new Properties();
    	
    	try
    	{
    		//load the properties file, which needs to be in the same folder
    		//as the RemoteLaunchX.jar file
    	    String path = "./config.properties";
    	    FileInputStream stream = new FileInputStream(path);

    	    /* This was used when we had the .properties file inside the JAR file
    		InputStream stream = Program.class.getResourceAsStream("config.properties");
    		if (stream == null)
    		{
        		throw new SettingsNotFoundException("Unable to load settings file 'config.properties' so aborting.");
    		}*/
    		prop.load(stream);
 
    		//get the property values
            serverUrl = prop.getProperty("server-url");
    		serverLogin = prop.getProperty("server-login");
    		serverPassword = prop.getProperty("server-password");
    		hostToken = prop.getProperty("host-token");
    		
    		//license information
    		licenseOrganization = prop.getProperty("license-organization");
    		licenseKey = prop.getProperty("license-key");
    		
    		//regex values
    		passRegex = prop.getProperty("pass-regex");
    		failRegex = prop.getProperty("fail-regex");
    		cautionRegex = prop.getProperty("caution-regex");
    		blockedRegex = prop.getProperty("blocked-regex");
    	}
    	catch (IOException ex)
    	{
    		throw new SettingsNotFoundException("Unable to load settings file 'config.properties' so aborting.");
        }
	}
	
	/**
	 * Gets the server URL
	 * @return
	 */
	public static String getServerUrl()
	{
		return serverUrl;
	}

	/**
	 * Gets the server login
	 * @return
	 */
	public static String getServerLogin()
	{
		return serverLogin;
	}
	
	/**
	 * Gets the server password
	 * @return
	 */
	public static String getServerPassword()
	{
		return serverPassword;
	}

	/**
	 * Gets the automation host token
	 * @return
	 */
	public static String getHostToken()
	{
		return hostToken;
	}

	public static String getPassRegex()
	{
		return passRegex;
	}

	public static String getFailRegex()
	{
		return failRegex;
	}

	public static String getCautionRegex()
	{
		return cautionRegex;
	}

	public static String getBlockedRegex()
	{
		return blockedRegex;
	}

	public static String getLicenseOrganization()
	{
		return licenseOrganization;
	}

	public static void setLicenseOrganization(String licenseOrganization)
	{
		Settings.licenseOrganization = licenseOrganization;
	}

	public static String getLicenseKey()
	{
		return licenseKey;
	}

	public static void setLicenseKey(String licenseKey)
	{
		Settings.licenseKey = licenseKey;
	}
}
