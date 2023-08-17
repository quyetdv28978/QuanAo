package com.du1.controller.APi.admin;

import com.du1.model.entity.Images;
import com.du1.model.entity.SanPham;
import com.du1.model.entity.loaisanpham;
import com.du1.model.viewModel.LoaiSanPhamViewModel;
import com.du1.model.viewModel.productViewModel;
import com.du1.services.ImagesSer;
import com.du1.services.LoaiSanPhamSer;
import com.du1.services.SanPhamSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("api/")
public class SanPhamControllerApi {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources");

    @Autowired
    private SanPhamSer quantriSer;

    @Autowired
    private ImagesSer imagesSer;

    @Autowired
    private LoaiSanPhamSer loaiSanPhamSer;

    @Autowired
    private com.du1.respon.jpaSanPham jpaSanPham;
//    Lấy toàn bộ sản phẩm

    @GetMapping("sanpham")
//    @PreAuthorize("hasRole('ADMIN')")
    public List getSP() {
        return quantriSer.getAll(jpaSanPham.findAll());
    }

    @GetMapping("sanphambysl")
//    @PreAuthorize("hasRole('ADMIN')")
    public List getSPBySL() {
        return quantriSer.getAllBySL(4);
    }

    @GetMapping("sanphamBySL/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public List getSPBySlSP(@PathVariable Integer id) {
        System.out.println(id);
        return quantriSer.getAll(quantriSer.getAll2(4, id));
    }

//    Lấy sản phẩm theo ID
    @GetMapping("sanpham/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public productViewModel getSPbyID(@PathVariable("id") Integer id) {
        String path = "http://localhost:6969/demov1/imagePath/";
        List<String> imaga = new ArrayList<>();
        if (quantriSer.findbyID(id).isPresent()) {
            SanPham s = quantriSer.findbyID(id).get();
            s.getImages().stream().sorted(Comparator.comparing(Images::getId)).forEach(item -> imaga.add(item.getId() + path + item.getAnh()));

            LoaiSanPhamViewModel l = null;
            if (s.getLoaisanpham() != null) {
                l = LoaiSanPhamViewModel.builder().id(s.getLoaisanpham().getId())
                        .tenlsp(s.getLoaisanpham().getTenlsp())
                        .trangthai(s.getTrangthai())
                        .build();
            }
            return new productViewModel(s.getId(), s.getTensanpham(), s.getMota(), s.getTieude(),
                    s.getMa(), s.getNhasanxuat(), s.getSoluong(), s.getTrangthai(), s.getTrongluong(),
                    s.getKichthuoc(), s.getGiaban(), s.getGiagoc(), imaga
                    , null, l);
        } else return null;
    }

//    Lấy sản phẩm theo loại sản phẩm
    @GetMapping("loaibysanpham/{id}")
    public List getSPByLoaiSanPham(@PathVariable Integer id){
            return quantriSer.getSanPhamByLoaiSanPham(id);
    }

//    Thêm mới 1 sản phẩm
    @PostMapping("sanpham")
//    @PreAuthorize("hasRole('ADMIN')")
    public String create(@RequestPart("image") MultipartFile[] image, @RequestPart productViewModel sanpham) {
        List<String> images = new ArrayList<>();
        for (MultipartFile i : image) {
            images.add(i.getOriginalFilename());
        }
        sanpham.setImages(images);
        quantriSer.add(sanpham);
        try {
            uploadFILE(image);
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    @PutMapping("sanpham/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public String Update(@RequestPart(value = "image", required = false) MultipartFile[] image,
                         @RequestPart productViewModel sanpham,
                         @PathVariable("id") int id
    ) {
        int check = 0;
        List<String> ids = sanpham.getImages().stream().toList();

        if (image != null) {
            List<String> images = new ArrayList<>();
            for (MultipartFile i : image) {
                images.add(i.getOriginalFilename() + "+" + ids.get(check));
                check++;
            }
            sanpham.setImages(images);

            try {
                uploadFILE(image);
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        sanpham.setId(id);
        quantriSer.update(sanpham);

        return null;
    }

    private void uploadFILE(MultipartFile image[]) throws IOException {
        System.out.println(CURRENT_FOLDER);
        System.out.println(image.length);
        for (MultipartFile imag : image) {
            System.out.println(imag.getName() + " " + imag.getContentType() + " " + imag.getOriginalFilename());
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("imagePath");
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path file = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(imag.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(imag.getBytes());
            }
        }
    }


    @PostMapping("loaisanpham")
//        @PreAuthorize("hasRole('ADMIN')")
    public String themLoaiSanPham(@RequestBody loaisanpham loaisanpham) {
        loaiSanPhamSer.add(loaisanpham);
        return null;
    }

    @GetMapping("loaisanpham")
    @PreAuthorize("hasRole('ADMIN')")
    public List<loaisanpham> getALL() {
//        loaiSanPhamSer.add(loaisanpham);
        return loaiSanPhamSer.getAll();
    }

    @DeleteMapping("sanpham/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void xoa(@PathVariable Integer id) {
        quantriSer.delete(id);
    }

    @GetMapping("/pageableSanPham/{page}")
    public ResponseEntity pageAble(@PathVariable(required = false) Integer page) {
        System.out.println("Day la page " + page);
        return ResponseEntity.ok().body(quantriSer.pageAble(page));
    }

//    San pham tuong tu


}
