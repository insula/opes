package br.com.insula.opes.json.deserializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.InscricaoEstadual;

public class InscricaoEstadualJsonDeserializerTest {

	@Test
	public void testDeserializeJsonParserDeserializationContext() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"ie\":\"9031525087\"}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertEquals(InscricaoEstadual.fromString("9031525087"), teste.getIe());
	}

	private ObjectMapper createObjectMapper() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addDeserializer(InscricaoEstadual.class, new InscricaoEstadualJsonDeserializer());
		objectMapper.registerModule(module);
		return objectMapper;
	}

	@Test
	public void testDeserializeJsonParserDeserializationContextWithNull() throws Exception {
		ObjectMapper mapper = createObjectMapper();

		String json = "{\"ie\":null}";
		Teste teste = mapper.readValue(json, Teste.class);
		assertNull(teste.getIe());
	}

	static class Teste {
		private InscricaoEstadual ie;

		public InscricaoEstadual getIe() {
			return ie;
		}

		public void setIe(InscricaoEstadual cep) {
			this.ie = cep;
		}
	}
}
