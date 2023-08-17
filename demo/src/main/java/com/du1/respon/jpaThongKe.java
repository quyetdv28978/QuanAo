package com.du1.respon;

import com.du1.model.entity.Hoadon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface jpaThongKe extends JpaRepository<Hoadon, Integer> {
    @Query("select month(h.ngaytt), sum(h.tongtien) from Hoadon h where year(h.ngaytt)=:year group by month (h.ngaytt)")
    public List<Object[]> thongKeByThanginNam(Integer year);

    @Query("select sum(ct.soluong), ct.sanpham.id, s.tensanpham from Chitiethoadon ct join SanPham s on ct.sanpham.id = s.id\n" +
            "join Hoadon h on ct.hoadon.id = h.id" +
            " group by ct.sanpham.id order by sum(ct.soluong) desc "
    )
    public List<Object[]> SanPHamTop();

    //   @Query("select sum(ct.soluong), ct.sanpham.id, s.tensanpham from Chitiethoadon ct join SanPham s on ct.sanpham.id = s.id\n" +
//           "join Hoadon h on ct.hoadon.id = h.id "+
//           "where h.ngaytt between DATE_ADD(now(), INTERVAL (month(now())-1) month )"
//           +" and DATE_ADD(now(), INTERVAL 1 month )"+
//           " group by ct.sanpham.id order by sum(ct.soluong) desc "
//   )
    @Query(value = "select sum(ct.soluong), idsp, s.tenSanPham from chitiethoadon ct join sanpham s on ct.idSP = s.id\n" +
            "                                           join hoadon d on d.id = ct.idDH\n" +
            "                                           where month(d.ngaytt) between month(DATE_SUB(CURDATE(), INTERVAL (month(curdate())-1) month ))\n" +
            "                                               and month(DATE_SUB(CURDATE(), INTERVAL ?1 month ))\n" +
            "                                           group by idSP\n" +
            "order by sum(ct.soluong) desc\n", nativeQuery = true)
    public List<Object[]> SanPHamTopByMonth(Integer mounth);
}
