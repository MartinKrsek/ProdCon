package sk.krsek.repository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.Optional;
import sk.krsek.exception.NonUniqueEntityException;
import sk.krsek.util.HibernateUtil;

public class Repository {

  final EntityManager em;

  public Repository() {
    em = HibernateUtil.getEntityManager();
  }

  void deleteAll(Class<?> object) {
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.createQuery("DELETE FROM " + object.getSimpleName()).executeUpdate();
    transaction.commit();
  }

  Object create(Object object) {
    EntityTransaction transaction = em.getTransaction();
    try {
      transaction.begin();
      em.persist(object);
      transaction.commit();
      return object;
    } catch (EntityExistsException e) {
      transaction.rollback();
      throw new NonUniqueEntityException(e.getMessage());
    }
  }

  Object findAll(Class<?> objectType) {
    return em.createQuery("SELECT o FROM " + objectType.getSimpleName() + " o", objectType).getResultList();
  }

  Optional<Object> findById(int id, Class<?> objectType) {
    return Optional.ofNullable(em.find(objectType, id));
  }

}
