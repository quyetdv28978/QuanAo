package com.quyet.be_quan_ao.controller.admin.products;

import com.quyet.be_quan_ao.Utility.Const;
import com.quyet.be_quan_ao.model.Product.Danhmuc;
import com.quyet.be_quan_ao.model.viewModel.MessErro;
import com.quyet.be_quan_ao.service.SerSanPham.SerIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/api/")
public class ControDanhMuc {
    @Autowired
    @Qualifier("danhmuc")
    private SerIF serDanhMuc;

    @GetMapping("danh-muc")
    public ResponseEntity showAllDanhMuc() {
        return ResponseEntity.ok(serDanhMuc.getAll());
    }

    @PostMapping("danh-muc")
    public ResponseEntity addDanhMuc(@RequestBody Danhmuc DanhMuc) {
        Optional kt = serDanhMuc.add(DanhMuc);

        if (!kt.isPresent())
            return Const.MESS_ERROR;

        return ResponseEntity.ok().body(kt.get());
    }

    @PutMapping("danh-muc")
    public ResponseEntity updateDanhMuc(@RequestBody Danhmuc DanhMuc) {
        Optional kt = serDanhMuc.update(DanhMuc);

        if (!kt.isPresent())
            return ResponseEntity.badRequest().body((MessErro) kt.get());

        return ResponseEntity.ok().body(serDanhMuc.update(DanhMuc));
    }

    @DeleteMapping("danh-muc/{id}")
    public ResponseEntity deleteDanhMuc(@PathVariable Integer id) {
        if (!serDanhMuc.delete(id))
            return ResponseEntity.badRequest().body(Const.MESS_ID_INVALID);

        return ResponseEntity.ok("Delete susscess");
    }
}

