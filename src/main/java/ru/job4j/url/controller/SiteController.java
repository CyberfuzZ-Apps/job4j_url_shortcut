package ru.job4j.url.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url.exception.IllegalRegistrationException;
import ru.job4j.url.generator.Generator;
import ru.job4j.url.model.SiteModel;
import ru.job4j.url.service.SiteService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Класс SiteController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@RestController
@RequestMapping("/")
public class SiteController {

    private final SiteService siteService;
    private final BCryptPasswordEncoder encoder;
    private final ObjectMapper objectMapper;

    public SiteController(SiteService siteService,
                          BCryptPasswordEncoder encoder,
                          ObjectMapper objectMapper) {
        this.siteService = siteService;
        this.encoder = encoder;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<SiteModel> signUp(@RequestBody SiteModel siteModel) {
        SiteModel foundedSite = siteService.findBySite(siteModel.getSite());
        if (foundedSite != null) {
            throw new IllegalRegistrationException("Сайт уже зарегистрирован!");
        }
        String login = Generator.generateRandomString();
        String password = Generator.generateRandomString();
        String passwordForDB = encoder.encode(password);
        siteModel.setLogin(login);
        siteModel.setPassword(passwordForDB);
        SiteModel savedSite = siteService.save(siteModel);
        savedSite.setRegistration(true);
        savedSite.setPassword(password);
        return new ResponseEntity<>(
                savedSite,
                HttpStatus.CREATED
        );
    }

    @ExceptionHandler(value = {IllegalRegistrationException.class})
    public void exceptionHandler(Exception e,
                                 HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
    }
}
