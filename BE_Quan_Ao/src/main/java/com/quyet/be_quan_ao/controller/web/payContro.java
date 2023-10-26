package com.quyet.be_quan_ao.controller.web;

import com.quyet.be_quan_ao.model.viewModel.MessErro;
import com.quyet.be_quan_ao.service.SerCart.SerIF_Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/api/")
public class payContro {
    @Autowired
    private SerIF_Pay serHoaDon;

    @GetMapping("pay")
    public ResponseEntity showAllGiohang() {
        return ResponseEntity.ok().body(serHoaDon.getAll());
    }

    @PostMapping("pay/{idgh}")
    public ResponseEntity addGioHang(@PathVariable Integer idgh) {
        Optional optional = serHoaDon.add(idgh);

        return ResponseEntity.status(((MessErro) optional.get()).getStatus()).body(optional.get());
    }
}
