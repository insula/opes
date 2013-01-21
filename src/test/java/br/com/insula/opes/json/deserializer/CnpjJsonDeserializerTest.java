package br.com.insula.opes.json.deserializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.Cnpj;

public class CnpjJsonDeserializerTest {

	@Test
	public void testDeserializeJsonParserDeserializationContext() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"cnpj\":\"86266937000129\"}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertEquals(Cnpj.fromString("86.266.937/0001-29"), teste.getCnpj());
	}

	private ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addDeserializer(Cnpj.class, new CnpjJsonDeserializer());
		objectMapper.registerModule(module);
		return objectMapper;
	}

	@Test
	public void testDeserializeJsonParserDeserializationContextWithNull() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"cnpj\":null}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertNull(teste.getCnpj());
	}

	static class Teste {
		private Cnpj cnpj;

		public Cnpj getCnpj() {
			return cnpj;
		}

		public void setCnpj(Cnpj cnpj) {
			this.cnpj = cnpj;
		}
	}
}
