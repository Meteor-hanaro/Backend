package com.hana.app.frame;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface BaseService<K, V> {
    @Transactional
    public V insert(V v);

    @Transactional
    public void delete(K k);

    @Transactional
    public V update(V v);

    public Optional<V> get(K k);

    public List<V> getAll();
}