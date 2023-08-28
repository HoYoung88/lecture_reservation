package com.lecture.reservation.web.controller;

import lombok.Getter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by HoYoung on 2023/06/30.
 */
@Getter
public class WebBaseController {
    private Model model;

    @ModelAttribute
    public void modelContext(Model model) {
        this.model = model;
    }

    public void setPageTitle(String pageTitle) {
        this.model.addAttribute("pagetitle", pageTitle);
    }

    public String createRouter(String depth) {
        return String.format("pages/%s.html", depth);
    }

    public String createRouter(String depth, String depth1) {
        return String.format("pages/%s/%s.html", depth, depth1) ;
    }

    public String createRouter(String depth, String depth1, String depth2) {
        return String.format("pages/%s/%s/%s.html", depth, depth1, depth2);
    }
}
