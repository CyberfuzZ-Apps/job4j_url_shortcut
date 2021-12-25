package ru.job4j.url.service;

import org.springframework.stereotype.Service;
import ru.job4j.url.model.UrlModel;
import ru.job4j.url.repository.UrlRepository;

import java.util.List;

/**
 * Класс UrlService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public List<UrlModel> findAllBySiteId(int siteId) {
        return urlRepository.findAllBySiteId(siteId).orElse(List.of());
    }

    public UrlModel findByUrl(String url) {
        return urlRepository.findByUrl(url).orElse(null);
    }

    public UrlModel findByCode(String code) {
        return urlRepository.findByCode(code).orElse(null);
    }

    public UrlModel save(UrlModel urlModel) {
        return urlRepository.save(urlModel);
    }

    public void incrementTotal(int id) {
        urlRepository.incrementTotal(id);
    }
}
