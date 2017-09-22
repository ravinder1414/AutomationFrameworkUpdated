package com.inflectra.remotelaunch.services;

import com.inflectra.remotelaunch.services.soap.RemoteDocument;

/**
 * Stores the automation attachment information, the document meta-data and the file itself
 * @author Inflectra Corporation
 *
 */
public class AutomationAttachment
{
	public AutomationAttachment()
	{	
		this.remoteDocument = null;
		this.testScript = null;
	}
	
	public AutomationAttachment(RemoteDocument remoteDocument, byte[] testScript)
	{
		this.remoteDocument = remoteDocument;
		this.testScript = testScript;
	}
	private RemoteDocument remoteDocument;
	private byte[] testScript;
	
	public RemoteDocument getRemoteDocument()
	{
		return this.remoteDocument;
	}
	public void setRemoteDocument(RemoteDocument remoteDocument)
	{
		this.remoteDocument = remoteDocument;
	}
	public byte[] getTestScript()
	{
		return this.testScript;
	}
	public void setTestScript(byte[] testScript)
	{
		this.testScript = testScript;
	}
}
