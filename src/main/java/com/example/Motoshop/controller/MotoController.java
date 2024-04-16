package com.example.Motoshop.controller;

import com.example.Motoshop.model.Moto;
import com.example.Motoshop.model.User;
import com.example.Motoshop.service.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class MotoController {
    private final MotoService motoService;

    @GetMapping("/")
    public String moto (@RequestParam(name = "searchWord",required = false)String title, Principal principal, Model model){
        model.addAttribute("moto",motoService.showAll(title));
        model.addAttribute("user",motoService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        return "moto";
    }

    @GetMapping("/moto/{id}")
    public String motoInfo(@PathVariable Long id, Model model){
        Moto moto = motoService.getMotoById(id);
        model.addAttribute("moto",moto);
        model.addAttribute("images",moto.getImages());
        return "moto-info";
    }

    @PostMapping("/moto/create")
    public String createMoto(@RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3, Moto moto , Principal principal) throws IOException {
        motoService.saveMoto(principal, moto, file1, file2, file3);
        return "redirect:/my/moto";
    }

    @PostMapping("/moto/delete/{id}")
    public String deleteMoto(@PathVariable Long id){
        motoService.deleteMoto(id);
        return "redirect:/";
    }

    @GetMapping("/my/moto")
    public String userProducts(Principal principal, Model model) {
        User user = motoService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("products", user.getMotos());
        return "my-moto";
    }
}
