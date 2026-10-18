package com.acme.employeehub.ui.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UiErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute("jakarta.servlet.error.status_code");
        if (status != null) {
            int code = Integer.parseInt(status.toString());
            if (code == HttpStatus.NOT_FOUND.value()) {
                return "forward:/index.html";
            }
        }

        return "forward:/index.html";
    }
}
