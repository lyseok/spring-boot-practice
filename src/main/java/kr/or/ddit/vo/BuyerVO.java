package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.constraints.MimeTypeCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 하나의 제조사 정보
 * 하나의 제조사 분류 정보
 * N개의 거래품목 정보를 담기 위한 Domain
 * 
 * 제조사 관리용 Domain layer
 */
@Data
@EqualsAndHashCode(of="buyerId")
public class BuyerVO implements Serializable{
	@NotBlank(groups = {UpdateGroup.class, DeleteGroup.class})
	private String buyerId;
	@NotBlank
	private String buyerName;
	@NotBlank
	private String lprodGu;
	@NotBlank
	private String buyerBank;
	@NotBlank
	private String buyerBankno;
	@NotBlank
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	@NotBlank
	private String buyerComtel;
	private String buyerFax;
	@NotBlank
	@Email
	private String buyerMail;
	@NotBlank
	private String buyerCharger;
	private String buyerTelext;
	
//	@NotNull
	private transient LprodVO lprod;	// has a
	
//	@NotNull
//	@NotEmpty
	private transient List<ProdVO> prodList; // has Many
	
	// buyer 엔터티의 buyerImg컬럼 바인드
	private byte[] buyerImg;

	// 업로드 이미지 바인드용 
	@MimeTypeCheck(mainType = "image")
	private MultipartFile buyerImage;
	
	public String getBuyerImgBase64() {
		if(buyerImg == null) return null;
		return Base64.getEncoder().encodeToString(buyerImg);
	}
}

