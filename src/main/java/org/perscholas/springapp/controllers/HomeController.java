package org.perscholas.springapp.controllers;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.springapp.models.User;
import org.perscholas.springapp.repo.UserRepo;
import org.perscholas.springapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@SessionAttributes({"theuser", "thename"})

public class HomeController {
  // localhost:8080/index

//        @Autowired
//        private UserRepo userRepo;

    private UserService userService;
    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/listusers")
    public String listUser(Model model){

//        List<User> listUsers = new ArrayList<>();
//
//        listUsers.add(new User(1,"Jafer","jafer@gmail.com"));
//        listUsers.add(new User(2,"Tim","tim@gmail.com"));
//        listUsers.add(new User(3,"Ying","ying@gmail.com"));
//        System.out.println("kjddk= "+ kjddk);
//
//        for(User list: listUsers) {
//            System.out.println(list.getEmail());
//        }

        List<User> listUsers = userService.getByEmail("habboubi@gmail.com");
        System.out.println(listUsers);


        model.addAttribute("thelist",listUsers);

        return "listusers";
    }

    @GetMapping({"/index","/"})
    public String showIndex(Model model){
        log.warn("requested index.html");


        String username="habboubi";
        model.addAttribute("thename", username);
        return "index";
    }
    @PostMapping("/process")
    public String showData(@ModelAttribute("theuser") @Valid User user, BindingResult bindingResult, Model model){
        log.warn("post request!");
        if(bindingResult.hasErrors()){
            log.warn(bindingResult.getAllErrors().toString());
            return "index";

        }

        model.addAttribute("thenewuser",user);

        userService.saveUser(user);

        return "showdata";
    }

    @ModelAttribute("theuser")
    public User userInit(){
        return new User();
    }

//    @PostMapping("/process")
//    public String showData(@RequestParam("id") int id,
//                           @RequestParam("name") String name,
//                           @RequestParam("email") String email,
//                           Model model){
//        log.warn("post request!");
//        User tooMuchWork = new User(id,name,email);
//        model.addAttribute("thenewuser",tooMuchWork);
//        return "showdata";
//    }


    @PostConstruct
    public void init(){
        log.warn("Constructed!");
    }

    @PreDestroy
    public void destroy(){
        log.warn("Destroyed!");
    }




}
