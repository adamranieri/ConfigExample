package dev.ranieri.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import dev.ranieri.entities.Associate;
import dev.ranieri.services.AssociateService;

@Component
@Controller // makes the class a singleton denotes that this is a controller thus designed to handle
// incoming http requests
@CrossOrigin("*") // should enable anyone to use these end points. No cors regulations
public class AssociateController {
	
	@Autowired
	AssociateService as;
	
	// You want your restful web services to suppport at minimum the basic crud operations
	// associates is the resource
	
	@Value("${me.name}")
	private String trainer;
	
	@RequestMapping(value = "/name", method = RequestMethod.GET)
	@ResponseBody // says that this method does not return an html view
	public String getName() {
		return this.trainer;
	}

	//getting all associates
	@RequestMapping(value = "/associates", method = RequestMethod.GET)
	@ResponseBody // says that this method does not return an html view
	// indicates we will be sending back information in JSON, or xml or plain text
	public List<Associate> getAllAssociates(){	
		return as.getAllAssociates();
	}
	
	//creating a new associates
	@ResponseBody
	@RequestMapping(value ="/associates", method = RequestMethod.POST)
	public Associate createAssociate(@RequestBody Associate associate) {	
		// at request body will parse the body of the incoming request into that paramter
	
		
		
		return as.createAssociate(associate);
	}
	
	// getting a single associate
	@ResponseBody
	@RequestMapping(value = "/associates/{id}", method = RequestMethod.GET)
	public Associate getAssociateById(@PathVariable int id) {
		// will read the id value in the uri
		System.out.println("the id is : " + id);
		try {
			return as.getAssociateById(id);
		}catch(NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find associate");
		}
		
	}
	
	// updating an associate
	@ResponseBody
	@RequestMapping(value = "/associates", method = RequestMethod.PUT)
	public Associate updateAssociate(@RequestBody Associate associate) {
		return as.updateAssociate(associate);
	}
	
	// delete
	@ResponseBody
	@RequestMapping(value = "/associates", method = RequestMethod.DELETE)
	public Boolean deleteAssociate(@RequestBody Associate associate) {
		boolean result = as.deleteAssociate(associate);
		
		if(result == false) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Did not delete");
		}
		
		return result;
	}
	
	// querying is using the ? in the uri
	// You can query a resource and expect to get back 0 to many results
	
	@ResponseBody
	@RequestMapping(value = "/query/associates", method = RequestMethod.GET)
	public List<Associate> query(@RequestParam int min, @RequestParam int max, @RequestParam(required = false)  Integer maxresults ){
		System.out.println(maxresults);
		String s = "";
		
		return as.findAssociatesInPointRange(min, max);	
	}
	
}
