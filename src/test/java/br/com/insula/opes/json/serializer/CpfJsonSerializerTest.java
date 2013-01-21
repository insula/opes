package br.com.insula.opes.json.serializer;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.Cpf;

public class CpfJsonSerializerTest {

	@Test
	public void testSerializeCpfJsonGeneratorSerializerProvider() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addSerializer(Cpf.class, new CpfJsonSerializer());
		mapper.registerModule(module);

		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, new Teste());
		assertEquals("{\"cpf\":\"00000000191\"}", writer.toString());
	}

	static class Teste {
		private Cpf cpf = Cpf.fromString("000.000.001-91");

		public Cpf getCpf() {
			return cpf;
		}

		public void setCpf(Cpf cpf) {
			this.cpf = cpf;
		}
	}
}
