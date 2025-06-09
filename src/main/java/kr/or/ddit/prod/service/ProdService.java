package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리(CRUD) business logic layer 
 */
public interface ProdService {
	public void createProd(ProdVO prod);
	public List<ProdVO> readProdList();
	public Optional<ProdVO> readProd(String prodId);
	public void modifyProd(ProdVO prod);
}
