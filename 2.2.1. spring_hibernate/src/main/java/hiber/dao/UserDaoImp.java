package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getByModelAndSeries(Car car) {
      String hql = "From User users WHERE users.car.series =: series AND users.car.model =: model";
      TypedQuery <User> query=sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("series", car.getSeries());
      query.setParameter("model", car.getModel());
      return query.getSingleResult();
   }

}

//   public User getUserByCar(int series) {
//   String hql = "FROM User usrs WHERE usrs.car.series =: series";
//     TypedQuery <User> typedQuery = sessionFactory.getCurrentSession().createQuery(hql);
//      typedQuery.setParameter("series", series);
//      return typedQuery.getSingleResult();