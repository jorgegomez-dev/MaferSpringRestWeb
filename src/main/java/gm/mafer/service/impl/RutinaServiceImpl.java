package gm.mafer.service.impl;

import gm.mafer.model.Rutina;
import gm.mafer.repository.RutinaRepository;
import gm.mafer.service.RutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RutinaServiceImpl implements RutinaService {

    @Autowired
    RutinaRepository rutinaRepository; // Creamos el repositorio para rutina

    @Autowired
    public RutinaServiceImpl(RutinaRepository rutinaRepository) { // Constructor de DiaServiceImpl
        this.rutinaRepository = rutinaRepository;
    }

    @Override
    public List<Rutina> findAllRutinas() {
        List<Rutina> rutinas = rutinaRepository.findAll();
        return rutinas;
    }

    @Override
    public List<Rutina> findAllRutinasPorSexo(String sexo) {
        List<Rutina> rutinas = rutinaRepository.findAll();
        List<Rutina> rutinasPorSexo = new ArrayList<>();
        for (int i = 0; i < rutinas.size(); i++) {
            if(rutinas.get(i).getSexo().equalsIgnoreCase(sexo)){
                rutinasPorSexo.add(rutinas.get(i));
            }
        }
        return rutinasPorSexo;
    }

    @Override
    public Rutina crearRutina(Rutina rutina) {
        return rutinaRepository.save(rutina);
    }

    @Override
    public Rutina findRutinaById(Long rutinaId) {
        Rutina rutina = rutinaRepository.findById(rutinaId).get();
        return rutina;
    }

    @Override
    public Rutina actualizarRutina(Rutina rutina) {
        return rutinaRepository.save(rutina);
    }

    @Override
    public Boolean deleteRutina(Long id) {
        Rutina rutina = rutinaRepository.findById(id).get();
        if(rutina != null) {
            rutinaRepository.delete(rutina);
            return true;
        } else {
            return false;
        }
    }
}
