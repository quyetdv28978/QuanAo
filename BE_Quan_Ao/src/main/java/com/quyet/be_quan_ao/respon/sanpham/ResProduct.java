package com.quyet.be_quan_ao.respon.sanpham;

import com.quyet.be_quan_ao.model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResProduct extends JpaRepository<Product, Integer> {

}
