package com.api.mail.manag.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* les attributs correspondent les Clés des e-mails*/

@Entity(name = "ent_Info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {

	private static final long serialVersionUID = -6833354013338112648L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", length = 100)
	private String title;

	@Column(name = "firstName", length = 20)
	private String firstName;

	@Column(name = "lastName", length = 20)
	private String lastName;

	@Column(name = "phone", length = 15)
	private String phone;

	@Column(name = "emailFrom", length = 25)
	private String emailFrom;

	@Column(name = "employee")
	private Boolean employee; // client salarié ou non
}
