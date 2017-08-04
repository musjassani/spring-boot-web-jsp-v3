package com.proto.services.impl;

import com.proto.dao.AbstractDao;
import com.proto.services.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractServiceImpl<E> implements AbstractService<E> {

    private final Logger logger = LoggerFactory.getLogger(AbstractServiceImpl.class);


    @Autowired
    AbstractDao<E> dao;

    @Override
    public void save(E e) {
        dao.save(e);
    }

    @Override
    public void update(E e) {
        dao.update(e);
    }

    @Override
    public void delete(E e) {
        dao.delete(e);
    }

    @Override
    public List<E> list() {
        return dao.list();
    }

    @Override
    public E getById(Serializable id) {
        return dao.getById(id);
    }
}
