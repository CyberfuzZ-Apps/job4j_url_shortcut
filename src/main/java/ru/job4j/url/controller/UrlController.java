package ru.job4j.url.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url.generator.Generator;
import ru.job4j.url.model.UrlModel;
import ru.job4j.url.service.SiteService;
import ru.job4j.url.service.UrlService;

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
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        int siteId = siteService.findByLogin((String) principal).getId();
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
}
