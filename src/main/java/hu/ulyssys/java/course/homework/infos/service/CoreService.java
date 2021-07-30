package hu.ulyssys.java.course.homework.infos.service;

import hu.ulyssys.java.course.homework.infos.entity.AbstractProperty;

import java.util.List;

public interface CoreService<T extends AbstractProperty> {
    List<T> getAll();

    void add(T example);

    void remove(T example);

    void update(T example);
}
