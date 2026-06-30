package com.example.protospace_c.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.AllArgsConstructor;

import com.example.protospace_c.repository.ProtoSpaceRepository;

@Controller
@AllArgsConstructor
public class ProtoSpaceController {
    private final ProtoSpaceRepository protoSpaceRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("prototypes", protoSpaceRepository.findAll());
        return "index";
    }
}
