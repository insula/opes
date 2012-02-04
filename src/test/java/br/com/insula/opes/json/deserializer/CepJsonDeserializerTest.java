package br.com.insula.opes.json.deserializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.Cep;

public class CepJsonDeserializerTest {

	@Test
	public void testDeserializeJsonParserDeserializationContext() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"cep\":\"87030020\"}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertEquals(Cep.fromString("87030020"), teste.getCep());
	}

	private ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addDeserializer(Cep.class, new CepJsonDeserializer());
		objectMapper.registerModule(module);
		return objectMapper;
	}

	@Test
	public void testDeserializeJsonParserDeserializationContextWithNull() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"cep\":null}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertNull(teste.getCep());
	}

	static class Teste {
		private Cep cep;

		public Cep getCep() {
			return cep;
		}

		public void setCep(Cep cep) {
			this.cep = cep;
		}
	}
}
