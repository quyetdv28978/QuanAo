package com.quyet.be_quan_ao.service.SerSanPham;

import java.util.List;
import java.util.Optional;

public interface SerIF<Q> {
    List<Q> getAll();

    Optional<Q> add(Q q);

    Optional<Q> update(Q q);

    Boolean delete(Integer id);

    Optional<Q> finById(Integer id);
}
