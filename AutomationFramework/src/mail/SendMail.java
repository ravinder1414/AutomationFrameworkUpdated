package mail;


import java.util.Base64;

/**********************************************************************************
 * Aauthor 			:	mohammad.makki
 * Description      : 	Send Email of generated html report to the given email id/ids
 **********************************************************************************/


import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;

import utility.Constant;
import utility.Log;


public class SendMail {
	/**
	 * @param fromMail
	 * @param toMail
	 * @param authPassword
	 * @param smtpHost
	 * @param smtpPort
	 */
	public static void sendAttachmentEmail(String fromMail, String toMail, String authPassword, String smtpHost,
			String smtpPort) {
		try {
			if ((null != fromMail && !fromMail.isEmpty()) && (null != toMail && !toMail.isEmpty())
					&& (null != authPassword && !authPassword.isEmpty()) && (null != smtpHost && !smtpHost.isEmpty())
					&& (null != smtpPort && !smtpPort.isEmpty())) {
				Date date = new Date();
				String currentDate= new SimpleDateFormat("MM/dd/yyyy").format(date);
				
				String body = getBodyContent();
				/*String body = "Hi," + System.lineSeparator() + "Release ID: " + Constant.Vars.getReportReleaseId() + System.lineSeparator() + "Please find the attached report."
						+ System.lineSeparator() + "Thanks!";*/
				String subject = "Automation test report - Execution on " + currentDate;
				Properties props = System.getProperties();

				props.put("mail.smtp.host", smtpHost);
				props.put("mail.smtp.port", smtpPort); // TLS Port
				props.put("-Dmail.smtp.starttls.enable", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.auth", "true");
				props.put("mail.store.protocol", "SMTP");
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						String authPass = "";
						Pattern r = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$");
						Matcher encodeM = r.matcher(authPassword);
						while (encodeM.find()) {
							Log.info(encodeM.group(0));
							byte[] decodedBytes = Base64.getDecoder().decode(authPassword);
							authPass = new String(decodedBytes);
						}
						return new PasswordAuthentication(fromMail, authPass);
					}
				});
				MimeMessage message = new MimeMessage(session);
				message.addHeader("Content-type", "text/HTML; charset=UTF-8");
				message.addHeader("format", "flowed");
				message.addHeader("Content-Transfer-Encoding", "8bit");

				message.setFrom(new InternetAddress(fromMail));
				message.setSubject(subject, "UTF-8");
				message.setSentDate(new Date());
				InternetAddress[] emailArray = InternetAddress.parse(toMail);
				message.setRecipients(Message.RecipientType.TO, emailArray);
				// Create the message body part
				BodyPart messageBodyPart = new MimeBodyPart();
				// Fill the message
				messageBodyPart.setText(body);
				// Create a multipart message for attachment
				Multipart multipart = new MimeMultipart();
				// Set text message part
				multipart.addBodyPart(messageBodyPart);
				// Second part is attachment
				messageBodyPart = new MimeBodyPart();
				String filename = Constant.tempTestReportPath + "Detail\\" + Constant.Vars.getDetailReport() + ".html";
				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName("Report_" + Constant.Vars.getDetailReport() + ".html");
				multipart.addBodyPart(messageBodyPart);
				// Send the complete message parts
				message.setContent(multipart);
				// Send message
				Transport.send(message);
				System.out.println("EMail Sent Successfully with attachment!!");
			} else {
				Log.error("Send email failed.");
			}
		} catch (MessagingException e) {
			Log.error("Send email failed" + e.getMessage());
		}
	}

	/**
	 * @return
	 * body content of mail
	 */
	public static String getBodyContent() {
		String strBody = "";
		if(null != Constant.Vars){
		strBody = "Hi," + System.lineSeparator() + "Execution Start Time: \t\t"
				+ Constant.Vars.getExecutionStartTime() + System.lineSeparator() + "Execution End Time: \t\t"
				+ Constant.Vars.getExecutionEndTime() + System.lineSeparator() + "Total TestCase Executed: \t"
				+ (Constant.Vars.passed + Constant.Vars.failed + Constant.Vars.blocked) + System.lineSeparator()
				+ "Passed: \t\t\t" + Constant.Vars.passed + System.lineSeparator() + "Failed: \t\t\t\t"
				+ Constant.Vars.failed + System.lineSeparator() + "Blocked: \t\t\t" + Constant.Vars.blocked
				+ System.lineSeparator() + "Release ID: \t\t\tEVO " + Constant.Vars.getReportReleaseId()
				+ System.lineSeparator() + System.lineSeparator() + "Please find the attached report."
				+ System.lineSeparator() + System.lineSeparator() + "Thanks!";
		}

		return strBody;

	}

}
