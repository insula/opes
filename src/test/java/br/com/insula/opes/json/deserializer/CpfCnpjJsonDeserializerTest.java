package br.com.insula.opes.json.deserializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.CpfCnpj;

public class CpfCnpjJsonDeserializerTest {

	@Test
	public void testDeserializeJsonParserDeserializationContext() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"cpfCnpj\":\"06305901000178\"}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertEquals(CpfCnpj.fromString("06305901000178"), teste.getCpfCnpj());
	}

	private ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addDeserializer(CpfCnpj.class, new CpfCnpjJsonDeserializer());
		objectMapper.registerModule(module);
		return objectMapper;
	}

	@Test
	public void testDeserializeJsonParserDeserializationContextWithNull() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"cpfCnpj\":null}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertNull(teste.getCpfCnpj());
	}

	static class Teste {
		private CpfCnpj cpfCnpj;

		public CpfCnpj getCpfCnpj() {
			return cpfCnpj;
		}

		public void setCpfCnpj(CpfCnpj cep) {
			this.cpfCnpj = cep;
		}
	}
}
