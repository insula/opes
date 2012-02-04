package br.com.insula.opes.json.serializer;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.Telefone;

public class TelefoneJsonSerializerTest {

	@Test
	public void testSerializeEmailJsonGeneratorSerializerProvider() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addSerializer(Telefone.class, new TelefoneJsonSerializer());
		mapper.registerModule(module);

		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, new Teste());
		assertEquals("{\"telefone\":\"4440529211\"}", writer.toString());
	}

	static class Teste {
		private Telefone telefone = Telefone.fromString("4440529211");

		public Telefone getTelefone() {
			return telefone;
		}

		public void setTelefone(Telefone cpfCnpj) {
			this.telefone = cpfCnpj;
		}
	}
}
