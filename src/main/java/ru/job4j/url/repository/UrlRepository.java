package ru.job4j.url.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.url.model.UrlModel;

import java.util.List;
import java.util.Optional;

/**
 * Класс UrlRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface UrlRepository extends CrudRepository<UrlModel, Integer> {

    Optional<List<UrlModel>> findAllBySiteId(int siteId);

    Optional<UrlModel> findByUrl(String url);

    Optional<UrlModel> findByCode(String code);

    @Transactional
    @Modifying
    @Query(value = "update UrlModel u set u.total = u.total + 1 where u.id = :id")
    void incrementTotal(@Param("id") int id);

}
