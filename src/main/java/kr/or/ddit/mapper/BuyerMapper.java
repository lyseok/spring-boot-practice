package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BuyerVO;

/**
 * 제조사 관리용 Persistence layer
 */
@Mapper
public interface BuyerMapper {
	public BuyerVO selectBuyer(String buyerId);
	public List<BuyerVO> selectBuyerList();
	public int insertBuyer(BuyerVO buyer);
	public int updateBuyer(BuyerVO buyer);
}
