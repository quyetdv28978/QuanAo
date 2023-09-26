package com.du1.controller.APi.admin;

import com.du1.services.filterSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class filterAPI {
    @Autowired
    private filterSer filterSer;

    @GetMapping("findByLsp/{id}/{trang}")
    public ResponseEntity filterByLSP(@PathVariable Integer id, @PathVariable Integer trang) {
        System.out.println(id + " " + trang);
return ResponseEntity.ok().body(filterSer.finallByLsp(id, trang));
    }


//    San pham tuong tu
//    @GetMapping("sanphamtuongtu/{id}")
//    public ResponseEntity sanphamTuongTu(@PathVariable Integer id){
//
//
//    }
}
