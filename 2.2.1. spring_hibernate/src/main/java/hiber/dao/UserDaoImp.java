package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
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
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserWithAuto(String model, int series) {
        Car car = null;
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(
                "FROM Car WHERE model = :model AND series = :series", Car.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        try {
            car = query.getSingleResult();
        } catch (NoResultException e) {

        }
        if (car != null) {
            return car.getUser();
        }
        return null;
    }

}
