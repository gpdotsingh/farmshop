package com.ing.testcase.farmshop.farmshop.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@JacksonXmlRootElement(localName = "flocks")
@Data
public final class Flocks {
	@JacksonXmlElementWrapper(localName = "flock", useWrapping = false)
	private List<Flock> flock;
}
