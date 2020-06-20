package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

public class FakeUploader {

	/**
	 * 
	 * @param imagens
	 * @return conjunto de links
	 */
	public Set<String> upload(Collection<MultipartFile> imagens) {
		// poderia mandar para algum lugar e pegar o link
		return imagens.stream()
				.map(imagem -> "http://algumlugar.com.br/bucket/"
						+ imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}

}
