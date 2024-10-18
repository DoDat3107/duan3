package com.example.mvc.service;

import java.util.List;

public interface IService<E> {
   boolean add(E e);
   boolean edit(E e,int id);
      boolean delete(int id);
   int findById(int id);
   List<E> findAll();

}
