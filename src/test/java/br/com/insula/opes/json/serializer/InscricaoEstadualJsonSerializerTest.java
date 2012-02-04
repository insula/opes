package br.com.insula.opes.json.serializer;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.InscricaoEstadual;

public class InscricaoEstadualJsonSerializerTest {

	@Test
	public void testSerializeEmailJsonGeneratorSerializerProvider() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addSerializer(InscricaoEstadual.class, new InscricaoEstadualJsonSerializer());
		mapper.registerModule(module);

		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, new Teste());
		assertEquals("{\"ie\":\"9031525087\"}", writer.toString());
	}

	static class Teste {
		private InscricaoEstadual ie = InscricaoEstadual.fromString("9031525087");

		public InscricaoEstadual getIe() {
			return ie;
		}

		public void setIe(InscricaoEstadual cpfCnpj) {
			this.ie = cpfCnpj;
		}
	}
}
