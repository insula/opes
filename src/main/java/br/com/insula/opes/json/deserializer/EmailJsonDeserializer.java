package br.com.insula.opes.json.deserializer;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import br.com.insula.opes.Email;

public class EmailJsonDeserializer extends JsonDeserializer<Email> {

	@Override
	public Email deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return Email.fromString(jp.getText());
	}

}