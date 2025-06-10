package kr.or.ddit.buyer.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	private void processImage(BuyerVO buyer) {
		try {
			MultipartFile buyerImage = buyer.getBuyerImage();
			if(buyerImage == null || buyerImage.isEmpty()) return;
			byte[] buyerImg = buyerImage.getBytes();
			buyer.setBuyerImg(buyerImg);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void createBuyer(BuyerVO buyer) {
		processImage(buyer);
		mapper.insertBuyer(buyer);
	}

	@Override
	public void modifyBuyer(BuyerVO buyer) {
		processImage(buyer);
		mapper.updateBuyer(buyer);
	}

}
