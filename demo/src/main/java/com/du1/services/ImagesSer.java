package com.du1.services;

import com.du1.model.entity.Images;
import com.du1.respon.jpaImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ImagesSer implements ServiceIF<Images> {

    @Autowired
    private jpaImages images;

    @Override
    public List<Images> getAll() {return null;
    }

    @Override
    @Transactional
    public Integer add(Images images) {
        return this.images.save(images).getId();
    }

    @Override
    @Transactional
    public int update(Images images) {
        return this.images.save(images).getId();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
    }

    @Override
    @Transactional
    public Object cd(Images o, boolean check) {
        return null;
    }

    public Images getById(Integer id){
        return images.findById(id).get();
    }
}
