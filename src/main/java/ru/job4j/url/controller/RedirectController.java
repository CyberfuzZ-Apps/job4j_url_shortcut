package ru.job4j.url.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url.model.UrlModel;
import ru.job4j.url.service.UrlService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс RedirectController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Controller
@RequestMapping("/redirect")
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{code}")
    public void redirect(@PathVariable String code,
                                         HttpServletResponse response) {
        UrlModel urlModel = urlService.findByCode(code);
        if (urlModel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ссылка не найдена");
        }
        urlService.incrementTotal(urlModel.getId());
        response.addHeader("REDIRECT", urlModel.getUrl());
        response.setStatus(302);
    }
}
