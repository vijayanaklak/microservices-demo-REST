package com.vj.learn.restfulwebservice.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vj.learn.restfulwebservice.post.PostDao;

@RestController
public class UserController {

	@Autowired
	private UserDao userService;

	@Autowired
	private PostDao postService;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable Integer id) {
		User user = userService.findUser(id);
		if (user == null) {
			throw new UserNotFoundException("Id -" + id);
		}

		// hateos for providing links to user
		EntityModel<User> resource = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		User user = userService.removeUser(id);
		if (user == null) {
			throw new UserNotFoundException("Id -" + id);
		}
	}

	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User updatedUser = userService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(updatedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	//
	// @GetMapping("/users/{id}/posts")
	// public List<Posts> getUserPosts(@PathVariable Integer id) {
	// List<Posts> userPosts = postService.retrieveUserPosts(id);
	// if (userPosts == null) {
	// throw new UserNotFoundException("Posts for Id - " + id + " not found");
	// } else {
	// return userPosts;
	// }
	// }
	//
	// @PostMapping("/users/{id}/posts")
	// public ResponseEntity<User> savePost(@PathVariable Integer id ,@RequestBody
	// User user) {
	// User updatedUser = postService.savePost(id,user);
	// URI location =
	// ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedUser.getId()).toUri();
	// return ResponseEntity.created(location).build();
	// }

}
