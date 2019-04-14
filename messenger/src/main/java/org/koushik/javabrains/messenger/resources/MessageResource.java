package org.koushik.javabrains.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.resources.beans.MessageFilterBean;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	 MessageService messageService = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJSONMassages(@BeanParam MessageFilterBean filterBean) {
		System.out.println("This is JSON");
		if(filterBean.getYear()>0) {
		return messageService.getAllMessageForYear(filterBean.getYear());
		}
		if(filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getXMLMassages(@BeanParam MessageFilterBean filterBean) {
		System.out.println("This is XML");
		if(filterBean.getYear()>0) {
		return messageService.getAllMessageForYear(filterBean.getYear());
		}
		if(filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message newMessage = messageService.addMessages(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		         return Response.created(uri)
								.entity(newMessage)
								.build();
	}
	
	
	/*@POST
	public Message addMessage(Message message) {
		return messageService.addMessages(message);
	}*/
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id ,Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("{messageId}")
	public void removeMessage(@PathParam("messageId") long id) {
		messageService.removeMassage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	@Path("/{messageId}")
	@GET
	public Message getmessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessages(id);
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComment(uriInfo, message), "commnet");
		return message;
	}

	private String getUriForComment(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getAbsolutePathBuilder()
		          .path(MessageResource.class)
		          .path(MessageResource.class, "getCommentResource")
		          .path(CommentResource.class)
		          .resolveTemplate("messageId", message.getId())
		          .build();
		return uri.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getAbsolutePathBuilder()
					          .path(ProfileResource.class)
					          .path(message.getAuthor())
					          .build();
		return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getAbsolutePathBuilder()
							.path(MessageResource.class)
							.path(Long.toString(message.getId()))
							.build()
							.toString();
		return uri;
	}
}
