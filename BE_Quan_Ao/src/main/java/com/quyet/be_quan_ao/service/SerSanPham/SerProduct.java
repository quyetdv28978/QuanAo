package com.quyet.be_quan_ao.service.SerSanPham;

import com.quyet.be_quan_ao.Utility.Const;
import com.quyet.be_quan_ao.model.Product.Product;
import com.quyet.be_quan_ao.model.viewModel.ViewProduct;
import com.quyet.be_quan_ao.model.viewModel.ViewProductChiTiet;
import com.quyet.be_quan_ao.respon.sanpham.ResProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "product")
public class SerProduct implements SerIF<Product> {

    @Autowired
    private ResProduct resProduct;

    @Override
    public List<Product> getAll() {
        return resProduct.findAll();
    }

    @Override
    public Optional<Product> add(Product product) {
        return Optional.of(resProduct.save(product));
    }

    @Override
    public Optional<Product> update(Product product) {
        return Optional.of(resProduct.save(product));
    }

    @Override
    public Boolean delete(Integer id) {
        if (resProduct.existsById(id)) {
            resProduct.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional finById(Integer id) {
        Optional optional = resProduct.findById(id);
        if (optional.isPresent())
            return resProduct.findById(id);
        return Optional.empty();
    }

    public List<ViewProductChiTiet> finBySanPham(Integer id) {
        Optional product = finById(id);
        if (!product.isPresent())
            return null;

        return (((Product) product.get()).getSanphams().stream()
                .map(i -> ViewProductChiTiet.builder()
                        .tenSP(i.getIdsp().getTensanpham())
                        .giaBan(i.getGianban())
                        .soLuong(i.getSoluong())
                        .mauSac(i.getIdms().getMausac())
                        .idctsp(i.getId())
                        .build()
                ).collect(Collectors.toList()));
    }
}
