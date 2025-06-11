package kr.or.ddit.member.service;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

/**
 * Layered Architecture : 하나의 명령이 처리되는 동안 순차적으로 객체들이 동작하는 구조
 * 		계층을 구성하고 있는 객체들간의 (의존)관계를 기반으로 한 설계 구조
 */
@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {
	private final MemberMapper dao; 
	private final PasswordEncoder pe;
	
	@Override
	public boolean authenticate(MemberVO inputData) {
		MemberVO saved = dao.selectMember(inputData.getMemId());
		if(saved!=null) {
			String inputPass = inputData.getMemPassword();
			String savedPass = saved.getMemPassword();
			boolean result = pe.matches(inputPass, savedPass);
			if(result) {
				BeanUtils.copyProperties(saved, inputData);
			}
			return result;
		} else {
			return false;
		}
	}

}
