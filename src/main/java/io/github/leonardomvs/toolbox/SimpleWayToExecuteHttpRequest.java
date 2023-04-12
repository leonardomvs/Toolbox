package io.github.leonardomvs.toolbox;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class SimpleWayToExecuteHttpRequest {
	
	private String url;
	private String requestBody;
	private String mediaType;
	private String requestMethod;
	private Map<String, String> headers;
	
	public SimpleWayToExecuteHttpRequest() {
		this.mediaType = "text/plain";
	}
	
	private void setHeaders(HttpURLConnection con) {
		if(headers == null){ return; }
		headers.forEach((key, value) -> con.setRequestProperty(key, value));
	}
	
	public String executeHttpRequest() throws IOException {
					
		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.addRequestProperty("Content-Type", mediaType + "; charset=utf-8");
		con.setRequestMethod(requestMethod);
		
		setHeaders(con);
		
		if(requestBody != null) {
			
			con.setDoOutput(true);
			
			try(DataOutputStream out = new DataOutputStream(con.getOutputStream())){
				out.writeBytes(requestBody);
				out.flush();
			}

		}

		StringBuilder response = new StringBuilder();
		
		try(BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))){
			String inputLine;
				
			while((inputLine = in.readLine()) != null) {
				response.append(new String(inputLine.getBytes(StandardCharsets.UTF_8))); 
			}
			
		}

		return response.toString();

	}
	
}
