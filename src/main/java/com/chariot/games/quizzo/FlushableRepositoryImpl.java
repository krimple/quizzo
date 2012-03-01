package com.chariot.games.quizzo;

import com.chariot.games.quizzo.db.FlushableAbstractRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: kenrimple
 * Date: 3/1/12
 * Time: 4:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlushableRepositoryImpl implements FlushableAbstractRepository {

  @PersistenceContext
  private EntityManager em;

  @Transactional
  public void flush() {
    em.flush();
  }
}
