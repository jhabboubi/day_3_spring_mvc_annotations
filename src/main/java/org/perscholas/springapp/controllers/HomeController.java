package org.perscholas.springapp.controllers;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.springapp.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@SessionAttributes({"theuser", "thename"})

public class HomeController {
  // localhost:8080/index


    @GetMapping("/listusers")
    public String listUser(Model model, @RequestParam("hello") int kjddk){
        List<User> listUsers = new ArrayList<User>();

        listUsers.add(new User(1,"Jafer","jafer@gmail.com"));
        listUsers.add(new User(2,"Tim","tim@gmail.com"));
        listUsers.add(new User(3,"Ying","ying@gmail.com"));
        System.out.println("kjddk= "+ kjddk);

        for(User list: listUsers) {
            System.out.println(list.getEmail());
        }


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
    public String showData(@ModelAttribute("theuser") User user, Model model){
        log.warn("post request!");
        model.addAttribute("thenewuser",user);
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






}
