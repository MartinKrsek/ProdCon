package sk.krsek.repository;

import java.util.Collection;

public interface RepositoryInterface<T> {

  T save(T object);

  Collection<T> get();

  void remove();
}
