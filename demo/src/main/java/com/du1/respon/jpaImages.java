package com.du1.respon;

import com.du1.model.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface jpaImages extends JpaRepository<Images, Integer> {
}
