package org.koushik.javabrains.messenger;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger.model.Email;
import org.koushik.javabrains.messenger.service.EmailService;

@Path("/emails")

public class MyEmail {
	
	EmailService emailservice = new EmailService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	
	public List<Email> getAllEmails(){
		
		return emailservice.getAllEmails();

	}
}
