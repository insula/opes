package br.com.insula.opes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.insula.opes.util.Assert;

public final class CertificadoDigital implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String C14N_TRANSFORM_METHOD = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";

	private static final String DEFAULT_CERTIFICATE_PASSWORD = "opes";

	private final transient byte[] bytes;

	private final transient PrivateKey privateKey;

	private final transient X509Certificate[] certificateChain;

	private CertificadoDigital(byte[] bytes, PrivateKey privateKey, X509Certificate[] certificateChain) {
		this.bytes = bytes;
		this.privateKey = privateKey;
		this.certificateChain = certificateChain;
	}

	public static final CertificadoDigital load(byte[] bytes) {
		return load(new ByteArrayInputStream(bytes), DEFAULT_CERTIFICATE_PASSWORD);
	}

	public static final CertificadoDigital load(InputStream in) {
		return load(in, DEFAULT_CERTIFICATE_PASSWORD);
	}

	public static final CertificadoDigital load(InputStream in, String password) {
		try {
			char[] charArray = password.toCharArray();

			KeyStore keyStore = KeyStore.getInstance("pkcs12");
			keyStore.load(in, charArray);

			for (Enumeration<String> aliases = keyStore.aliases(); aliases.hasMoreElements();) {
				String alias = aliases.nextElement();
				if (!keyStore.isKeyEntry(alias)) {
					continue;
				}
				Key key = keyStore.getKey(alias, charArray);
				if (!(key instanceof PrivateKey)) {
					continue;
				}
				Certificate[] certs = keyStore.getCertificateChain(alias);
				if ((certs == null) || (certs.length == 0) || !(certs[0] instanceof X509Certificate)) {
					continue;
				}
				if (!(certs instanceof X509Certificate[])) {
					Certificate[] tmp = new X509Certificate[certs.length];
					System.arraycopy(certs, 0, tmp, 0, certs.length);
					certs = tmp;
				}

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				keyStore.store(baos, DEFAULT_CERTIFICATE_PASSWORD.toCharArray());

				return new CertificadoDigital(baos.toByteArray(), (PrivateKey) key, (X509Certificate[]) certs);
			}
			throw new IllegalArgumentException("Unable to load PrivateKey and CertificateChain from KeyStore.");
		}
		catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public byte[] getBytes() {
		return bytes;
	}

	public Node sign(Node node) {
		Assert.notNull(node);
		Assert.isTrue(node instanceof Document || node instanceof Element);
		try {
			Element element = node instanceof Document ? ((Document) node).getDocumentElement() : (Element) node;
			DOMSignContext dsc = new DOMSignContext(privateKey, element);
			XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");

			List<Transform> transformList = new LinkedList<Transform>();
			transformList.add(signatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));
			transformList.add(signatureFactory.newTransform(C14N_TRANSFORM_METHOD, (TransformParameterSpec) null));

			Node child = findFirstElementChild(element);

			String id = child.getAttributes().getNamedItem("Id").getNodeValue();
			String uri = String.format("#%s", id);
			Reference reference = signatureFactory.newReference(uri,
					signatureFactory.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);

			SignedInfo signedInfo = signatureFactory.newSignedInfo(signatureFactory.newCanonicalizationMethod(
					CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null), signatureFactory
					.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(reference));

			KeyInfoFactory kif = signatureFactory.getKeyInfoFactory();
			X509Data x509Data = kif.newX509Data(Collections.singletonList(certificateChain[0]));
			KeyInfo keyInfo = kif.newKeyInfo(Collections.singletonList(x509Data));

			XMLSignature xmlSignature = signatureFactory.newXMLSignature(signedInfo, keyInfo);

			xmlSignature.sign(dsc);

			return node;
		}
		catch (Exception ex) {
			throw new IllegalArgumentException("Erro ao assinar XML.", ex);
		}
	}

	private Node findFirstElementChild(Node node) {
		NodeList childNodes = node.getChildNodes();
		Node child = null;
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				child = childNode;
				break;
			}
		}
		return child;
	}

	public boolean validate(Document document) {
		Assert.notNull(document);
		try {
			NodeList nl = document.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
			DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(), nl.item(0));

			XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
			XMLSignature signature = signatureFactory.unmarshalXMLSignature(valContext);

			boolean coreValidity = signature.validate(valContext);
			return coreValidity;
		}
		catch (Exception ex) {
			throw new IllegalArgumentException("Erro ao validar o XML.", ex);
		}
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		throw new InvalidObjectException("Proxy required");
	}

	private Object writeReplace() {
		return new SerializationProxy(this);
	}

	private static class SerializationProxy implements Serializable {

		private static final long serialVersionUID = 1L;

		private final byte[] bytes;

		SerializationProxy(CertificadoDigital certificadoDigital) {
			this.bytes = certificadoDigital.bytes;
		}

		private Object readResolve() {
			return CertificadoDigital.load(bytes);
		}

	}

	private static class X509KeySelector extends KeySelector {

		public KeySelectorResult select(KeyInfo keyInfo, KeySelector.Purpose purpose, AlgorithmMethod method,
				XMLCryptoContext context) throws KeySelectorException {

			@SuppressWarnings("unchecked")
			Iterator<XMLStructure> ki = keyInfo.getContent().iterator();

			while (ki.hasNext()) {
				XMLStructure info = ki.next();
				if (info instanceof X509Data) {
					X509Data x509Data = (X509Data) info;
					@SuppressWarnings("unchecked")
					Iterator<Object> xi = x509Data.getContent().iterator();
					while (xi.hasNext()) {
						Object o = xi.next();
						if (!(o instanceof X509Certificate))
							continue;
						final PublicKey key = ((X509Certificate) o).getPublicKey();
						if (algEquals(method.getAlgorithm(), key.getAlgorithm())) {
							return new KeySelectorResult() {
								public Key getKey() {
									return key;
								}
							};
						}
					}
				}
			}
			throw new KeySelectorException("No KeyValue element found!");
		}

		static boolean algEquals(String algURI, String algName) {
			if (algName.equalsIgnoreCase("DSA") && algURI.equalsIgnoreCase(SignatureMethod.DSA_SHA1)) {
				return true;
			}
			else if (algName.equalsIgnoreCase("RSA") && algURI.equalsIgnoreCase(SignatureMethod.RSA_SHA1)) {
				return true;
			}
			else {
				return false;
			}
		}
	}

}