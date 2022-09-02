package com.trevor.bcrypt.Controllers;

import com.trevor.bcrypt.models.UserName;
import com.trevor.bcrypt.repository.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthenticationController {
    @Autowired
    UserRepo userRepository;

    @GetMapping("/login")
    public String getLoginPage(){
        return "/login.html";
    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "/signup.html";
    }



    @PostMapping("/login")
    public RedirectView logInUser(String username, String password){
        UserName userFromDb = userRepository.findByUsername(username);
        if((userFromDb == null) || (!BCrypt.checkpw(password, userFromDb.getPassword()))){
            return new RedirectView("/login");
        }
        return new RedirectView("/");

    }


    @PostMapping("/signup")
    public RedirectView signUpUser(String username, String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        UserName newUser = new UserName(username, hashedPassword);
        userRepository.save(newUser);
        return new RedirectView("/login");
    }

}
