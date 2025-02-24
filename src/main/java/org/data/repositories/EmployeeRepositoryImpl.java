package org.data.repositories;

import org.data.entities.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private SessionFactory sessionFactory;

//    private Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }

    @Override
    public void save(Employee employee) {
        sessionFactory.openSession().saveOrUpdate(employee);
    }

    @Override
    public Employee findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {  // Open session explicitly
            Query<Employee> query = session.createQuery("FROM Employee WHERE username = :username", Employee.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Employee> findAll() {
        Criteria criteria = sessionFactory.openSession().createCriteria(Employee.class);
        return criteria.list();
    }

    @Override
    public Employee findById(Long id) {
        return sessionFactory.openSession().get(Employee.class, id);
    }

    @PostConstruct
    public void init() {
        System.out.println("SessionFactory instance in EmployeeRepositoryImpl: " + System.identityHashCode(sessionFactory));
    }
}
