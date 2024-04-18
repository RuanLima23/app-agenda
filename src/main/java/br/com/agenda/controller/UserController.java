package br.com.agenda.controller;

import br.com.agenda.persistence.entity.UserEntity;
import br.com.agenda.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/app-agenda/api/user/")
public class UserController {

    @Autowired
    UserRepository  userRepository;

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<UserEntity> getAllUnit() {
        List<UserEntity> userList = userRepository.findAll();
        return userList;
    }

    @RequestMapping(value = "/addUsers", method = RequestMethod.POST)
    public UserEntity addUser(@RequestBody UserEntity user){
        UserEntity newUser = userRepository.save(user);
        return newUser;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HashMap<String, String> getlogin(@RequestBody UserEntity userReq){
        UserEntity userEntity = userRepository.findByUserNameAndPassword(userReq.getUserName(), userReq.getPassword());
        boolean status = false;
        String response = new String("");
        if(userEntity != null) {
            response = ("Usuário e senha confere");
            status = true;
        } else {
            response = ("Usuário e senha não confere");
            status = false;
        }
        HashMap<String, String> res = new HashMap<String, String>();
        res.put("auth", String.valueOf(status));
        res.put("msg", response);
        return res;

    }

}
