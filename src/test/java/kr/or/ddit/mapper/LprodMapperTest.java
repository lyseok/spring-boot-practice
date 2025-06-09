package kr.or.ddit.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LprodMapperTest {
	@Autowired
	LprodMapper mapper;
	
	@Test
	void testSelectLprodList() {
		assertDoesNotThrow(() -> mapper.selectLprodList());
	}

}
