package com.farmacia.backend.repository;

import com.farmacia.backend.entity.UsuarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {
    Optional<UsuarioEntity> findByUsuarioAndContrasenaAndTipoUsuario(String usuario, String contrasena, String tipoUsuario);
}
