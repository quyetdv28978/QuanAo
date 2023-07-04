package com.du1.controller.APi.web;

import com.du1.model.entity.donhang;
import com.du1.model.viewModel.donhangViewModel;
import com.du1.services.donhangSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class donhangControllerApi {
    @Autowired
    private donhangSer donhangSer;

    @PostMapping("api/donhang")
    public void donhang(@RequestBody donhangViewModel donhang){
        System.out.println("sadfjhskajhfks");
        System.out.println(donhang);
        donhangSer.add(donhang);
    }
}
