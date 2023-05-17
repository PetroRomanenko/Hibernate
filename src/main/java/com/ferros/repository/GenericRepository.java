package com.ferros.repository;

import com.ferros.model.Label;

import java.util.List;
import java.util.Optional;

public interface GenericRepository <T, ID>{
    Optional<T> getById(ID id);
    List<T> getAll();
    T save(T t);
    T update(T t);
    void deleteById(ID id);
}
