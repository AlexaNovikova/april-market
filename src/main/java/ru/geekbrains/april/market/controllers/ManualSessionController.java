package ru.geekbrains.april.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.april.market.utils.ManualSessionBean;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/manual_session")
@RequiredArgsConstructor
@Deprecated
public class ManualSessionController {
    @GetMapping("/man/get")
    public ManualSessionBean get(HttpSession session) {
        if (session.getAttribute("man_bean") == null) {
            session.setAttribute("man_bean", new ManualSessionBean());
        }
        return (ManualSessionBean)session.getAttribute("man_bean");
    }

    @GetMapping("/man/set")
    public void set(@RequestParam int value, HttpSession session) {
        if (session.getAttribute("man_bean") == null) {
            session.setAttribute("man_bean", new ManualSessionBean());
        }
        ManualSessionBean bean = ((ManualSessionBean)session.getAttribute("man_bean"));
        bean.setValue(value);
        session.setAttribute("man_bean", bean);
    }
}
