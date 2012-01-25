package br.com.insula.opes;

import static org.junit.Assert.assertTrue;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class CertificadoDigitalTest {

	private CertificadoDigital certificadoDigital;

	@Before
	public void setup() throws Exception {
		this.certificadoDigital = CertificadoDigital.load(getClass().getResourceAsStream("/test.p12"), "testcase");
	}

	@Test
	public void testSign() throws Exception {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\"><infNFe Id=\"NFe41110168816305000100550000000004430000006994\" versao=\"1.10\"><ide><cUF>41</cUF><cNF>000000699</cNF><natOp>5101</natOp><indPag>1</indPag><mod>55</mod><serie>0</serie><nNF>443</nNF><dEmi>2011-01-28</dEmi><dSaiEnt>2011-01-28</dSaiEnt><tpNF>1</tpNF><cMunFG>4115200</cMunFG><tpImp>1</tpImp><tpEmis>1</tpEmis><cDV>4</cDV><tpAmb>1</tpAmb><finNFe>1</finNFe><procEmi>0</procEmi><verProc>GlomerNF 1.0.0</verProc></ide><emit><CNPJ>68816305000100</CNPJ><xNome>RAGUS INDUSTRIA E COMERCIO DE CONFECCOES LTDA</xNome><enderEmit><xLgr>RUA GASPAR RICARDO</xLgr><nro>43</nro><xBairro>ZONA 10</xBairro><cMun>4115200</cMun><xMun>MARINGA</xMun><UF>PR</UF><CEP>87040365</CEP><fone>4432292811</fone></enderEmit><IE>7011770961</IE></emit><dest><CNPJ>09720751000157</CNPJ><xNome>NICOLL - INDUSTRIA PLASTICA LTDA</xNome><enderDest><xLgr>RODOVIA BR 376 - KM 16,5 - PORTARIA 02</xLgr><nro>S/N</nro><xBairro>BARRO PRETO</xBairro><cMun>4125506</cMun><xMun>SAO JOSE DOS PINHAIS</xMun><UF>PR</UF><CEP>83015000</CEP><fone>4132081900</fone></enderDest><IE>9045713773</IE></dest><det nItem=\"1\"><prod><cProd>001</cProd><cEAN/><xProd>CAMISA COR 815 M/CURTA TAPE MAIS MOD. ITALIANA</xProd><NCM>62059000</NCM><CFOP>5101</CFOP><uCom>UN</uCom><qCom>110.0000</qCom><vUnCom>22.7900</vUnCom><vProd>2506.90</vProd><cEANTrib/><uTrib>UN</uTrib><qTrib>110.0000</qTrib><vUnTrib>22.7900</vUnTrib></prod><imposto><ICMS><ICMS40><orig>0</orig><CST>41</CST></ICMS40></ICMS><PIS><PISOutr><CST>99</CST><qBCProd>0.0000</qBCProd><vAliqProd>0.0000</vAliqProd><vPIS>0.00</vPIS></PISOutr></PIS><COFINS><COFINSOutr><CST>99</CST><qBCProd>0.0000</qBCProd><vAliqProd>0.0000</vAliqProd><vCOFINS>0.00</vCOFINS></COFINSOutr></COFINS></imposto></det><det nItem=\"2\"><prod><cProd>002</cProd><cEAN/><xProd>CALCA COR 815 SOLASOL MOD. PROF.</xProd><NCM>62034900</NCM><CFOP>5101</CFOP><uCom>UN</uCom><qCom>170.0000</qCom><vUnCom>23.6800</vUnCom><vProd>4025.60</vProd><cEANTrib/><uTrib>UN</uTrib><qTrib>170.0000</qTrib><vUnTrib>23.6800</vUnTrib></prod><imposto><ICMS><ICMS40><orig>0</orig><CST>41</CST></ICMS40></ICMS><PIS><PISOutr><CST>99</CST><qBCProd>0.0000</qBCProd><vAliqProd>0.0000</vAliqProd><vPIS>0.00</vPIS></PISOutr></PIS><COFINS><COFINSOutr><CST>99</CST><qBCProd>0.0000</qBCProd><vAliqProd>0.0000</vAliqProd><vCOFINS>0.00</vCOFINS></COFINSOutr></COFINS></imposto></det><total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vBCST>0.00</vBCST><vST>0.00</vST><vProd>6532.50</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc><vII>0.00</vII><vIPI>0.00</vIPI><vPIS>0.00</vPIS><vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>6532.50</vNF></ICMSTot></total><transp><modFrete>0</modFrete><transporta><CNPJ>80227796000159</CNPJ><xNome>EXPRESSO PRINCESA DOS CAMPOS S.A.</xNome><IE>2010436039</IE><xEnder>AV. PREFEITO SINCLER SAMBATTI, 145 -</xEnder><xMun>MARINGA</xMun><UF>PR</UF></transporta><vol><qVol>04</qVol><esp>CX</esp></vol></transp><cobr><dup><nDup>A</nDup><dVenc>2011-02-25</dVenc><vDup>2177.50</vDup></dup><dup><nDup>B</nDup><dVenc>2011-03-11</dVenc><vDup>2177.50</vDup></dup><dup><nDup>C</nDup><dVenc>2011-03-25</dVenc><vDup>2177.50</vDup></dup></cobr><infAdic><infCpl>DOCUMENTO EMITIDO POR ME OU EPP OPTANTE PELO SIMPLES NACIONAL E NAO GERA DIREITO A CREDITO FISCAL DE ICMS E DE ISS.; ORDEM DE COMPRA: 10.224</infCpl></infAdic></infNFe></NFe>";

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(xml)));
		document.setXmlStandalone(true);

		certificadoDigital.sign(document.getDocumentElement());

		assertTrue(certificadoDigital.validate(document));
	}

	@Test
	public void testSign2() throws Exception {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\"><infNFe Id=\"NFe41110268816305000100550000000002710746764646\" versao=\"1.10\"><ide><cUF>41</cUF><cNF>074676464</cNF><natOp>5101</natOp><indPag>0</indPag><mod>55</mod><serie>0</serie><nNF>271</nNF><dEmi>2011-02-23</dEmi><tpNF>1</tpNF><cMunFG>4115200</cMunFG><tpImp>2</tpImp><tpEmis>1</tpEmis><cDV>6</cDV><tpAmb>2</tpAmb><finNFe>1</finNFe><procEmi>0</procEmi><verProc>1.0</verProc></ide><emit><CNPJ>68816305000100</CNPJ><xNome>RAGUS INDUSTRIA E COMERCIO DE CONFECCOES LTDA</xNome><enderEmit><xLgr>RUA GASPAR RICARDO</xLgr><nro>43</nro><xBairro>ZONA 10</xBairro><cMun>4115200</cMun><xMun>MARINGA</xMun><UF>PR</UF><CEP>87040365</CEP><fone>4432292811</fone></enderEmit><IE>7011770961</IE></emit><dest><CNPJ>06305901000178</CNPJ><xNome>INSULA TECNOLOGIA DA INFORMACAO LTDA</xNome><enderDest><xLgr>RUA MARECHAL DEODORO</xLgr><nro>1166</nro><xBairro>ZONA 7</xBairro><cMun>4115200</cMun><xMun>MARINGA</xMun><UF>PR</UF><CEP>87030020</CEP></enderDest><IE>ISENTO</IE></dest><det nItem=\"1\"><prod><cProd>1234567890</cProd><cEAN/><xProd>CALCA BRANCA COM BARRA ITALIANA E BOLSO GRANDE</xProd><NCM>01234567</NCM><CFOP>5101</CFOP><uCom>UN</uCom><qCom>10.0000</qCom><vUnCom>20.0000</vUnCom><vProd>200.00</vProd><cEANTrib/><uTrib>UN</uTrib><qTrib>10.0000</qTrib><vUnTrib>20.0000</vUnTrib></prod><imposto><ICMS><ICMS40><orig>0</orig><CST>41</CST></ICMS40></ICMS><PIS><PISOutr><CST>99</CST><qBCProd>0.0000</qBCProd><vAliqProd>0.0000</vAliqProd><vPIS>0.00</vPIS></PISOutr></PIS><COFINS><COFINSOutr><CST>99</CST><qBCProd>0.0000</qBCProd><vAliqProd>0.0000</vAliqProd><vCOFINS>0.00</vCOFINS></COFINSOutr></COFINS></imposto></det><total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vBCST>0.00</vBCST><vST>0.00</vST><vProd>200.00</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>50.00</vDesc><vII>0.00</vII><vIPI>0.00</vIPI><vPIS>0.00</vPIS><vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>150.00</vNF></ICMSTot></total><transp><modFrete>0</modFrete><vol><qVol>3</qVol><esp>CAIXA</esp></vol></transp><cobr><dup><nDup>P1</nDup><dVenc>2011-02-23</dVenc><vDup>150.00</vDup></dup></cobr><infAdic><infAdFisco>DOCUMENTO EMITIDO POR ME OU EPP OPTANTE PELO SIMPLES NACIONAL. NAO GERA DIREITO A CREDITO FISCAL DE IPI.</infAdFisco><infCpl>PEDIDO N.O 4</infCpl></infAdic></infNFe></NFe>";

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(xml)));
		document.setXmlStandalone(true);
		certificadoDigital.sign(document.getDocumentElement());
		assertTrue(certificadoDigital.validate(document));
	}

}