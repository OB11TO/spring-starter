package com.ob11to.spring.database.repository;

import com.ob11to.spring.database.entity.Chat;
import com.ob11to.spring.database.entity.Role;
import com.ob11to.spring.database.entity.User;
import com.ob11to.spring.dto.PersonalInfo2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.lang.NonNull;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        QuerydslPredicateExecutor<User> {

    @Query("select u from User u " +
            "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u " +
            "set u.role = :role " +
            "where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findFirstByOrderByIdDesc();

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<User> findTop3ByBirthDateBefore(LocalDate localDate, Sort sort);

    List<User> findAllBy(Pageable pageable);

    //    @EntityGraph(value = "User.company")
    @EntityGraph(attributePaths = {"company", "company.locales"})
    @Override
    @NonNull
    Optional<User> findById(@NonNull Long id);

//    List<PersonalInfo> findAllByCompanyId(Integer id);

//    <T> List<T> findAllByCompanyId(Integer id, Class<T> clazz);

    @Query(nativeQuery = true,
            value = "SELECT firstname, " +
                    "lastname, " +
                    "birth_date birthDate " +
                    "FROM users " +
                    "WHERE company_id = :companyId")
    List<PersonalInfo2> findAllByCompanyId(Integer companyId);

//    @Query("select uc.chat from UserChat uc where uc.user = :user")
    @Query("select us.chat from User u " +
            "join u.userChats us " +
            "where u.id = :id")
    List<Chat> findAllByUserChats(Long id);

    Optional<User> findByUsername(String username);

}
