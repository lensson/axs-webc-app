package com.alcatel.axs.webc.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alcatel.axs.webc.domain.User;




@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	
    @RequestMapping(method = GET)
    public List<User> searchUsers(
            @RequestParam(defaultValue="") String keyword, 
            @RequestParam(defaultValue="0") int page, 
            @RequestParam(defaultValue="200") int pageSize) {
        
    	List<User> userList = new ArrayList<User>();
    	
    	User user = new User();
    	user.setId(1);
    	user.setName("User 1");
    	user.setDescription("User Description 1");
    	
    	userList.add(user);
    	
    	user = new User();
    	user.setId(2);
    	user.setName("User 2");
    	user.setDescription("User Description 2");
    	
    	userList.add(user);
    	
        return userList;
    }
}
