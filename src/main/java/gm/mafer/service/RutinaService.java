package gm.mafer.service;

import gm.mafer.model.Rutina;

import java.util.List;

public interface RutinaService {
    List<Rutina> findAllRutinas();

    List<Rutina> findAllRutinasPorSexo(String sexo);

    Rutina crearRutina(Rutina rutina);

    Rutina findRutinaById(Long rutinaId);

    Rutina actualizarRutina (Rutina rutina);

    Boolean deleteRutina (Long id);
}
