package com.crud.controller;

import com.crud.controller.dto.TelefoneDTO;
import com.crud.controller.form.TelefoneForm;
import com.crud.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {
	
	@Autowired
	private TelefoneService telefoneService;

	@PostMapping(value = { "/{idCliente}" })
	@ResponseStatus(HttpStatus.CREATED)
	public TelefoneDTO adicionarTelefone(@PathVariable("idCliente") Long idCliente,
										 @RequestBody TelefoneForm telefoneForm) {
		return telefoneService.salvar(telefoneForm, idCliente);
	}

}
