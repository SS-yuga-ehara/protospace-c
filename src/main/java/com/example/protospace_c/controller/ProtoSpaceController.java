package com.example.protospace_c.controller;

import com.example.protospace_c.repository.ProtoSpaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
