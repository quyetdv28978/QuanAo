package com.quyet.be_quan_ao.service.SerCart;

import java.util.List;
import java.util.Optional;

public interface SerIF_Pay<Q> {
    List<Q> getAll();

    Optional add(Integer idGH);

    Optional update(Q q);

    Optional delete(Q q);

    Optional findById(Integer id);
}
