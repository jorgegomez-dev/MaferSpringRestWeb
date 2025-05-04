package gm.mafer.service;


public interface ValidacionesService {

    public Boolean validarSession(String id, String session);

    public int generarSession();


}
