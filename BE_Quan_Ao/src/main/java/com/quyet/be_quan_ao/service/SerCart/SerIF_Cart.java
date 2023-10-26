package com.quyet.be_quan_ao.service.SerCart;


import java.util.List;
import java.util.Optional;

public interface SerIF_Cart<Q> {
    List<Q> getAll();

    Optional add(Q q);

    Optional update(Q q);

    Optional delete(Integer q);

    Optional findById(Integer id);
}
