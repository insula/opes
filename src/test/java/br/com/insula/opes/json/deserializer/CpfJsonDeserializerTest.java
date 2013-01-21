package br.com.insula.opes.json.deserializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.Cpf;

public class CpfJsonDeserializerTest {

	@Test
	public void testDeserializeJsonParserDeserializationContext() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"cpf\":\"00000000191\"}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertEquals(Cpf.fromString("000.000.001-91"), teste.getCpf());
	}

	private ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addDeserializer(Cpf.class, new CpfJsonDeserializer());
		objectMapper.registerModule(module);
		return objectMapper;
	}

	@Test
	public void testDeserializeJsonParserDeserializationContextWithNull() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"cpf\":null}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertNull(teste.getCpf());
	}

	static class Teste {
		private Cpf cpf;

		public Cpf getCpf() {
			return cpf;
		}

		public void setCpf(Cpf cpf) {
			this.cpf = cpf;
		}
	}
}
