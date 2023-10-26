package com.quyet.be_quan_ao.controller.admin.products;

import com.quyet.be_quan_ao.Utility.Const;
import com.quyet.be_quan_ao.model.Product.Kichthuoc;
import com.quyet.be_quan_ao.model.viewModel.MessErro;
import com.quyet.be_quan_ao.service.SerSanPham.SerIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("v1/api/")
public class ControKichThuoc {
    @Autowired
    @Qualifier("kichthuoc")
    private SerIF serKichThuoc;

    @GetMapping("kich-thuoc")
    public ResponseEntity showAllKichThuoc() {
        return ResponseEntity.ok(serKichThuoc.getAll());
    }

    @PostMapping("kich-thuoc")
    public ResponseEntity addKichThuoc(@RequestBody Kichthuoc kichthuoc) {
        Optional kt = serKichThuoc.add(kichthuoc);

        if (!kt.isPresent())
            return Const.MESS_ERROR;

        return ResponseEntity.ok().body(kt.get());
    }

    @PutMapping("kich-thuoc")
    public ResponseEntity updateKichThuoc(@RequestBody Kichthuoc kichthuoc) {
        Optional kt = serKichThuoc.update(kichthuoc);

        if (!kt.isPresent())
            return ResponseEntity.badRequest().body((MessErro) kt.get());

        return ResponseEntity.ok().body(serKichThuoc.update(kichthuoc));
    }

    @DeleteMapping("kich-thuoc/{id}")
    public ResponseEntity deleteKichThuoc(@PathVariable Integer id) {
        if (!serKichThuoc.delete(id))
            return ResponseEntity.badRequest().body(Const.MESS_ID_INVALID);

        return ResponseEntity.ok("Delete susscess");
    }
}
