package ru.job4j.url.service;

import org.springframework.stereotype.Service;
import ru.job4j.url.model.SiteModel;
import ru.job4j.url.repository.SiteRepository;

import java.util.List;

/**
 * Класс SiteService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class SiteService {

    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<SiteModel> findAll() {
        return (List<SiteModel>) siteRepository.findAll();
    }

    public SiteModel findById(int id) {
        return siteRepository.findById(id).orElse(null);
    }

    public SiteModel findByLogin(String login) {
        return siteRepository.findByLogin(login).orElse(null);
    }

    public SiteModel findBySite(String site) {
        return siteRepository.findBySite(site).orElse(null);
    }

    public SiteModel save(SiteModel siteModel) {
        return siteRepository.save(siteModel);
    }

    public void deleteById(int id) {
        siteRepository.deleteById(id);
    }

    public void deleteBySiteName(String siteName) {
        siteRepository.deleteBySite(siteName);
    }
}
