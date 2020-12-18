package webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/jpa/users")
	public List<User> RetriveAllUser(){
		
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> RetriveUser(@PathVariable int id){
		Optional<User> user =  userRepository.findById(id);
		
		if (!(user.isPresent())) {
			throw new UserNotFoundException("id-"+id);
		}
		
		EntityModel<User> resource = new EntityModel<User>(user.get());
		
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).RetriveAllUser());
		
		resource.add(linkTo.withRel("all_users"));
				
		return resource;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> CreateUser(@Valid @RequestBody User user) {
		
		userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").buildAndExpand(user.getId()).
				toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	
	public void DeleteUser(@PathVariable int id) {
		
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> RetriveAllPostUser(@PathVariable int id){
		Optional<User> user =  userRepository.findById(id);
		
		if (!(user.isPresent())) {
			throw new UserNotFoundException("id-"+id);
		}
				
		return user.get().getPost();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> CreatePost(@PathVariable int id, @RequestBody Post post){
		Optional<User> userOptional =  userRepository.findById(id);
		
		if (!(userOptional.isPresent())) {
			throw new UserNotFoundException("id-"+id);
		}
				
		 User user = userOptional.get();
		 
		 post.setUser(user);
		 
		 postRepository.save(post);
		 
		 URI location = ServletUriComponentsBuilder.
					fromCurrentRequest().
					path("/{id}").buildAndExpand(post.getId()).
					toUri();
			
			return ResponseEntity.created(location).build();
	}
	
	

}
