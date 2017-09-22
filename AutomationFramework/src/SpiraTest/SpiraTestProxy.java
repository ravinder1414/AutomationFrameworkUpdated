package SpiraTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
//import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
//import javax.xml.ws.BindingProvider;

import com.inflectra.spirateam.mylyn.core.internal.services.soap.ArrayOfRemoteFilter;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.ArrayOfRemoteIncident;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.ArrayOfint;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.IImportExport;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.IImportExportConnectionAuthenticateServiceFaultMessageFaultFaultMessage;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.IImportExportIncidentCreateServiceFaultMessageFaultFaultMessage;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.IImportExportIncidentRetrieveServiceFaultMessageFaultFaultMessage;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.ImportExport;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.MultiValueFilter;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.ObjectFactory;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.RemoteFilter;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.RemoteIncident;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.RemoteSort;

public class SpiraTestProxy {
	
	private static final String WEB_SERVICE_SUFFIX = "/Services/v4_0/ImportExport.svc"; //$NON-NLS-1$ 
	private static final String WEB_SERVICE_NAMESPACE = "{https://impellam.spiraservice.net/Services/v4.0/}ImportExport"; //$NON-NLS-1$ 
	private static final String WEB_SERVICE_NAMESPACE_DATA_OBJECTS = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects"; //$NON-NLS-1$ 
	private static final String baseUrl = "https://impellam.spiraservice.net";
	private static final String userName = "bharat.sethi";
	private static final String password = "Password";
	private static final int projectId =75;
	static URL serviceUrl;
	static ImportExport service;
	static IImportExport soap;
	
	
	public static void connect() throws IImportExportConnectionAuthenticateServiceFaultMessageFaultFaultMessage, IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage, MalformedURLException
	    {
		    serviceUrl = new URL(baseUrl + WEB_SERVICE_SUFFIX); 
			service = new ImportExport(serviceUrl, QName.valueOf(WEB_SERVICE_NAMESPACE)); 
			IImportExport soap = service.getBasicHttpBindingIImportExport();
		 	boolean success = soap.connectionAuthenticate(userName, password);
		 	success = soap.connectionConnectToProject(projectId);
	    }
	
	 public static void createNewIncident() throws DatatypeConfigurationException
	 {
		 ObjectFactory  objectFactory = new ObjectFactory();
		 RemoteIncident remoteIncident = objectFactory.createRemoteIncident();
		 remoteIncident.setIncidentTypeId(objectFactory.createRemoteIncidentIncidentTypeId(113));
		 remoteIncident.setIncidentStatusId(objectFactory.createRemoteIncidentIncidentStatusId(113));
		 remoteIncident.setName(objectFactory.createRemoteIncidentName("Test incident name"));
		 remoteIncident.setDescription(objectFactory.createRemoteIncidentDescription("Test description"));
		 remoteIncident.setCreationDate(objectFactory.createRemoteIncidentCreationDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar( 1976, Calendar.DECEMBER, 22 ))));
		 try {
		       RemoteIncident i = soap.incidentCreate(remoteIncident);
		 } catch (IImportExportIncidentCreateServiceFaultMessageFaultFaultMessage e) {
		       // TODO Auto-generated catch block
		       e.printStackTrace();
		 }
	 }
	 
	 public void retriveIncident()
	 {
		// Create RemoteFilter
		 ObjectFactory  objectFactory = new ObjectFactory();
		 RemoteFilter filter1 = objectFactory.createRemoteFilter();
		 MultiValueFilter mvf = objectFactory.createMultiValueFilter();
		 ArrayOfint array1 = objectFactory.createArrayOfint();
		 array1.getInt().add(1);
		 array1.getInt().add(2);
		 array1.getInt().add(3);
		 array1.getInt().add(4);
		 mvf.setValues(objectFactory.createMultiValueFilterValues(array1));
		 filter1.setPropertyName(objectFactory.createRemoteIncidentDescription("PriorityId"));
		 filter1.setMultiValue(objectFactory.createMultiValueFilter(mvf));
		 ArrayOfRemoteFilter filters = objectFactory.createArrayOfRemoteFilter();
		 filters.getRemoteFilter().add(filter1);
		 // Create RemoteSort
		 RemoteSort sort = objectFactory.createRemoteSort();
		 sort.setPropertyName(objectFactory.createRemoteFilterPropertyName("Name"));
		 sort.setSortAscending(true);
		 try{
		       ArrayOfRemoteIncident incidents = soap.incidentRetrieve(filters, sort, 1, 10);
		 } catch (IImportExportIncidentRetrieveServiceFaultMessageFaultFaultMessage e) {
		       // TODO Auto-generated catch block
		       e.printStackTrace();
		 }
	 }
	 
	 public static void main(String[] args) throws MalformedURLException, IImportExportConnectionAuthenticateServiceFaultMessageFaultFaultMessage, IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage, DatatypeConfigurationException
	 {
		 connect();
		 createNewIncident();
	 }
}
