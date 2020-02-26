package com.ing.testcase.farmshop.farmshop;

import com.ing.testcase.farmshop.farmshop.entities.Flocks;
import com.ing.testcase.farmshop.farmshop.util.CommonUtills;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;


@SpringBootApplication
public class FarmshopApplication {

	public static void main(String[] args) throws  Exception{

		String fileName = "Inputflock.xml";
		ClassLoader classLoader = new FarmshopApplication().getClass().getClassLoader();
		File fXmlFile = new File(classLoader.getResource(fileName).getFile());
		System.out.println("File Found : " + fXmlFile.exists());

		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		//Build Document
		Document document = builder.parse(fXmlFile);

		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		//Here comes the root node
		Element root = document.getDocumentElement();
		document.getDocumentElement().getNextSibling();
		System.out.println(document.getDocumentElement().getNextSibling());
		System.out.println(root.getNodeName());

		CommonUtills commonUtills = new CommonUtills();
		Flocks flocks=commonUtills.extracted();
		SpringApplication.run(FarmshopApplication.class, args);
	}

}
