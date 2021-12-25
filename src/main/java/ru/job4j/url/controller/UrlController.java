package ru.job4j.url.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url.generator.Generator;
import ru.job4j.url.model.StatisticModel;
import ru.job4j.url.model.UrlModel;
import ru.job4j.url.service.SiteService;
import ru.job4j.url.service.UrlService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс UrlController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@RestController
@RequestMapping("/")
public class UrlController {

    private final UrlService urlService;
    private final SiteService siteService;

    public UrlController(UrlService urlService, SiteService siteService) {
        this.urlService = urlService;
        this.siteService = siteService;
    }

    @PostMapping("/convert")
    public ResponseEntity<UrlModel> convertUrl(@RequestBody UrlModel urlModel) {
        UrlModel foundedUrlModel = urlService.findByUrl(urlModel.getUrl());
        if (foundedUrlModel != null) {
            return new ResponseEntity<>(
                    foundedUrlModel,
                    HttpStatus.OK
            );
        }
        int siteId = getSiteId();
        urlModel.setSiteId(siteId);
        String code = Generator.generateShortUrl(urlModel.getUrl());
        UrlModel collisionUrl = urlService.findByCode(code);
        while (collisionUrl != null) {
            code = Generator.generateShortUrl(urlModel.getUrl()
                    + Generator.generateRandomString());
            collisionUrl = urlService.findByCode(code);
        }
        urlModel.setCode(code);
        UrlModel savedUrlModel = urlService.save(urlModel);
        return new ResponseEntity<>(
                savedUrlModel,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<StatisticModel>> getStatistic() {
        List<UrlModel> allUrlsBySiteId = urlService.findAllBySiteId(getSiteId());
        List<StatisticModel> statisticModels = new ArrayList<>(allUrlsBySiteId.size());
        for (UrlModel url : allUrlsBySiteId) {
            statisticModels.add(new StatisticModel(
                    url.getUrl(),
                    url.getTotal()
            ));
        }
        return new ResponseEntity<>(
                statisticModels,
                HttpStatus.OK
        );
    }

    private int getSiteId() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return siteService.findByLogin((String) principal).getId();
    }
}
