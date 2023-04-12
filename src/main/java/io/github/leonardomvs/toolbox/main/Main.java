package io.github.leonardomvs.toolbox.main;

import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.leonardomvs.toolbox.JSONObjectToJavaObjectConverter;
import io.github.leonardomvs.toolbox.JavaObjectToJSONObjectConverter;
import io.github.leonardomvs.toolbox.SimpleWayToExecuteHttpRequestTester;
import io.github.leonardomvs.toolbox.entity.User;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Main main = new Main();
		
		main.testJavaObjectToJSONObject();
		main.testJSONObjectToJavaObjectMountingWithString();
		main.testJSONObjectToJavaObjectMountingWithApi();
		main.testSimpleWayToExecuteHttpRequest();
				
	}
	
	public void testJavaObjectToJSONObject() throws JsonProcessingException {
		
		User user = User.builder()
				.name("Leonardo Silveira")
				.email("leonardomvs@gmail.com")
				.build();
		
		String jsonObj = new JavaObjectToJSONObjectConverter().convert(user);
		
		System.out.println(jsonObj);
		
	}
	
	public void testJSONObjectToJavaObjectMountingWithString() throws IOException {
		
		String userAsJSON = new StringBuilder()
				.append("{")
				.append("	\"name\":\"Leonardo Maciel Verissimo da Silveira\",")
				.append("	\"email\":\"leonardomvs@gmail.com\"")
				.append("}")
				.toString();
		
		User user = new JSONObjectToJavaObjectConverter().convert(userAsJSON, User.class);
		
		System.out.println(user);
		
	}
	
	public void testJSONObjectToJavaObjectMountingWithApi() throws IOException {
		
		JSONObject userAsJSONObject = new JSONObject();
		
		userAsJSONObject.put("name", "Leonardo M. V. Silveira");
		userAsJSONObject.put("email", "leonardomvs@gmail.com");
		
		User user = new JSONObjectToJavaObjectConverter().convert(userAsJSONObject.toString(), User.class);
		
		System.out.println(user);
				
	}
	
	public void testSimpleWayToExecuteHttpRequest() throws IOException {
		new SimpleWayToExecuteHttpRequestTester().testSimpleWayToExecuteHttpRequest();
	}
		
}