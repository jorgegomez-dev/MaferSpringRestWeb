package gm.mafer.service;

import gm.mafer.model.Asesoria;

import java.util.List;

public interface AsesoriaService {

    List<Asesoria> findAllAsesorias();

    Asesoria crearAsesoria(Asesoria asesoria);

    Asesoria findAsesoriaById(Long asesoriaId);

    Asesoria actualizarAsesoria (Asesoria asesoria);

    Boolean deleteAsesoria (Long id);
}
