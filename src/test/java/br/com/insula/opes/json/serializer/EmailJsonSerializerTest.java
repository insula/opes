package br.com.insula.opes.json.serializer;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import br.com.insula.opes.Email;

public class EmailJsonSerializerTest {

	@Test
	public void testSerializeEmailJsonGeneratorSerializerProvider() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("OpesModule", new Version(1, 0, 0, null));
		module.addSerializer(Email.class, new EmailJsonSerializer());
		mapper.registerModule(module);

		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, new Teste());
		assertEquals("{\"meuEmail\":\"eu@email.com\"}", writer.toString());
	}

	static class Teste {
		private Email meuEmail = Email.fromString("eu@email.com");

		public Email getMeuEmail() {
			return meuEmail;
		}

		public void setMeuEmail(Email meuEmail) {
			this.meuEmail = meuEmail;
		}
	}
}
