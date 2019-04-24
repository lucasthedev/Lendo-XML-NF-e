package br.com.lerxml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LerXml{

	public static void main(String[] args) {
		try {
			File arquivo = new File("src/nfe-identada.xml");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document = builder.parse(arquivo);
			
			String cnpj = document.getElementsByTagName("CNPJ").item(0).getTextContent();
			
			String nomeEmpresa = document.getElementsByTagName("xNome").item(0).getTextContent();
			
			System.out.println("CNPJ: " + cnpj);
			
			System.out.println("Nome da Empresa: " + nomeEmpresa);
			
			NodeList listaProdutos = document.getElementsByTagName("prod");
			
			int tamanho = listaProdutos.getLength();
			
			System.out.println("Quantidade total de produtos: " + tamanho);
			System.out.println();
			
			for (int i = 0; i < tamanho; i++) {
				
				Node noProduto = listaProdutos.item(i);
				
				if(noProduto.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) noProduto;
					
					NodeList dadosProdutos = elemento.getChildNodes();
					
					for (int j = 0; j < dadosProdutos.getLength(); j++) {
						
						Node noFilho = dadosProdutos.item(j);
						
						if(noFilho.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) noFilho;
							
							switch (element.getTagName()) {
							case "xProd":
								System.out.println("Nome Produto: " + element.getTextContent());
								break;
							case "qCom":
								System.out.println("Quantidade: " + element.getTextContent());
								break;
							case "uCom":
								System.out.println("Unidade de Medida: " + element.getTextContent());
								break;
							case "vProd":
								System.out.println("Valor: " + element.getTextContent());
								break;
							}
						}
					}
					System.out.println();
				}
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}