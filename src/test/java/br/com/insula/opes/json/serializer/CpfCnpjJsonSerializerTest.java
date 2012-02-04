package br.com.insula.opes.json.serializer;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.CpfCnpj;

public class CpfCnpjJsonSerializerTest {

	@Test
	public void testSerializeEmailJsonGeneratorSerializerProvider() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addSerializer(CpfCnpj.class, new CpfCnpjJsonSerializer());
		mapper.registerModule(module);

		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, new Teste());
		assertEquals("{\"cpfCnpj\":\"06305901000178\"}", writer.toString());
	}

	static class Teste {
		private CpfCnpj cpfCnpj = CpfCnpj.fromString("06305901000178");

		public CpfCnpj getCpfCnpj() {
			return cpfCnpj;
		}

		public void setCpfCnpj(CpfCnpj cpfCnpj) {
			this.cpfCnpj = cpfCnpj;
		}
	}
}
