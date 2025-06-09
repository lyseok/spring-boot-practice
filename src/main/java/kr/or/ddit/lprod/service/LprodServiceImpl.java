package kr.or.ddit.lprod.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.LprodMapper;
import kr.or.ddit.vo.LprodVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LprodServiceImpl implements LprodService {
	private final LprodMapper mapper;
	
	@Override
	public List<LprodVO> readLprodList() {
		return mapper.selectLprodList();
	}

}
