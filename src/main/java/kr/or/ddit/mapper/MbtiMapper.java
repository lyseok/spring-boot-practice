package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.MbtiVO;

/**
 * Persistent Layer : 테이블을 대상으로 CRUD 메소드(사용할 쿼리 반영)가 기본 요건
 * 
 *   MyBatis에서는 Mapper인터페이스와 Mapper xml이 1:1 구조로 개발됨
 */
@Mapper
public interface MbtiMapper {
	public int insertMbti(MbtiVO mbti);
	/**
	 * 다건 조회, 데이터 매퍼는 단건조회에서 null값을 반환하지 않음. empty list 반환
	 * @return
	 */
	public List<MbtiVO> selectMbtiList();
	/**
	 * 단건 조회 
	 * @param mtType
	 * @return 검색 조건에 해당하는 데이터가 없는 경우, null 값이 반환됨
	 */
	public MbtiVO selectMbti(String mtType);
	public int updateMbti(MbtiVO mbti);
	public int deleteMbti(String mtType);
}
