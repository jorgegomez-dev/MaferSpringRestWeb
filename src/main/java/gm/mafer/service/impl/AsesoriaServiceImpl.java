package gm.mafer.service.impl;

import gm.mafer.model.Asesoria;
import gm.mafer.repository.AsesoriaRepository;
import gm.mafer.repository.DiaRepository;
import gm.mafer.service.AsesoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsesoriaServiceImpl implements AsesoriaService {

    @Autowired
    private AsesoriaRepository asesoriaRepository; // Creamos un repositorio para traer el objeto producto

    @Autowired
    public AsesoriaServiceImpl(DiaRepository diaRepository) { // Constructor de DiaServiceImpl
        this.asesoriaRepository = asesoriaRepository;
    }

    @Override
    public List<Asesoria> findAllAsesorias() {
        List<Asesoria> asesorias = asesoriaRepository.findAll();
        return asesorias;
    }

    @Override
    public Asesoria crearAsesoria(Asesoria asesoria) {
        return asesoriaRepository.save(asesoria);
    }

    @Override
    public Asesoria findAsesoriaById(Long asesoriaId) {
        Asesoria asesoria = asesoriaRepository.findById(asesoriaId).get();
        return asesoria;
    }

    @Override
    public Asesoria actualizarAsesoria(Asesoria asesoria) {
        return asesoriaRepository.save(asesoria);
    }


    @Override
    public Boolean deleteAsesoria(Long id) {
        Asesoria asesoria = asesoriaRepository.findById(id).get();
        if(asesoria != null) {
            asesoriaRepository.delete(asesoria);
            return true;
        } else {
            return false;
        }
    }


}
