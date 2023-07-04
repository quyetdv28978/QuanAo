package com.du1.controller.APi.admin;

import com.du1.model.entity.SanPham;
import com.du1.model.entity.loaisanpham;
import com.du1.model.viewModel.LoaiSanPhamViewModel;
import com.du1.model.viewModel.productViewModel;
import com.du1.services.ImagesSer;
import com.du1.services.LoaiSanPhamSer;
import com.du1.services.quantriSer;
import com.sun.source.tree.TryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SanPhamControllerApi {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources");

    @Autowired
    private quantriSer quantriSer;

    @Autowired
    private ImagesSer imagesSer;

    @Autowired
    private LoaiSanPhamSer loaiSanPhamSer;

    @GetMapping("api/sanpham")
    public List getSP(){
        return quantriSer.getAll();
    }

    @GetMapping("api/sanpham/{id}")
    public productViewModel getSPbyID(@PathVariable("id") Integer id){
        System.out.println("get id : " + id);
        String path ="http://localhost:6969/imagePath/";
        Set<String> imaga = new HashSet<>();
       if (quantriSer.findbyID(id).isPresent()){
          SanPham s =  quantriSer.findbyID(id).get();
          s.getImages().forEach(item -> imaga.add(item.getId()+ path + item.getAnh()));

           LoaiSanPhamViewModel l = null;
           if (s.getLoaisanpham() != null){
               l=LoaiSanPhamViewModel.builder().id(s.getLoaisanpham().getId())
                       .tenlsp(s.getLoaisanpham().getTenlsp())
                       .trangthai(s.getTrangthai())
                       .build();
           }
           return new productViewModel(s.getId(), s.getTensanpham(), s.getMota(), s.getTieude(),
                   s.getMa(), s.getNhasanxuat(), s.getSoluong(), s.getTrangthai(), s.getTrongluong(),
                   s.getKichthuoc(), s.getGiaban(), s.getGiagoc(), imaga, null,l);
       }
       else return null;
    }

    @PostMapping("api/sanpham")
    public String create(@RequestPart("image") MultipartFile[] image, @RequestPart productViewModel sanpham) {
        Set<String> images = new HashSet<>();
        for (MultipartFile i : image) {
            images.add(i.getOriginalFilename());
        }
        sanpham.setImages(images);
        quantriSer.add(sanpham);
        try {
            uploadFILE(image);
        }
        catch (Exception e){
            return e.getMessage();
        }
        return null;
    }

    @PutMapping ("api/sanpham/{id}")
    public String Update(@RequestPart(value = "image", required = false) MultipartFile[] image,
                         @RequestPart productViewModel sanpham,
                         @PathVariable("id") int id
                         ) {
        int check = 0;
        System.out.println("Day la PUT: " + sanpham);
        System.out.println("Day la PUT: " + id);
        List<String> ids = sanpham.getImages().stream().toList();

        if (image != null) {
        Set<String> images = new HashSet<>();
        for (MultipartFile i : image) {
            images.add(i.getOriginalFilename() +"+"+ ids.get(check));
            check ++;
        }
        sanpham.setImages(images);

            try {
                uploadFILE(image);
            } catch (Exception e) {
                return e.getMessage();
            }
        }
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


        @PostMapping("api/loaisanpham")
public String themLoaiSanPham(@RequestBody loaisanpham loaisanpham){
        loaiSanPhamSer.add(loaisanpham);
return null;
}

    @GetMapping("api/loaisanpham")
    public List<loaisanpham> getALL(){
//        loaiSanPhamSer.add(loaisanpham);
        return loaiSanPhamSer.getAll();
    }

    @DeleteMapping("api/sanpham/{id}")
    public void xoa(@PathVariable Integer id){
         quantriSer.delete(id);
    }

}
