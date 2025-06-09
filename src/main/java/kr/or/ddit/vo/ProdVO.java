package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.constraints.FileNotEmpty;
import kr.or.ddit.validate.constraints.MimeTypeCheck;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 상품 관리를 위한 Domain Layer (JavaBean)
 * 객체 생성 패턴인 Builder 퍁천 구현 단계
 * 1. 모든 프로퍼티를 한번에 받을 수 있는 생성자 정의 ex) @AllArgsConstructor
 * 2. 해당 생성자는 이 클래스 내부에서만 사용할 수 있도록 접근 제한(private) 선언 -> @AllArgsConstructor(access = AccessLevel.PRIVATE)
 * 3. 외부에서 객체 생성에 사용할 수 있도록 static 내부 클래스로 Builder 구현
 * 	  ex) ProdVoBuilder(@Builder 으로 생성한 빌더)
 * 4. Builder 내부에서 Build() 메소드로 객체를 생성함. (빈 객체 생성됨)
 * 5. build 대상이 되는 객체가 가진 프로퍼치를 결정할 수 있는 setter 역할 메서드 구현
 * 	  -> 메소드 체이닝 구조를 위해 해당 메소드 내부에서 Builder 가 다시 반환됨
 * 
 * ★★★★★ Mybatis를 이용해서 여러개의 테이블을 조인하는 방법
 * 1. 사용할 테이블의 관계를 파악 (메인 테이블을 중심으로 파악)
 * 		1 : 1	=>	PROD(1) : BUYER(1) - 자식은 부모를 하나만 가질 수 있음
 * 		1 : N	=>	BUYER(1): PROD(N) - 부모는 여러명의 자식을 가질 수 있음
 * 
 * 2. 조인 쿼리 작성
 * 3. 조인한 테이블 갯수만큼 VO(D.L) 생성
 * 		ProdVO, BuyerVO
 * 4. 테이블의 구조를 VO에 반영.
 *		PROD(1) : BUYER(1)
 *		-> ProdVO has A(한개) BuyerVO
 *		BUYER(1): PROD(N)
 *		-> BuyerVO has Many ProdVO
 * 5. resultType 대신 resultMap 수동 바인드 설정
 * 		1:1 (has A) 	=> association 으로 바인드
 * 		1:N (has Many)  => collection 으로 바인드
 * 
 */
@Data
@EqualsAndHashCode(of="prodId")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ProdVO implements Serializable{
	@NotBlank(groups = UpdateGroup.class)
	private String prodId;
	@NotBlank
	private String prodName;
	@NotBlank
	private String lprodGu;
	@NotBlank
	private String buyerId;
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	@ToString.Exclude
	private String prodDetail;
	// PROD 엔터티 컬럼 바인드용 
	private String prodImg;
	// 업로드되는 파일 바인드용 
//	@NotNull
//	@FileNotEmpty
	@MimeTypeCheck(mainType = "image/")
	private MultipartFile prodImage;
	private Integer prodTotalstock;
	private Date prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private LprodVO lprod;	// has A 관계 (소유격 - pord가 다른 vo들을 가지고 있다)
	private BuyerVO buyer;	// has 관계 
}
