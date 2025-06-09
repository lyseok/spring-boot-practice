package kr.or.ddit.buyer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.BuyerMapper;
import kr.or.ddit.vo.BuyerVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
	private final BuyerMapper mapper;
	
	@Override
	public Optional<BuyerVO> readBuyer(String buyerId) {
		return Optional.ofNullable(mapper.selectBuyer(buyerId));
	}

	@Override
	public List<BuyerVO> readBuyerList() {
		return mapper.selectBuyerList();
	}

	@Override
	public void createBuyer(BuyerVO buyer) {
		mapper.insertBuyer(buyer);
	}

	@Override
	public void modifyBuyer(BuyerVO buyer) {
		mapper.updateBuyer(buyer);
	}

}
