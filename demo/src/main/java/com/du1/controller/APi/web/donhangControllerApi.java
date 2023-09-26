package com.du1.controller.APi.web;

import com.du1.model.viewModel.donhangViewModel;
import com.du1.services.donhangSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class donhangControllerApi {
    @Autowired
    private donhangSer donhangSer;

    @PostMapping("donhang")
    public void donhang(@RequestBody donhangViewModel donhang){
        donhangSer.add(donhang);
    }
}
