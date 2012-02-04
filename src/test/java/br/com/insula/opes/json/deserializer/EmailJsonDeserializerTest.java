package br.com.insula.opes.json.deserializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.Email;

public class EmailJsonDeserializerTest {

	@Test
	public void testDeserializeJsonParserDeserializationContext() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"email\":\"eu@mail.com\"}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertEquals(Email.fromString("eu@mail.com"), teste.getEmail());
	}

	private ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addDeserializer(Email.class, new EmailJsonDeserializer());
		objectMapper.registerModule(module);
		return objectMapper;
	}

	@Test
	public void testDeserializeJsonParserDeserializationContextWithNull() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"email\":null}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertNull(teste.getEmail());
	}

	static class Teste {
		private Email email;

		public Email getEmail() {
			return email;
		}

		public void setEmail(Email cep) {
			this.email = cep;
		}
	}
}
