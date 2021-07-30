package hu.ulyssys.java.course.homework.infos.service.Impl;

import hu.ulyssys.java.course.homework.infos.entity.AbstractProperty;

import java.util.ArrayList;
import java.util.List;

//Ezt nevezik generikus megoldásnak
public abstract class AbstractServiceImpl<T extends AbstractProperty> {
    private List<T> list = new ArrayList<>();

    public List<T> getAll() {
        return list;
    }

    public void add(T example) {
        list.add(example);
    }

    public void remove(T example) {
        list.remove(example);
    }
}