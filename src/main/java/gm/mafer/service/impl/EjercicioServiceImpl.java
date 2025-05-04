package gm.mafer.service.impl;

import gm.mafer.model.Ejercicio;
import gm.mafer.repository.EjercicioRepository;
import gm.mafer.service.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EjercicioServiceImpl implements EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository; // Creamos un ejercicio desde la interface Repository para traer los ejercicios

    @Autowired
    public EjercicioServiceImpl(EjercicioRepository ejercicioRepository) { // Constructor  para EjercicioServiceImpl
        this.ejercicioRepository = ejercicioRepository;
    }

    @Override
    public List<Ejercicio> findAllEjercicios() {
        List<Ejercicio> ejercicios = ejercicioRepository.findAll();
        return ejercicios;
    }

    @Override
    public List<Ejercicio> findAllEjerciciosPorSexo(String sexo) {
        List<Ejercicio> ejercicios = ejercicioRepository.findAll();
        List<Ejercicio> ejerciciosPorSexo = new ArrayList<>();
        for (int i = 0; i < ejercicios.size(); i++) {
            if(ejercicios.get(i).getSexo().equalsIgnoreCase(sexo)){
                ejerciciosPorSexo.add(ejercicios.get(i));
            }
        }
        return ejerciciosPorSexo;
    }


    @Override
    public Ejercicio crearEjercicio(Ejercicio ejercicio) {
        return ejercicioRepository.save(ejercicio);
    }

    @Override
    public Ejercicio findEjercicioById(Long ejercicioId) {
        Ejercicio ejercicio = ejercicioRepository.findById(ejercicioId).get();
        return ejercicio;
    }

    @Override
    public Ejercicio actualizarEjercicio(Ejercicio ejercicio) {
        return ejercicioRepository.save(ejercicio);
    }

    @Override
    public Boolean deleteEjercicio(Long id) {
        Ejercicio ejercicio = ejercicioRepository.findById(id).get();
        if(ejercicio != null) {
            ejercicioRepository.delete(ejercicio);
            return true;
        } else {
            return false;
        }
    }

}
