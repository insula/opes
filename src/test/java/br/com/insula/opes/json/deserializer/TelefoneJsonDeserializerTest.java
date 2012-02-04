package br.com.insula.opes.json.deserializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.Telefone;

public class TelefoneJsonDeserializerTest {

	@Test
	public void testDeserializeJsonParserDeserializationContext() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"telefone\":\"4440529211\"}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertEquals(Telefone.fromString("4440529211"), teste.getTelefone());
	}

	private ObjectMapper createObjectMapper() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addDeserializer(Telefone.class, new TelefoneJsonDeserializer());
		objectMapper.registerModule(module);
		return objectMapper;
	}

	@Test
	public void testDeserializeJsonParserDeserializationContextWithNull() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"telefone\":null}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertNull(teste.getTelefone());
	}

	static class Teste {
		private Telefone telefone;

		public Telefone getTelefone() {
			return telefone;
		}

		public void setTelefone(Telefone cep) {
			this.telefone = cep;
		}
	}
}
