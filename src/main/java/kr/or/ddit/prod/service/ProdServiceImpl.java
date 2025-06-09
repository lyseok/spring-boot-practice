package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.mapper.ProdMapper;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdServiceImpl implements ProdService {
	private final ProdMapper mapper;
	
	@Value("${imagesFolder}")
	private File imagesFolder;
	
	private void processImage(ProdVO prod) {    
		MultipartFile prodImage = prod.getProdImage();
		if(prodImage != null && !prodImage.isEmpty()) {
			// 메타데이터 분리 
			String saveName = UUID.randomUUID().toString();
			prod.setProdImg(saveName);
			// binary 데이터 저장, 저장위치 : Fileinfo.properties, FileinfoContextConfig
			File saveFile = new File(imagesFolder, saveName);
			try {
				prodImage.transferTo(saveFile);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override
	public void createProd(ProdVO prod) {
		processImage(prod);
		mapper.insertProd(prod);
	}

	@Override
	public List<ProdVO> readProdList() {
		return mapper.selectProdList();
	}

	@Override
	public Optional<ProdVO> readProd(String prodId) {
		return Optional.ofNullable(mapper.selectProd(prodId));
	}

	@Override
	public void modifyProd(ProdVO prod) {
		processImage(prod);
		mapper.updateProd(prod);
	}

}
