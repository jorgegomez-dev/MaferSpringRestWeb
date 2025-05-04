package gm.mafer.service;

import gm.mafer.model.Ejercicio;

import java.util.List;

public interface EjercicioService {

    List<Ejercicio> findAllEjercicios();
    List<Ejercicio> findAllEjerciciosPorSexo(String sexo);
    Ejercicio crearEjercicio(Ejercicio ejercicio);
    Ejercicio findEjercicioById(Long ejercicioId);
    Ejercicio actualizarEjercicio (Ejercicio ejercicio);

    Boolean deleteEjercicio (Long id);

}
