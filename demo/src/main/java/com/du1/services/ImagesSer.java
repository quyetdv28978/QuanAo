package com.du1.services;

import com.du1.model.entity.Images;
import com.du1.respon.jpaImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesSer implements ServiceIF<Images> {

    @Autowired
    private jpaImages images;

    @Override
    public List<Images> getAll() {return null;
    }

    @Override
    public int add(Images images) {
        return this.images.save(images).getId();
    }

    @Override
    public int update(Images images) {
        return this.images.save(images).getId();
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public Object cd(Images o, boolean check) {
        return null;
    }

    public Images getById(Integer id){
        return images.findById(id).get();
    }
}
