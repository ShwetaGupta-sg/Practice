package org.data.repositories;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.entities.Permission;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

//    @Inject
    @Autowired
    private SessionFactory sessionFactory;

    @Inject
    private PermissionRepository permissionRepository;

    @PersistenceContext
    private EntityManager entityManager;


    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Permission findByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(Permission.class);
        criteria.add(Restrictions.eq("name", name));
        return (Permission) criteria.uniqueResult();
    }

    @Override
    public List<Permission> findAll() {
        Criteria criteria = getCurrentSession().createCriteria(Permission.class);
        return criteria.list();
    }


    @Override
    public List<String> getPermissionsByEmployeeId(Long employeeId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Permission> root = cq.from(Permission.class);

        cq.select(root.get("name"))
                .where(cb.equal(root.get("employee").get("id"), employeeId));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public boolean hasPermission(Employee employee, String permissionName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Permission> root = cq.from(Permission.class);

        Predicate employeePredicate = cb.equal(root.get("employee"), employee);
        Predicate permissionPredicate = cb.equal(root.get("name"), permissionName);

        cq.select(cb.count(root))
                .where(cb.and(employeePredicate, permissionPredicate));

        Long count = entityManager.createQuery(cq).getSingleResult();
        return count > 0;
    }

    @Override
    public boolean hasEditPermission(Long employeeId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Permission> root = cq.from(Permission.class);

        Predicate employeePredicate = cb.equal(root.get("employee").get("id"), employeeId);
        Predicate permissionPredicate = cb.equal(root.get("name"), "EDIT");

        cq.select(cb.count(root))
                .where(cb.and(employeePredicate, permissionPredicate));

        Long count = entityManager.createQuery(cq).getSingleResult();
        return count > 0;
    }
}
