package gm.mafer.service.impl;

import gm.mafer.model.Dia;
import gm.mafer.repository.DiaRepository;
import gm.mafer.service.DiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaServiceImpl implements DiaService {

    @Autowired
    private DiaRepository diaRepository; // Creamos un repositorio para traer el objeto dia

    @Autowired
    public DiaServiceImpl(DiaRepository diaRepository) { // Constructor de DiaServiceImpl
        this.diaRepository = diaRepository;
    }

    @Override
    public List<Dia> findAllDias() {
        List<Dia> dias = diaRepository.findAll();
        return dias;
    }

    @Override
    public List<Dia> findAllDiasPorSexo(String sexo) {
        List<Dia> dias = diaRepository.findAll();
        List<Dia> diasPorSexo = new ArrayList<>();
        for (int i = 0; i < dias.size(); i++) {
            if(dias.get(i).getSexo().equalsIgnoreCase(sexo)){
                diasPorSexo.add(dias.get(i));
            }
        }
        return diasPorSexo;
    }

    @Override
    public Dia crearDia(Dia dia) {
        return diaRepository.save(dia);
    }

    @Override
    public Dia findDiaById(Long diaId) {
        Dia dia = diaRepository.findById(diaId).get();
        return dia;
    }

    @Override
    public Dia actualizarDia(Dia dia) {
        return diaRepository.save(dia);
    }

    @Override
    public Boolean deleteDia(Long id) {
        Dia dia = diaRepository.findById(id).get();
        if(dia != null) {
            diaRepository.delete(dia);
            return true;
        } else {
            return false;
        }
    }
}
