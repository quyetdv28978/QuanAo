package com.quyet.be_quan_ao.controller.web;

import com.quyet.be_quan_ao.model.viewModel.Cart;
import com.quyet.be_quan_ao.model.viewModel.MessErro;
import com.quyet.be_quan_ao.service.SerCart.SerIF_Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/api/")
public class CartContro {
    @Autowired
    @Qualifier("giohang")
    private SerIF_Cart serGioHang;

    @GetMapping("gio-hang")
    public ResponseEntity showAllGiohang() {
        return ResponseEntity.ok().body(serGioHang.getAll());
    }

    @PostMapping("gio-hang")
    public ResponseEntity addGioHang(@RequestBody Cart cart) {
        Optional optional = serGioHang.add(cart);

        return ResponseEntity.status(((MessErro) optional.get()).getStatus()).body(optional.get());
    }
}
