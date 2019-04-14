package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import org.koushik.javabrains.messenger.model.Email;

public class EmailService {

	public List<Email> getAllEmails() {
		Email e1 = new Email(1L, "Darshan", "Khandelwal");
		Email e2 = new Email(2L, "Prasad", "Pendke");
		Email e3 = new Email(3L, "Ankush", "Shirbhate");
		Email e4 = new Email(4L, "Pratik", "Paithankar");
		Email e5 = new Email(5L, "Ankit", "Jain");
		
		List<Email> list = new ArrayList<Email>();
		list.add(e1);
		list.add(e2);
		list.add(e3);
		list.add(e4);
		list.add(e5);
		return list;
	}
}
