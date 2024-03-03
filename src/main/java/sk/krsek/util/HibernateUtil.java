package sk.krsek.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sk.krsek.exception.AppInitializationException;

public class HibernateUtil {

  private static EntityManagerFactory emf;

  private static EntityManagerFactory buildEntityManagerFactory() {
    try {
      if (emf == null) {
        emf = Persistence.createEntityManagerFactory("persistence");
      }
      return emf;
    } catch (Throwable ex) {
      System.err.println("EntityManagerFactory creation failed with message: " + ex.getMessage());
      throw new AppInitializationException(ex.getMessage());
    }
  }

  public static EntityManager getEntityManager() {
    return buildEntityManagerFactory().createEntityManager();
  }
}
