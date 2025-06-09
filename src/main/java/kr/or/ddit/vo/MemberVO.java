package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
//@Getter
//@Setter
@EqualsAndHashCode(of = "memId")
//@ToString
public class MemberVO implements Serializable{
	private String memId;
	private String memPassword;
	private String memName;
	@ToString.Exclude
	private transient String memRegno1;
	@ToString.Exclude
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memHobby;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private String memDelete;
}
