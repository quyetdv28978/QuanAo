package com.du1.services;

import com.du1.model.viewModel.ThongKe;
import com.du1.model.viewModel.sanPhamTop;
import com.du1.respon.jpaHoadon;
import com.du1.respon.jpaSanPham;
import com.du1.respon.jpaChitiethoadon;
import com.du1.respon.jpaThongKe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

@Service
public class ThongkeSer implements IFThongKe {
    @Autowired
    private jpaChitiethoadon jpact;

    @Autowired
    private jpaHoadon jpaHoadon;

    @Autowired
    private jpaSanPham jpaS;

    @Autowired
    private com.du1.respon.jpaThongKe jpaThongKe;

    @Override
    public ThongKe ThongKeByKhoangThoiGian(Date dateS, Date dateE) {
        return ThongKe.builder().tongTien((Double) jpact.get(dateS, dateE)).build();
    }

    @Override
    public List<Object[]> thongKeByThanginNam(Integer year) {
        List<Object[]> month_year = jpaThongKe.thongKeByThanginNam(year);
        List list_month_year = new ArrayList();
        if (!month_year.isEmpty()) {
            month_year.forEach(i -> {
                list_month_year.add(ThongKe.builder().time((Integer) i[0])
                        .tongTien((Double) i[1])
                        .build()
                );
            });
        }

        return list_month_year;
    }

    @Override
    public List<Object[]> SanPHamTop() {
        List<Object[]> month_year = jpaThongKe.SanPHamTop();
        List list_month_year = new ArrayList();
        if (!month_year.isEmpty()) {
            month_year.forEach(i -> list_month_year.add(sanPhamTop.builder().idsp(String.valueOf(i[1]))
                    .slsp(BigDecimal.valueOf((Long) i[0]))
                    .tensp(i[2].toString())
                    .build()
            ));
        }
        return list_month_year;
    }

    @Override
    public List<Object[]> SanPHamTopByMonth(Integer mounth) {
        List<Object[]> month_year = jpaThongKe.SanPHamTopByMonth(mounth);
        List list_month_year = new ArrayList();
        if (!month_year.isEmpty()) {
            month_year.forEach(i -> list_month_year.add(sanPhamTop.builder().idsp(String.valueOf(i[1]))
                    .slsp((BigDecimal) i[0])
                    .tensp(i[2].toString())
                    .build()
            ));
        }
        return list_month_year;
    }
}
