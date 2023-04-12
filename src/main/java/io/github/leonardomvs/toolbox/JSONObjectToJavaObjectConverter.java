package io.github.leonardomvs.toolbox;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONObjectToJavaObjectConverter {

	public <T> T convert(String jsonStr, Class<T> valueType) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonStr, valueType);
	}

}
