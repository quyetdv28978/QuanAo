package com.du1.services;

import java.util.List;

public interface ServiceIF<Q>{

    public List<Q> getAll();

    public Integer add(Q q);

    public int update(Q q);

    public void delete(Integer id);

    public Object cd(Q o, boolean check);
}
