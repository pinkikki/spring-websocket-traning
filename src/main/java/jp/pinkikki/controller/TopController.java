package jp.pinkikki.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopController {

    @RequestMapping(value = "/", method = GET)
    public String show() {
        return "index";
    }

    @RequestMapping(value = "/top", method = GET)
    public String top() {
        return "show";
    }
}
