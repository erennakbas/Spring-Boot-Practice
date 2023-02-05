package com.warmup.project.service.impl;

import com.warmup.project.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Component
public class URLQueryParamsService {
    @PersistenceContext
    EntityManager entityManager;
    public TypedQuery<Object> executeQueryWithParams(Map<String, String> parameters, Set<String> parameterTypes, Pageable pageable, Class ObjClass){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(ObjClass);
        Root<Object> root = cq.from(ObjClass);
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String,String> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key);
            if (parameterTypes.contains(key)){predicates.add(cb.equal(root.get(key), value));}
        }

        if (predicates.size()>0) cq.where(predicates.toArray(new Predicate[0]));

        if (pageable.getSort().isSorted()) {
            pageable.getSort().forEach(order->{
                String property = order.getProperty();
                cq.orderBy(order.isAscending() ? cb.asc(root.get(property)) : cb.desc(root.get(property)));
            });

        }

        TypedQuery<Object> query = entityManager.createQuery(cq);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return query;
    }
}
