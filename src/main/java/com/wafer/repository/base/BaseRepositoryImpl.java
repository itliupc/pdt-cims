package com.wafer.repository.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
    implements BaseRepository<T, ID> {

  private final EntityManager em;

  public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
    super(domainClass, em);
    this.em = em;
  }


  @Override
  public Query createQuery(String sql) {
    if (sql.contains("ps_")) {
      return em.createNativeQuery(sql);
    } else {
      return em.createQuery(sql);
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public List listBySql(String sql) {
    if (sql.contains("ps_")) {
      Query query = em.createNativeQuery(sql);
      query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
      return query.getResultList();
    } else {
      return em.createQuery(sql).getResultList();
    }
  }

  @Override
  public Object queryBySql(String sql) {
    if (sql.contains("ps_")) {
      return em.createNamedQuery(sql).getSingleResult();
    } else {
      return em.createQuery(sql).getSingleResult();
    }
  }
}
