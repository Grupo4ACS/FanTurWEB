package grupo4.FanTurWEB.ctrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import grupo4.FanTurWEB.model.dao.interfaces.AdminDao;
//import grupo4.FanTurWEB.ws.Inject;

@Named("mailConfirUsuarioBean")
@ViewScoped
public class MailConfirUsuarioCtrl implements Serializable {
 
	/*public static void main(String[] args)
	{
	    new EmailWithHTMLTemplate().sendMailInJava();   
	}
	*/
	// Method to send an Email   
	
	private String email;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private static final long serialVersionUID = 1L;
	
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
	           /*
	           recipient_mail_id.add(new InternetAddress("mansilla.alberto.23@gmail.com"));
	           recipient_mail_id.add(new InternetAddress("nicoromero3210@gmail.com"));
	           recipient_mail_id.add(new InternetAddress("tuto_inxs@ca.frre.utn.edu.ar"));
	           */
	           
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
	            
	            //Set key values
	            Map<String, String> input = new HashMap<String, String>();
	               input.put("Author", "Grupo4 DACS");
	               input.put("Topic", "Usuario Registrado Correctamente");
	               input.put("Content In", "Español");
	               
	            String cabeza = "Estimado Usuario, con esta clave usted podrá completar el registro de su usuario en el Sistema de Viajes FanTur: ";
	            
	            String pie = "Muchas Gracias por elegirnos, somos Viajes FanTur";
	            
	            String texto = cabeza + '\n' +  '\n' + pie;
	            message.setText(texto);

	            //Conect to smtp server and send Email
	            System.out.println("Comenzando el envio del mail de usuario confirmado.."); 
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
}
