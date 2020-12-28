package org.orz.psol.controller;

import org.orz.psol.model.pageModel.HomePageVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class HomePageController {

    @GetMapping("/home")
    HomePageVO getHomeData() {
        return new HomePageVO();
    }
}
