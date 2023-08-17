package com.du1.services;

import com.du1.model.viewModel.ThongKe;

import java.util.Date;
import java.util.List;

public interface IFThongKe {
    public ThongKe ThongKeByKhoangThoiGian(Date dateS, Date dateE);

    public List<Object[]> thongKeByThanginNam(Integer year);

    public List<Object[]>SanPHamTop();
    public List<Object[]>SanPHamTopByMonth(Integer mounth);
}
