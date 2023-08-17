package com.du1.controller.APi.admin;

import com.du1.model.viewModel.ThongKe;
import com.du1.services.ThongkeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class ThongKeAPI {
    @Autowired
    private ThongkeSer jpaThongKe;

    @GetMapping("/monthinyear/{year}")
    public ResponseEntity ThongKeTheoKhoang(@PathVariable(name = "year") Integer year){
        return ResponseEntity.ok().body(jpaThongKe.thongKeByThanginNam(year));
    }

    @GetMapping("sanphamtop")
    public ResponseEntity SanPhamTop(){

        return ResponseEntity.ok().body(jpaThongKe.SanPHamTop());
    }


    @GetMapping("sanphamtop/{m}")
    public ResponseEntity SanPhamTopByMonth(@PathVariable Integer m){

        return ResponseEntity.ok().body(jpaThongKe.SanPHamTopByMonth(m));
    }
}
