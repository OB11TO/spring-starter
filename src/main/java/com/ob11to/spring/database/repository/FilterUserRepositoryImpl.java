package com.ob11to.spring.database.repository;

import com.ob11to.spring.database.entity.User;
import com.ob11to.spring.database.repository.querydsl.QPredicates;
import com.ob11to.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ob11to.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter userFilter) {
//        var cb = entityManager.getCriteriaBuilder();
//        var criteria = cb.createQuery(User.class);
//
//        var user = criteria.from(User.class);
//        criteria.select(user);
//
//        List<Predicate> predicates = new ArrayList<>();
//        if(userFilter.getFirstname() != null){
//            predicates.add(cb.like(user.get("firstname"),userFilter.getFirstname()));
//        }
//        if(userFilter.getLastname() != null){
//            predicates.add(cb.like(user.get("lastname"),userFilter.getLastname()));
//        }
//        if(userFilter.getBirthDate() != null){
//            predicates.add(cb.lessThan(user.get("birthDate"),userFilter.getBirthDate()));
//        }
//        criteria.where(predicates.toArray(Predicate[]::new));
//
//        return entityManager.createQuery(criteria).getResultList();
        var predicate = QPredicates.builder()
                .add(userFilter.getFirstname(), user.firstname::containsIgnoreCase)
                .add(userFilter.getLastname(), user.lastname::containsIgnoreCase)
                .add(userFilter.getBirthDate(), user.birthDate::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }
}
