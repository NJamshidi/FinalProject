package ir.maktab.homeservicesystem.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseService<T, ID> {

    private JpaRepository<T, ID> jpaRepository;

    public void setJpaRepository(JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Transactional
    public T save(T entity) {
        return jpaRepository.save(entity);
    }

    public T findById(ID id) {
        return jpaRepository.findById(id).orElseThrow(() ->
               new NotFoundObjectException("Entity", "ByID", id)
        );
    }

    @Transactional
    public T update(T entity) {
        return jpaRepository.save(entity);
    }

    public void removeById(ID id) {
        jpaRepository.deleteById(id);
    }

    public List<T> findAll() {
        return jpaRepository.findAll();
    }
}