package com.lecture.reservation.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by HoYoung on 2023/06/30.
 */
@Controller
public class WebController extends WebBaseController {

    @GetMapping(value = "")
    public String goPage() {
        return super.createRouter("lecture");
    }

    @GetMapping(value = "/{depth}.do")
    public String goPage(@PathVariable String depth) {
        return super.createRouter(depth);
    }

    @GetMapping(value = "/{depth}/{depth1}.do")
    public String goPage(@PathVariable String depth,
                         @PathVariable String depth1) {
        return super.createRouter(depth, depth1);
    }

    @GetMapping(value = "/{depth}/{depth1}/{depth2}.do")
    public String goPage(@PathVariable String depth,
                         @PathVariable String depth1,
                         @PathVariable String depth2) {

        return super.createRouter(depth, depth1, depth2);
    }

}
