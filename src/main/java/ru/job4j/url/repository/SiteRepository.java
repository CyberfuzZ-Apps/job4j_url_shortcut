package ru.job4j.url.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.model.SiteModel;

import java.util.Optional;

/**
 * Класс SiteRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface SiteRepository extends CrudRepository<SiteModel, Integer> {

    Optional<SiteModel> findByLogin(String login);

    Optional<SiteModel> findBySite(String site);

    void deleteBySite(String siteName);
}
