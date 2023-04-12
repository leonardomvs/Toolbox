package io.github.leonardomvs.toolbox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.github.leonardomvs.toolbox.dto.CredentialsDTO;
import io.github.leonardomvs.toolbox.dto.TokenDTO;

public class SimpleWayToExecuteHttpRequestTester {
	
	public void testSimpleWayToExecuteHttpRequest() throws IOException {
		
		CredentialsDTO dto = CredentialsDTO.builder()
				.email("leonardomvs@gmail.com")
				.password("12345678")
				.build();
		
		String requestBody = new JavaObjectToJSONObjectConverter().convert(dto);
		
		SimpleWayToExecuteHttpRequest req = SimpleWayToExecuteHttpRequest
				.builder()
				.url("http://localhost:8080/api/auth")
				.requestMethod("POST")				
				.mediaType("application/json")
				.requestBody(requestBody)
				.build();
				
		String response = req.executeHttpRequest();
		
		TokenDTO tokenDto = new JSONObjectToJavaObjectConverter().convert(response, TokenDTO.class);
		
		System.out.println(tokenDto);
		
		req = SimpleWayToExecuteHttpRequest
				.builder()
				.url("http://localhost:8080/api/helloprivate")
				.requestMethod("GET")
				.headers(generateHeadersMap(tokenDto))
				.build();
		
		System.out.println(req.executeHttpRequest());
		
	}
	
	private Map<String, String> generateHeadersMap(TokenDTO tokenDto) {
		
		String key = "Authorization";
		String value = new StringBuilder().append("Bearer ").append(tokenDto.getToken()).toString();
		
		Map<String, String> map = new HashMap<>();		
		map.put(key, value);		
		return map;
		
	}
	
}
