package gm.mafer.controller;

import gm.mafer.modelo.Cliente;
import gm.mafer.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    // Endpoints del servicio
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping() // localhost:8080
    public String conexion(){
        return "CONECTADO";
    }

    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return clienteRepositorio.findAll();
    }

    @PostMapping("/crear")
    public String crearCliente(@RequestBody Cliente cliente){
        clienteRepositorio.save(cliente);
        return "Cliente creado!";
    }

    @PutMapping("/update/{id}")
    public String updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente){

        try{
            Cliente updateCliente = clienteRepositorio.findById(id).get();
            if(updateCliente != null) {
                updateCliente.setNombre(cliente.getNombre());
                updateCliente.setApellido(cliente.getApellido());
                updateCliente.setPeso(cliente.getPeso());
                clienteRepositorio.save(updateCliente);
                return "Cliente actualizado correctamente";
            }
        } catch (Exception e){
            return "Cliente NO encontrado!";
        }
        return "";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        try {
            Cliente deleteCliente = clienteRepositorio.findById(id).get();

            if(deleteCliente != null) {
                clienteRepositorio.delete(deleteCliente);
                return "Cliente Eliminado con exito!";
            }
        } catch (Exception e){
            return "Cliente NO encontrado!";
        }
        return "";
        }

    }
