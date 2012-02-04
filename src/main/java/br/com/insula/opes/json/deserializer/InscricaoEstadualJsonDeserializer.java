package br.com.insula.opes.json.deserializer;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import br.com.insula.opes.InscricaoEstadual;

public class InscricaoEstadualJsonDeserializer extends JsonDeserializer<InscricaoEstadual> {

	@Override
	public InscricaoEstadual deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return InscricaoEstadual.fromString(jp.getText());
	}

}