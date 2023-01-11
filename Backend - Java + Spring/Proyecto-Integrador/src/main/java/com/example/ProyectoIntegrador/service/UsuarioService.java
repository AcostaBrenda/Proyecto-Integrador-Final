package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.DTO.UsuarioDTO;
import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.model.Rol;
import com.example.ProyectoIntegrador.model.Usuario;
import com.example.ProyectoIntegrador.repository.RolRepository;
import com.example.ProyectoIntegrador.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IGenericaService<UsuarioDTO, Long>{

    final static Logger logger = Logger.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UsuarioDTO buscar(Long aLong) throws BadRequestException {
        return null;
    }

    @Override
    public UsuarioDTO agregar(UsuarioDTO usuarioDTO) throws BadRequestException {
        if (usuarioDTO == null) {
            logger.error("El usuario ingresado no tiene datos");
            throw new BadRequestException("El usuario ingresado no tiene datos");
        }

        Usuario usuarioNuevo = mapper.convertValue(usuarioDTO, Usuario.class);

        if (usuarioDTO.getListadereservas() == null) {
            usuarioNuevo.setListadereservas(null);
        }
            usuarioNuevo.setPassword(encoder.encode(usuarioDTO.getPassword()));
            // Hardcodeamos al usuario reistrado el rol de USER
            Rol rol = rolRepository.findByNombre("USER");
            usuarioNuevo.setRol(rol);
            usuarioRepository.save(usuarioNuevo);
            logger.info("Se agregó un nuevo usuario al sistema");
            return mapper.convertValue(usuarioNuevo, UsuarioDTO .class);
        }

        /**    Metodo que a ser llamado en el logueo con jwt      **/
    public UsuarioDTO buscarPorEmail(String email) throws BadRequestException {
        Usuario user= usuarioRepository.findByEmail(email);
        logger.info("Usuario obtenido de la base de datos : "+ user);
        if(user == null){
            throw new BadRequestException("No existe usuario con ese email");
        } else{
            UsuarioDTO responseUserDTO= mapper.convertValue(user, UsuarioDTO.class);
            logger.info("Usuario de respuesta : "+ responseUserDTO);
            return responseUserDTO;
        }
    }
    /** ************************************************************************  **/

    @Override
    public List<UsuarioDTO> listarTodas() {
        return null;
    }

    @Override
    public UsuarioDTO editar(UsuarioDTO usuarioDTO, Long aLong) throws BadRequestException {
        return null;
    }

    @Override
    public void eliminar(Long aLong) throws BadRequestException {
    }
}
