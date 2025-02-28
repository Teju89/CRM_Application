package com.crm.generic.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Contact {

	private String firstName;
	private String lastName;
	private long mobile;
	private String orgName;
}
