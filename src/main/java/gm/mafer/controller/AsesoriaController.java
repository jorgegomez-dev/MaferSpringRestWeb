package gm.mafer.controller;

import gm.mafer.model.Asesoria;
import gm.mafer.repository.AsesoriaRepository;
import gm.mafer.service.AsesoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/asesorias")
public class AsesoriaController {

    @Autowired
    private AsesoriaRepository asesoriaRepository;

    @Autowired
    private AsesoriaService asesoriaService;

    @Autowired
    public AsesoriaController(AsesoriaRepository asesoriaRepository, AsesoriaService asesoriaService) {
        this.asesoriaRepository = asesoriaRepository;
        this.asesoriaService = asesoriaService;
    }

    @GetMapping("/listarAsesorias")
    public ResponseEntity<?> listarAsesorias(){
        try{
            return new ResponseEntity<>(asesoriaService.findAllAsesorias(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crearAsesoria")
    public ResponseEntity<?> crearAsesoria(@RequestBody Asesoria asesoria) {
        Asesoria asesoriaCreada = asesoriaService.crearAsesoria(asesoria);
        if(asesoriaCreada!= null){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest() // Toma la url base de la solicitud
                    .path("/{id}")
                    .buildAndExpand(asesoria.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido crear el Dia");
        }
    }

    @GetMapping("/buscarAsesoriaId/{id}")
    public ResponseEntity<?> buscarAsesoriaById (@PathVariable Long id){
        try{
            return new ResponseEntity<>(asesoriaService.findAsesoriaById(id), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dia no encontrado");
        }
    }

    @PutMapping("/updateAsesoria/{asesoriaId}")
    public ResponseEntity<?> updateAsesoria(@PathVariable Long asesoriaId, @RequestBody Asesoria asesoria){

        Asesoria updateAsesoria = asesoriaService.findAsesoriaById(asesoriaId);
        if(updateAsesoria != null) {
            updateAsesoria.setNombreAsesoria(asesoria.getNombreAsesoria());
            updateAsesoria.setTipoAsesoria(asesoria.getTipoAsesoria());
            updateAsesoria.setPdfUrl(asesoria.getPdfUrl());
            updateAsesoria.setPrecio(asesoria.getPrecio());
            updateAsesoria.setImgUrl(asesoria.getImgUrl());
            updateAsesoria.setLinkPago(asesoria.getLinkPago());
            asesoriaService.actualizarAsesoria(updateAsesoria);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAsesoria/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(asesoriaService.deleteAsesoria(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
