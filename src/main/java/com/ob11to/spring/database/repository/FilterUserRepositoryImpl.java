package com.ob11to.spring.database.repository;

import com.ob11to.spring.database.entity.Role;
import com.ob11to.spring.database.entity.User;
import com.ob11to.spring.database.repository.querydsl.QPredicates;
import com.ob11to.spring.dto.PersonalInfo;
import com.ob11to.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ob11to.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{

    private static final String FIND_BY_ALL_COMPANY_ID_AND_ROLE = """
            SELECT 
                    firstname,
                    lastname,
                    birth_date
            FROM users
            WHERE company_id = ? AND role = ?
            """;

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

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

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_ALL_COMPANY_ID_AND_ROLE,
                (rs, rowNum) -> new PersonalInfo(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate()
                ),companyId, role.name());
    }
}
