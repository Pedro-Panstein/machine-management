package weg.arquiteturasoftware.chaodefabricaapplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.HttpMessageConvertersRestClientCustomizer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import weg.arquiteturasoftware.chaodefabricaapplication.DTO.RequestMaquinaDTO;
import weg.arquiteturasoftware.chaodefabricaapplication.DTO.ResponseMaquinaDTO;
import weg.arquiteturasoftware.chaodefabricaapplication.Entity.Maquina;
import weg.arquiteturasoftware.chaodefabricaapplication.Service.MaquinaService;

import java.net.http.HttpClient;
import java.util.List;

@RestController
@RequestMapping("/weg")
public class MaquinaController {

    @Autowired
    MaquinaService maquinaService;

    @PostMapping("/addMaquina")
    public ResponseMaquinaDTO addMaquina(@RequestBody RequestMaquinaDTO maquina) {
        return maquinaService.addMaquina(maquina);
    }

    @GetMapping("/getAllMaquinas")
    public List<ResponseMaquinaDTO> getAllMaquinas() {
        return maquinaService.getMaquinas();
    }

    @GetMapping("/getMaquina/{id}")
    public ResponseMaquinaDTO getMaquina(@PathVariable int id) {
        return maquinaService.getMaquinaById(id);
    }

    @DeleteMapping("/deleteMaquina/{id}")
    public void deleteMaquina(@PathVariable int id) {
        maquinaService.deleteMaquina(id);
    }

    @PutMapping("/updateMaquina/{id}")
    public ResponseMaquinaDTO updateMaquina(@PathVariable int id, @RequestBody RequestMaquinaDTO maquina) {
        return maquinaService.updateMaquina(maquina, id);
    }

    @PutMapping("/alterarStatusOperando/{id}")
    public String alterarStatusOperando(@PathVariable int id) {
        return maquinaService.alterarStatusOperando(id);
    }
}
