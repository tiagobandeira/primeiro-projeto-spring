package com.eletrongroup.stringapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.eletrongroup.stringapp.Empresa;
import com.eletrongroup.stringapp.EmpresaRepository;

@Service
public class EmpresaService {
	@Autowired 
	private EmpresaRepository empresaRepository;
	
	public Empresa saveEmpresa(Empresa empresa) {
		//validacao do cnpj
		return empresaRepository.save(empresa);
	}
	public ResponseEntity <Empresa> updateEmpresa(Long id, Empresa empresa) {
		return empresaRepository.findById(id).map( item -> {
            item.setCnpj(empresa.getCnpj());
            Empresa empresaUpdated = empresaRepository.save(item);
            return ResponseEntity.ok().body(empresaUpdated);
        }).orElse(ResponseEntity.notFound().build());
		
	}
	public Iterable<Empresa> getEmpresas(Long id){
	    return empresaRepository.findAll();
	}
	public Empresa getEmpresa(@PathVariable Long id) {
	    return empresaRepository.findEmpresaById(id);
	}
	public ResponseEntity<?> deleteEmpresa(Long id){
		return empresaRepository.findById(id).map(item ->{
    		empresaRepository.deleteById(id);
    		return ResponseEntity.ok().build();
    	}).orElse(ResponseEntity.notFound().build());
	}
}
