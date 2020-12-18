package webservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> RetriveAllUser(){
		
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> RetriveUser(@PathVariable int id){
		User user =  service.findOne(id);
		
		if (user == null) {
			throw new UserNotFoundException("id-"+id);
		}
		
		EntityModel<User> resource = new EntityModel<User>(user);
		
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).RetriveAllUser());
		
		resource.add(linkTo.withRel("all_users"));
				
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> CreateUser(@Valid @RequestBody User user) {
		
		service.save(user);
		
		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").buildAndExpand(user.getId()).
				toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	
	public void DeleteUser(@PathVariable int id) {
		
		User user = service.deleteById(id);
		
		if (user == null) {
			throw new UserNotFoundException("id-"+id);
		}
	
	}

}
