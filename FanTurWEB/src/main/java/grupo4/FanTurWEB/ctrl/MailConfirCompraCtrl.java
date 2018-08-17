package grupo4.FanTurWEB.ctrl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import grupo4.FanTurWEB.model.dao.interfaces.AdminDao;
//import grupo4.FanTurWEB.ws.Inject;

@Named("mailCompraBean")
@ViewScoped
public class MailConfirCompraCtrl implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//@Inject
	//private AdminDao adminDao;
	@PostConstruct
	public void init() {
		
	}
	
	public void sendMailInJava()
	{
	 try {
	       
	           //Email data 
	           String Email_Id = "viajesfantur@gmail.com";        //change to your email ID
	           String password = "fantur2018";                 //change to your password
	           String mail_subject = "Usuario Registrado en Viajes FanTur!";
	           
	           
	           
	           //ArrayList<String> recipient_mail_id = new ArrayList<String>();
	           
	           ArrayList<InternetAddress> recipient_mail_id = new ArrayList<InternetAddress>();
	           recipient_mail_id.add(new InternetAddress(email));
	           
	           InternetAddress[] to = new InternetAddress[recipient_mail_id.size()];
	           to = recipient_mail_id.toArray(to);

	           //Set mail properties
	            Properties props = System.getProperties();
	            String host_name = "smtp.gmail.com";
	            props.put("mail.smtp.starttls.enable", "true");
	            props.put("mail.smtp.host", host_name);
	            props.put("mail.smtp.user", Email_Id);
	            props.put("mail.smtp.password", password);
	            props.put("mail.smtp.port", "587");
	            props.put("mail.smtp.auth", "true");
	 
	        Session session = Session.getDefaultInstance(props);
	        MimeMessage message = new MimeMessage(session);

	        	 try {
		        	 
	        			
		             message.setRecipients(Message.RecipientType.TO, to);
		            //Set email data 
		            message.setFrom(new InternetAddress(Email_Id));
		            //message.setRecipients(Message.RecipientType.TO, recipient_mail_id);
		            message.setSubject(mail_subject);
		            MimeMultipart multipart = new MimeMultipart();
		            BodyPart messageBodyPart = new MimeBodyPart();
		            
		            //Set key values
		            Map<String, String> input = new HashMap<String, String>();
		               input.put("Author", "Grupo4 DACS");
		               input.put("Topic", "Newsletter de Novedades");
		               input.put("Content In", "Español");
		             
		            //HTML mail content
		            String htmlText = readEmailFromHtml("C:\\Users\\monica\\git\\FanTurSalvavidas\\FanTurWEB\\WebContent\\paquetecomprado.html",input);
		            messageBodyPart.setContent(htmlText, "text/html");
		            
		            multipart.addBodyPart(messageBodyPart); 
		            message.setContent(multipart);
		 
		            //Conect to smtp server and send Email
		            System.out.println("Comenzando el envio del mail.."); 
		            Transport transport = session.getTransport("smtp");            
		            transport.connect(host_name, Email_Id, password);
		            transport.sendMessage(message, message.getAllRecipients());
		            transport.close();
		            System.out.println("Mail enviado correctamente..."); 
		         
		        }
		            catch (MessagingException ex) {
		                Logger.getLogger(EmailWithHTMLTemplate.class.getName()).log(Level.SEVERE, null, ex);
		            }        catch (Exception ae) {
		            ae.printStackTrace();
		        }    
		        }   
		        catch(Exception exception)
		        {
		            exception.printStackTrace();
		        }
		}
		//Method to replace the values for keys
		protected String readEmailFromHtml(String filePath, Map<String, String> input)
		{
		    String msg = readContentFromFile(filePath);
		    try
		    {
		    Set<Entry<String, String>> entries = input.entrySet();
		    for(Map.Entry<String, String> entry : entries) {
		        msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
		    }
		    }
		    catch(Exception exception)
		    {
		        exception.printStackTrace();
		    }
		    return msg;
		}
		//Method to read HTML file as a String 
		private String readContentFromFile(String fileName)
		{
		    StringBuffer contents = new StringBuffer();
		    
		    try {
		      //use buffering, reading one line at a time
		      BufferedReader reader =  new BufferedReader(new FileReader(fileName));
		      try {
		        String line = null; 
		        while (( line = reader.readLine()) != null){
		          contents.append(line);
		          contents.append(System.getProperty("line.separator"));
		        }
		      }
		      finally {
		          reader.close();
		      }
		    }
		    catch (IOException ex){
		      ex.printStackTrace();
		    }
		    return contents.toString();
		}
	}