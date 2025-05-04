package gm.mafer.controller;

import gm.mafer.model.Dia;
import gm.mafer.repository.DiaRepository;
import gm.mafer.service.DiaService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/dias")
public class DiaController {

    @Autowired
    private DiaRepository diaRepository;

    @Autowired
    private DiaService diaService;

    @Autowired
    public DiaController(DiaService diaService) { // Constructor de DiaController
        this.diaService = diaService;
    }

    @GetMapping("/listarDias")
    public ResponseEntity<?> listarDias(){
        try{
            return new ResponseEntity<>(diaService.findAllDias(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarDiasPorSexo{sexo}")
    public ResponseEntity<?> listarDiasPorSexo(@PathVariable String sexo){
        try{
            return new ResponseEntity<>(diaService.findAllDiasPorSexo(sexo), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crearDia")
    public ResponseEntity<?> crearDia(@RequestBody Dia dia) {
        Dia diaCreado = diaService.crearDia(dia);
        if(diaCreado!= null){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest() // Toma la url base de la solicitud
                    .path("/{id}")
                    .buildAndExpand(dia.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido crear el Dia");
        }
    }

    @GetMapping("/buscarDiaId/{id}")
    public ResponseEntity<?> buscarDiaById (@PathVariable String id){
        try{
            return new ResponseEntity<>(diaService.findDiaById(Long.valueOf(id)), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dia no encontrado");
        }
    }

    @PutMapping("/updateDia/{diaId}")
    public ResponseEntity<?> updateDia(@PathVariable Long diaId, @RequestBody Dia dia){

        Dia updateDia = diaService.findDiaById(diaId);
        if(updateDia != null) {
            updateDia.setNombreDia(dia.getNombreDia());
            updateDia.setSexo(dia.getSexo());
            updateDia.setEjercicioUno(dia.getEjercicioUno());
            updateDia.setEjercicioDos(dia.getEjercicioDos());
            updateDia.setEjercicioTres(dia.getEjercicioTres());
            updateDia.setEjercicioCuatro(dia.getEjercicioCuatro());
            updateDia.setEjercicioCinco(dia.getEjercicioCinco());
            updateDia.setEjercicioSeis(dia.getEjercicioSeis());
            updateDia.setEjercicioSiete(dia.getEjercicioSiete());
            updateDia.setEjercicioOcho(dia.getEjercicioOcho());
            updateDia.setEjercicioNueve(dia.getEjercicioNueve());
            updateDia.setEjercicioDiez(dia.getEjercicioDiez());
            diaService.actualizarDia(updateDia);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteDia/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(diaService.deleteDia(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
