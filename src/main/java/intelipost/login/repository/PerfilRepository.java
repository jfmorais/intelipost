package intelipost.login.repository;

import java.util.Map;

import intelipost.login.bean.Perfil;

public interface PerfilRepository {

    void savePerfil(Perfil person);

    void updatePerfil(Perfil student);

    Perfil findPerfil(String id);

    Map<Object, Object> findAllPerfil();

    void deletePerfil(String id);
}