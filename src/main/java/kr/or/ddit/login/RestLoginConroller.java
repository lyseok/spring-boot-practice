package kr.or.ddit.login;

import java.util.Base64;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestLoginConroller {
	private final AuthenticationManager authenticationManager;
	
	@PostMapping("/rest/auth")
	public ResponseEntity<?> authenticate(@RequestBody RestAuthVO auth) {
		UsernamePasswordAuthenticationToken inputData =
				UsernamePasswordAuthenticationToken
					.unauthenticated(auth.getUsername(), auth.getPassword());
		try {
			authenticationManager.authenticate(inputData);
			String tokenValue = auth.getUsername() + ":" + auth.getPassword();
			String encodedToken = Base64.getEncoder().encodeToString(tokenValue.getBytes());
			
			return ResponseEntity.ok()
						.body(Map.of("token", encodedToken));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
						.body(Map.of("error", 401, "message", e.getMessage()));
		}
	}
}
