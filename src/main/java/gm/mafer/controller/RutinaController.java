package gm.mafer.controller;

import gm.mafer.model.Rutina;
import gm.mafer.repository.RutinaRepository;
import gm.mafer.service.RutinaService;
import gm.mafer.service.ValidacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaController {

    @Autowired
    private RutinaRepository rutinaRepository; // Creamos un usuarioRepository
    @Autowired
    private RutinaService rutinaService; // Creamos un usuarioService para poder usar los servicios de Usuario

    @Autowired
    public ValidacionesService validacionesService; // Creo el servicio para poder llamar a los metodos

    @Autowired
    public RutinaController(RutinaService rutinaService) { // Constructor de UsuarioController
        this.rutinaService = rutinaService;
    }

    @GetMapping("/listarRutinas")
    public ResponseEntity<?> listarRutinas(){
        try{
            return new ResponseEntity<>(rutinaService.findAllRutinas(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listarRutinasPorSexo{sexo}")
    public ResponseEntity<?> listarRutinasPorSexo(@PathVariable String sexo){
        try{
            return new ResponseEntity<>(rutinaService.findAllRutinasPorSexo(sexo), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crearRutina")
    public ResponseEntity<?> crearRutina(@RequestBody Rutina rutina) {
        Rutina rutinaCreada = rutinaService.crearRutina(rutina);
        if(rutinaCreada!= null){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest() // Toma la url base de la solicitud
                    .path("/{id}")
                    .buildAndExpand(rutina.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido crear la Rutina");
        }
    }

    @GetMapping("/buscarRutinaId/{idRutinaActual}")
    public ResponseEntity<?> buscarRutinaById (@PathVariable Long idRutinaActual){
        try{
            return new ResponseEntity<>(rutinaService.findRutinaById(idRutinaActual), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rutina no encontrada");
        }
    }


    @PutMapping("/updateRutina/{rutinaId}")
    public ResponseEntity<?> updateRutina(@PathVariable Long rutinaId, @RequestBody Rutina rutina){

        Rutina updateRutina = rutinaService.findRutinaById(rutinaId);
        if(updateRutina != null) {
            updateRutina.setNombreRutina(rutina.getNombreRutina());
            updateRutina.setSexo(rutina.getSexo());
            // updateRutina.setFechaInicioRutina(rutina.getFechaInicioRutina());
            updateRutina.setDiaUno(rutina.getDiaUno());
            updateRutina.setDiaDos(rutina.getDiaDos());
            updateRutina.setDiaTres(rutina.getDiaTres());
            updateRutina.setDiaCuatro(rutina.getDiaCuatro());
            updateRutina.setDiaCinco(rutina.getDiaCinco());
            updateRutina.setDiaSeis(rutina.getDiaSeis());
            updateRutina.setDiaSiete(rutina.getDiaSiete());
            rutinaService.actualizarRutina(updateRutina);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteRutina/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(rutinaService.deleteRutina(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
