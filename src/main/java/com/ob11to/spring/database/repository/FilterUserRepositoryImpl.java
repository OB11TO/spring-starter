package com.ob11to.spring.database.repository;

import com.ob11to.spring.database.entity.Role;
import com.ob11to.spring.database.entity.User;
import com.ob11to.spring.database.repository.querydsl.QPredicates;
import com.ob11to.spring.dto.PersonalInfo;
import com.ob11to.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static com.ob11to.spring.database.entity.QUser.user;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private static final String FIND_BY_ALL_COMPANY_ID_AND_ROLE = """
            SELECT
                    firstname,
                    lastname,
                    birth_date
            FROM users
            WHERE company_id = ? AND role = ?
            """;

    private static final String UPDATE_COMPANY_ID_AND_ROLE = """
            UPDATE users
            SET company_id = ?,
                role = ?
            WHERE id = ?
            """;
    private static final String UPDATE_COMPANY_ID_AND_ROLE_NAMED = """
            UPDATE users
            SET company_id = :companyId,
                role = :role
            WHERE id = :id
            """;


    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
                ), companyId, role.name());
    }

    @Override
    public int[] updateCompanyAndRole(List<User> users) {
        var args = users.stream()
                .map(user -> new Object[]{user.getCompany().getId(), user.getRole().name(), user.getId()})
                .collect(toList());

        return jdbcTemplate.batchUpdate(UPDATE_COMPANY_ID_AND_ROLE, args);
    }

    @Override
    public int[] updateCompanyAndRoleNamed(List<User> users) {
        var args = users.stream()
                .map(user -> Map.of(
                        "companyId", user.getCompany().getId(),
                        "role", user.getRole().name(),
                        "id", user.getId()
                ))
                .map(MapSqlParameterSource::new)
                .toArray(MapSqlParameterSource[]::new);

        return namedParameterJdbcTemplate.batchUpdate(UPDATE_COMPANY_ID_AND_ROLE_NAMED,args);
    }
}
