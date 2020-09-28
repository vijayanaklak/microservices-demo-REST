package com.vj.learn.restfulwebservice.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vj.learn.restfulwebservice.user.User;

@Component
public class PostDao {
	
	private static int idcounter = 3;
	
	private static int postCounter = 4;

//	private static List<User> userList = new ArrayList<User>();
//
//	private static List<Posts> postList = new ArrayList<Posts>();
//	
//	static {
//
//		postList.add(new Posts(1, new Date(), "Post1", "This is post one"));
//		postList.add(new Posts(2, new Date(), "Post2", "This is post two"));
//		postList.add(new Posts(3, new Date(), "Post3", "This is post three"));
//		postList.add(new Posts(4, new Date(), "Post4", "This is post four"));
//
//		userList.add(new User(1, "Jack", new Date(), postList));
//		userList.add(new User(2, "Bob", new Date(), postList));
//		userList.add(new User(3, "Adam", new Date(), postList));
//	}
//	
//	public List<Posts> retrieveUserPosts(Integer id) {
//		for (User user : userList) {
//			if(user.getId() == id) {
//				return user.getUserPosts();
//			}
//		}
//		return null;
//	}
//	
//	public User savePost(Integer id, User userObj) {
//		if(userObj.getId() == null) {
//			userObj.setId(++idcounter);
//		}
//		if(userObj.getUserPosts() == null) {
//			Posts newPost = new Posts(++postCounter, new Date(), "newPost", "This is new post");
//			postList.add(newPost);
//			userObj.setUserPosts(postList);
//		}
// 		userList.add(userObj);
//		return userObj;
//	}

}
