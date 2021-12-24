package ru.job4j.url.repository;

import org.springframework.data.repository.CrudRepository;
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

}
