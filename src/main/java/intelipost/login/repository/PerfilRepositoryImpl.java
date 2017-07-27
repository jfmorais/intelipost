package intelipost.login.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import intelipost.login.bean.Perfil;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class PerfilRepositoryImpl implements PerfilRepository {

    private static final String KEY = "Student";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public PerfilRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void savePerfil(final Perfil student) {
        hashOperations.put(KEY, student.getId(), student);
    }

    public void updatePerfil(final Perfil student) {
        hashOperations.put(KEY, student.getId(), student);
    }

    public Perfil findPerfil(final String id) {
        return (Perfil) hashOperations.get(KEY, id);
    }

    public Map<Object, Object> findAllPerfil() {
        return hashOperations.entries(KEY);
    }

    public void deletePerfil(final String id) {
        hashOperations.delete(KEY, id);
    }

	}