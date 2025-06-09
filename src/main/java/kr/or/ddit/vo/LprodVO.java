package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="lprodGu")
public class LprodVO implements Serializable{
	private Integer lprodId;
	private String lprodGu;
	private String lprodName;
}
