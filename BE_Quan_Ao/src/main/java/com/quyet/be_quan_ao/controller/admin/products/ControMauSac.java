package com.quyet.be_quan_ao.controller.admin.products;

import com.quyet.be_quan_ao.Utility.Const;
import com.quyet.be_quan_ao.model.Product.MauSac;
import com.quyet.be_quan_ao.model.viewModel.MessErro;
import com.quyet.be_quan_ao.service.SerSanPham.SerIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/api/")
public class ControMauSac {
    @Autowired
    @Qualifier("mausac")
    private SerIF sermausac;

    @GetMapping("mau-sac")
    public ResponseEntity showAllmausac() {
        return ResponseEntity.ok(sermausac.getAll());
    }

    @PostMapping("mau-sac")
    public ResponseEntity addmausac(@RequestBody MauSac mausac) {
        Optional kt = sermausac.add(mausac);

        if (!kt.isPresent())
            return Const.MESS_ERROR;

        return ResponseEntity.ok().body(kt.get());
    }

    @PutMapping("mau-sac")
    public ResponseEntity updatemausac(@RequestBody MauSac mausac) {
        Optional kt = sermausac.update(mausac);

        if (!kt.isPresent())
            return ResponseEntity.badRequest().body((MessErro) kt.get());

        return ResponseEntity.ok().body(sermausac.update(mausac));
    }

    @DeleteMapping("mau-sac/{id}")
    public ResponseEntity deletemausac(@PathVariable Integer id) {
        if (!sermausac.delete(id))
            return ResponseEntity.badRequest().body(Const.MESS_ID_INVALID);

        return ResponseEntity.ok("Delete susscess");
    }
}
