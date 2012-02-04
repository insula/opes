package br.com.insula.opes.json.deserializer;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import br.com.insula.opes.CpfCnpj;

public class CpfCnpjJsonDeserializer extends JsonDeserializer<CpfCnpj> {

	@Override
	public CpfCnpj deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return CpfCnpj.fromString(jp.getText());
	}

}