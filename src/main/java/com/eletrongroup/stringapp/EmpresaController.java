package com.eletrongroup.stringapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eletrongroup.stringapp.service.EmpresaService;


@RestController
@RequestMapping({"/empresa"})
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;
    

    @PostMapping("/add")
    public Empresa add(@RequestBody Empresa empresa) {
        return empresaService.saveEmpresa(empresa);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Empresa> edit(@PathVariable("id") Long id, @RequestBody Empresa empresa) {
        return empresaService.updateEmpresa(id, empresa);
    }
    @GetMapping("/list")
    public Iterable<Empresa> list(Long id){
        return empresaService.getEmpresas(id);
    }
    @GetMapping("/find/{id}")
    public Empresa find(@PathVariable Long id) {
        return empresaService.getEmpresa(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
    	return empresaService.deleteEmpresa(id);
    }


}

