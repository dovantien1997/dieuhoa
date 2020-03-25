package com.minhtien.app.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.minhtien.app.model.CongNghe;
import com.minhtien.app.model.DieuHoa;
import com.minhtien.app.model.LoaiMay;
import com.minhtien.app.model.LoaiSanPham;
import com.minhtien.app.model.ThuongHieu;
import com.minhtien.app.service.CongNgheService;
import com.minhtien.app.service.DieuHoaService;
import com.minhtien.app.service.LoaiMayService;
import com.minhtien.app.service.LoaiSanPhamService;
import com.minhtien.app.service.ThuongHieuService;

@Controller
public class FilterProductController {

	@Autowired
	private DieuHoaService dieuHoaService;

	@Autowired
	private CongNgheService congNgheService;

	@Autowired
	private LoaiSanPhamService loaiSanPhamService;

	@Autowired
	private LoaiMayService loaiMayService;

	@Autowired
	private ThuongHieuService thuongHieuService;

	@Autowired
	private EntityManager em;

	@RequestMapping(value = "/filter")
	public String fiterProduct(Model model) {
		List<CongNghe> congNghes = congNgheService.listAll();
		List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.listAll();
		List<LoaiMay> loaiMays = loaiMayService.listAll();
		List<ThuongHieu> thuongHieus = thuongHieuService.listAll();
		

//		model.addAttribute("max", maxprice.getPrice());
//		model.addAttribute("min", minprice.getPrice());
		model.addAttribute("congnghes", congNghes);
		model.addAttribute("loaisanphams", loaiSanPhams);
		model.addAttribute("loaimays", loaiMays);
		model.addAttribute("thuonghieus", thuongHieus);

		return "views/categories-grid";
	}

	public StringBuilder query = new StringBuilder();

	@RequestMapping(value = "search/{min}/{max}/{lm}/{lsp}/{th}/{cn}", method = RequestMethod.GET, produces = {
			MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = { "Accept=application/json" })
	public ResponseEntity<List<DieuHoa>> search(@PathVariable("min") String min, @PathVariable("max") String max,
			@PathVariable("lm") List<String> lm, @PathVariable("lsp") List<String> lsp,
			@PathVariable("th") List<String> th, @PathVariable("cn") List<String> cn) {
//		Comparator<DieuHoa> comparator = Comparator.comparing(DieuHoa::getPrice);
//		List<DieuHoa> dieuHoas = dieuHoaService.listAll();
//		DieuHoa maxprice = dieuHoas.stream().max(comparator).get();
//
//		if(Integer.parseInt(min) == 0) {
//			max = String.valueOf(maxprice.getPrice());
//		}else {
//			
//		}

		query.append("select d from DieuHoa d where price between ");
		query.append(min);
		query.append(" and ");
		query.append(max);

		int lenglm = lm.size();
		for (int i = 0; i < lenglm; i++) {
			if (i == 0 && Integer.parseInt(lm.get(i)) > 0) {
				query.append(" and d.loaiMay.id = ");
				query.append(lm.get(i));
			}
			if (i >= 1) {
				query.append(" or d.loaiMay.id = ");
				query.append(lm.get(i));
			}
		}
		int lenglsp = lsp.size();
		for (int i = 0; i < lenglsp; i++) {
			if (i == 0 && Integer.parseInt(lsp.get(i)) > 0) {
				query.append(" and d.loaiSanPham.id = ");
				query.append(lsp.get(i));
			}
			if (i >= 1) {
				query.append(" or d.loaiSanPham.id = ");
				query.append(lsp.get(i));
			}
		}
		
		int length = th.size();
		for (int i = 0; i < length; i++) {
			if (i == 0 && Integer.parseInt(th.get(i)) > 0) {
				query.append(" and d.thuongHieu.id = ");
				query.append(th.get(i));
			}
			if (i >= 1) {
				query.append(" or d.thuongHieu.id = ");
				query.append(th.get(i));
			}
		}
		
		int lengcn = cn.size();
		for (int i = 0; i < lengcn; i++) {
			if (i == 0 && Integer.parseInt(cn.get(i)) > 0) {
				query.append(" and d.congNghe.id = ");
				query.append(cn.get(i));
			}
			if (i >= 1) {
				query.append(" or d.congNghe.id = ");
				query.append(cn.get(i));
			}
		}
		System.out.println(query);
		System.out.println(query.length());

		try {
			TypedQuery<DieuHoa> listQuery = em.createQuery(query.toString(), DieuHoa.class);
			query.delete(0, query.length());
			System.out.println(query.length());
			return new ResponseEntity<List<DieuHoa>>(listQuery.getResultList(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<DieuHoa>>(HttpStatus.BAD_REQUEST);
		}
	}

}
