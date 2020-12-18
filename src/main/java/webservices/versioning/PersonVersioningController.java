package webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	
	@GetMapping("v1/person")
	public PersonV1 person1() {
		return new PersonV1("Jack Karma");
	}
	
	@GetMapping("v2/person")
	public PersonV2 person2() {
		
		return new PersonV2(new Name("jack","karma"));
	}

	
	//params
	@GetMapping(value = "person/param", params = "version=1")
	public PersonV1 param1() {
		return new PersonV1("Jack Karma");
	}
	
	@GetMapping(value = "person/param", params = "version=2")
	public PersonV2 param2() {
		
		return new PersonV2(new Name("jack","karma"));
	}
	
	
	//headers
	@GetMapping(value = "person/header", headers = "X-API-VERSION=1")
	public PersonV1 header1() {
		return new PersonV1("Jack Karma");
	}
	
	@GetMapping(value = "person/param", params = "X-API-VERSION=2")
	public PersonV2 header2() {
		
		return new PersonV2(new Name("jack","karma"));
	}
	
	
	//produces
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 produces1() {
		return new PersonV1("Jack Karma");
	}
	
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 produces2() {
		
		return new PersonV2(new Name("jack","karma"));
	}
}
