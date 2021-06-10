package com.eletrongroup.stringapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/empresa"})
public class EmpresaController {
    @Autowired
    private EmpresaRepository empresaRepository;
    

    @PostMapping("/add")
    public String addEmpresa(@RequestParam String cnpj) {
        Empresa empresa = new Empresa();
        empresa.setCnpj(cnpj);
        empresaRepository.save(empresa);
        return "Empresa adicionada com sucesso!";
    }
    @PutMapping("/edit/{id}")
    public String editEmpresa(@PathVariable("id") Long id, @RequestBody Empresa empresa) throws Exception{
        //Metodo 1
        empresaRepository.findById(id).map( item -> {
            item.setCnpj(empresa.getCnpj());
            Empresa empresaUpdated = empresaRepository.save(item);
            return ResponseEntity.ok().body(empresaUpdated);
        }).orElse(ResponseEntity.notFound().build());
        /*
        //Metodo 2
        var response = empresaRepository.findById(id);
        if(response.isPresent()){

            var empresaUpdated = response.get();
            empresaUpdated.setCnpj(empresa.getCnpj());
            empresaRepository.save(empresaUpdated);

        }else  {
            throw new Exception("Empresa não encontrada");
        }
        */

        return "Dados da empresa atualizados: ";
    }
    @GetMapping("/list")
    public Iterable<Empresa> getEmpresa(Long id){
        return empresaRepository.findAll();
    }
    @GetMapping("/find/{id}")
    public Empresa findEmpresaById(@PathVariable Long id) {
        return empresaRepository.findEmpresaById(id);
    }


}

