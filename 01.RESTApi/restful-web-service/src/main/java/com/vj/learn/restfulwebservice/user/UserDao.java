package com.vj.learn.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {

	private static int idcounter = 3;

	private static List<User> userList = new ArrayList<User>();

	static {
		userList.add(new User(1, "Jack", new Date()));
		userList.add(new User(2, "Bob", new Date()));
		userList.add(new User(3, "Adam", new Date()));
	}

	public List<User> findAll() {
		return userList;
	}

	public User saveUser(User userObj) {
		if (userObj.getId() == null) {
			userObj.setId(++idcounter);
		}
		userList.add(userObj);
		return userObj;
	}

	public User findUser(Integer id) {
		for (User user : userList) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User removeUser(Integer id) {
		Iterator<User> iterator = userList.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
