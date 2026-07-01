

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProtoSpaceController {

  @GetMapping("/")
  public String index(Model model) {
    return "index";
  }
}