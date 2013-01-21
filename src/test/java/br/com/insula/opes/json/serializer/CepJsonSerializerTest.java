package br.com.insula.opes.json.serializer;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.Cep;

public class CepJsonSerializerTest {

	@Test
	public void testSerializeCepJsonGeneratorSerializerProvider() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addSerializer(Cep.class, new CepJsonSerializer());
		mapper.registerModule(module);

		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, new Teste());
		assertEquals("{\"cep\":\"87030020\"}", writer.toString());
	}

	static class Teste {
		private Cep cep = Cep.fromString("87030020");

		public Cep getCep() {
			return cep;
		}

		public void setCep(Cep cpfCnpj) {
			this.cep = cpfCnpj;
		}
	}
}
