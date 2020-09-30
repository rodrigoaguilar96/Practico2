package repository;

import config.JpaEntityManagerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class GlobalRepository<T> {
  protected final EntityManager entityManager;

  public GlobalRepository() {
    this.entityManager = new JpaEntityManagerFactory().getEntityManager();
  }

  protected void execute(List<T> list) {
    final EntityTransaction entityTransaction = entityManager.getTransaction();
    try {
      entityTransaction.begin();
      list.forEach(x -> entityManager.persist(x));
      entityTransaction.commit();
    } catch (RuntimeException e) {
      entityTransaction.rollback();
      throw e;
    }
  }

  protected void execute(T t) {
    execute(Arrays.asList(t));
  }

  void executeInsideTransaction(Consumer<EntityManager> action) {
    final EntityTransaction entityTransaction = entityManager.getTransaction();
    try {
      entityTransaction.begin();
      action.accept(entityManager);
      entityTransaction.commit();
    } catch (RuntimeException e) {
      entityTransaction.rollback();
      throw e;
    }
  }
}
