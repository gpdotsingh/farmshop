package com.ing.testcase.farmshop.farmshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public final class Flock {
	@JacksonXmlProperty(localName = "name")
	private String name;
	@JacksonXmlProperty(localName = "sex")
	private String sex;
	@JacksonXmlProperty(localName = "wool")
	private Integer wool;
	@JacksonXmlProperty(localName = "type")
	private String type;

}
