package gm.mafer.service;

import gm.mafer.model.Dia;

import java.util.List;

public interface DiaService {

    List<Dia> findAllDias();

    List<Dia> findAllDiasPorSexo(String sexo);

    Dia crearDia(Dia dia);

    Dia findDiaById(Long diaId);

    Dia actualizarDia (Dia dia);

    Boolean deleteDia (Long id);
}
