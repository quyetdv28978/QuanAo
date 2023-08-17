package com.du1.controller.APi.admin;

import com.du1.model.entity.DanhMuc;
import com.du1.model.viewModel.danhmucViewModel;
import com.du1.services.DanhMucSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/")
public class DanhMucControllerAPI {

    @Autowired
    private DanhMucSer danhMucSer;

    @GetMapping("danhmuc")
    @PreAuthorize("hasRole('ADMIN')")
    public List<danhmucViewModel> getALl() {
        return danhMucSer.getAll();
    }

    @GetMapping("danhmuc/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DanhMuc getDM(@PathVariable("id") int id) {
        return danhMucSer.finbyID(id);
    }

    @PostMapping("danhmuc")
    @PreAuthorize("hasRole('ADMIN')")
    public void addDM(@RequestBody danhmucViewModel danhMuc) {
        danhMucSer.add(danhMuc);
    }

    @PutMapping("danhmuc/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void addDM(@RequestBody(required = false) Set<String> sanphamChon
            , @PathVariable Integer id) {
        danhmucViewModel d = danhmucViewModel.builder().id(id).idSP(sanphamChon).build();
        danhMucSer.update(d);

    }

    @PutMapping("/danhmuc2/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void UpDM(@PathVariable Integer id,
                     @RequestBody danhmucViewModel danhMuc
    ) {
        danhmucViewModel d = danhmucViewModel.builder().id(id).tendm(danhMuc.getTendm()).idSP(danhMuc.getIdSP()).build();

        danhMucSer.update(d);

    }


    @DeleteMapping("/danhmuc/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDM(@PathVariable Integer id) {
        danhMucSer.delete(id);
    }
}
