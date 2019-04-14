package org.koushik.javabrains.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Email {
	private Long no;
	private String name;
	private String surname;
	private Date DOB;

	public Email(){
			
		}
	public Email(Long no1, String name1, String surname1){
			this.no = no1;
			this.name = name1;
			this.surname = surname1;
			this.DOB = new Date();
		}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
}

