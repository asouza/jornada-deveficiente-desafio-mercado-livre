package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagensProdutoRequest {

	@Size(min = 1)
	@Valid
	private List<MultipartFile> imagens = new ArrayList<>();
	
	public void setImagens(List<MultipartFile> imagens) {		
		this.imagens = imagens;
	}
	
	public List<MultipartFile> getImagens() {
		return imagens;
	}

}
