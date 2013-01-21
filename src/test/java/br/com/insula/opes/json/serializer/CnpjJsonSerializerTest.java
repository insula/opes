package br.com.insula.opes.json.serializer;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.Cnpj;

public class CnpjJsonSerializerTest {

	@Test
	public void testSerializeCnpjJsonGeneratorSerializerProvider() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addSerializer(Cnpj.class, new CnpjJsonSerializer());
		mapper.registerModule(module);

		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, new Teste());
		assertEquals("{\"cnpj\":\"86266937000129\"}", writer.toString());
	}

	static class Teste {
		private Cnpj cnpj = Cnpj.fromString("86.266.937/0001-29");

		public Cnpj getCnpj() {
			return cnpj;
		}

		public void setCnpj(Cnpj cnpj) {
			this.cnpj = cnpj;
		}
	}
}
