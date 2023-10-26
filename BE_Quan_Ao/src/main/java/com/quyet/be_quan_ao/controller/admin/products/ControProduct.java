package com.quyet.be_quan_ao.controller.admin.products;

import com.quyet.be_quan_ao.Utility.Const;
import com.quyet.be_quan_ao.model.Product.Product;
import com.quyet.be_quan_ao.model.viewModel.MessErro;
import com.quyet.be_quan_ao.model.viewModel.ViewProduct;
import com.quyet.be_quan_ao.service.SerSanPham.SerIF;
import com.quyet.be_quan_ao.service.SerSanPham.SerProduct;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("v1/api/")
public class ControProduct {
    @Autowired
    @Qualifier("product")
    private SerIF serSanPham;

    @Autowired
    @Qualifier("chitietsp")
    private SerIF serSanPhamDetail;

    @PostMapping("a")
    public MessErro te(@RequestPart MessErro a, @RequestPart MultipartFile b) {
//        System.out.println(a instanceof MessErro);
        System.out.println(a + " " + b);
        return MessErro.builder().build();
    }

    //  Hiển thị toàn bộ sản phẩm
    @GetMapping("products")
    public ResponseEntity showAllProduct() {
        return ResponseEntity.ok().body(serSanPham.getAll());
    }

    //    Hiển thị sản phẩm chi tiết của sản phẩm được chọn
    @GetMapping("product/{id}")
    public ResponseEntity showDetailProduct(@PathVariable Integer id) {
        List optional = ((SerProduct) serSanPham).finBySanPham(id);
        if (optional == null)
            return ResponseEntity.badRequest().body(Optional.of(Const.MESS_ID_INVALID));

        return ResponseEntity.ok().body(optional);
    }

    @GetMapping("product-detail/{id}")
    public ResponseEntity showDetail(@PathVariable Integer id) {
        Optional optional = serSanPham.finById(id);
        if (optional.isEmpty())
            return ResponseEntity.badRequest().body(Const.MESS_NOT_NULL);
        return ResponseEntity.ok().body(optional.get());
    }

    //  thêm chi tiết sản phẩm
    @PostMapping("product")
    public ResponseEntity addCTSP(@RequestPart ViewProduct viewProduct, @RequestPart MultipartFile file) throws IOException {
        System.out.println(viewProduct);
        updoadLoadFile(file);
        viewProduct.setImages(file.getOriginalFilename());
        Optional<MessErro> optional1 = serSanPhamDetail.add(viewProduct);
        return ResponseEntity.status(optional1.get().getStatus()).body(optional1.get().getMess());
    }

    private Optional updoadLoadFile(MultipartFile imag) throws IOException {
        String duoi = imag.getOriginalFilename();
        if (!StringUtils.containsAny(duoi, ".png", ".jpg"))
            return Const.MESS_FILE;

        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");

        if (Files.exists(Const.CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(imag.getOriginalFilename())))
            return null;
        if (!Files.exists(Const.CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(Const.CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = Const.CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(imag.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(imag.getBytes());
        }
        return null;
    }
}
