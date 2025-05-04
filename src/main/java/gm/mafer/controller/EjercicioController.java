package gm.mafer.controller;

import gm.mafer.model.Ejercicio;
import gm.mafer.repository.EjercicioRepository;
import gm.mafer.service.EjercicioService;
import gm.mafer.service.ValidacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/ejercicios")
public class EjercicioController {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private EjercicioService ejercicioService;

    @Autowired
    public ValidacionesService validacionesService; // Creo el servicio para poder llamar a los metodos

    @Autowired
    public EjercicioController(EjercicioService ejercicioService) { // Constructor de EjercicioController
        this.ejercicioService = ejercicioService;
    }

    @GetMapping("/listarEjercicios")
    public ResponseEntity<?> listarEjercicios(){
        try{
            return new ResponseEntity<>(ejercicioService.findAllEjercicios(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarEjerciciosPorSexo{sexo}")
    public ResponseEntity<?> listarEjerciciosPorSexo(@PathVariable String sexo){
        try{
            return new ResponseEntity<>(ejercicioService.findAllEjerciciosPorSexo(sexo), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/buscarEjercicioId/{id}")
    public ResponseEntity<?> buscarEjercicioById (@PathVariable String id){
        try{
            return new ResponseEntity<>(ejercicioService.findEjercicioById(Long.valueOf(id)), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ejercicio no encontrado");
        }
    }

    @PostMapping("/crearEjercicio")
    public ResponseEntity<?> crearEjercicio(@RequestBody Ejercicio ejercicio) {
    Ejercicio ejercicioCreado = ejercicioService.crearEjercicio(ejercicio);
    if(ejercicioCreado!= null){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // Toma la url base de la solicitud
                .path("/{id}")
                .buildAndExpand(ejercicio.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido crear el Ejercicio");
    }
  }

    @PutMapping("/updateEjercicio/{ejercicioId}")
    public ResponseEntity<?> updateEjercicio(@PathVariable Long ejercicioId, @RequestBody Ejercicio ejercicio){

        Ejercicio updateEjercicio = ejercicioService.findEjercicioById(ejercicioId);
        if(updateEjercicio != null) {
            updateEjercicio.setNombreEjercicio(ejercicio.getNombreEjercicio());
            updateEjercicio.setCantSeriesMin(ejercicio.getCantSeriesMin());
            updateEjercicio.setCantSeriesMax(ejercicio.getCantSeriesMax());
            updateEjercicio.setCantRepeticionesMin(ejercicio.getCantRepeticionesMin());
            updateEjercicio.setCantRepeticionesMax(ejercicio.getCantRepeticionesMax());
            updateEjercicio.setDescansoMinutosMin(ejercicio.getDescansoMinutosMin());
            updateEjercicio.setDescansoMinutosMax(ejercicio.getDescansoMinutosMax());
            updateEjercicio.setMusculo(ejercicio.getMusculo());
            updateEjercicio.setUrlimgejercicio(ejercicio.getUrlimgejercicio());
            ejercicioService.actualizarEjercicio(updateEjercicio);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/deleteEjercicio/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id,  // id del ejercicio como path variable
            @RequestHeader(value = "session", required = true) String session,
            @RequestHeader(value = "userId", required = true) Long userId) {

        // Verifica si el usuario y la sesión son válidos
        if (!validacionesService.validarSession(String.valueOf(userId), session)) {
            return new ResponseEntity<>("Sesión inválida o expirada", HttpStatus.UNAUTHORIZED);
        } else {
            // Intenta eliminar el ejercicio por el id
            if (ejercicioService.deleteEjercicio(id)) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
    }
}
