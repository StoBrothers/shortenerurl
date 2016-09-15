package com.shorterurl.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.shorterurl.exception.ApplicationException;
import com.shorterurl.service.CurrentAccount;

/**
 * Controller for show detail error.
 *
 * @author Sergey Stotskiy
 */
@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory
        .getLogger(CustomErrorController.class);

    public static final String CURRENT_USER_ATTRIBUTE = "currentUser";
    public static final String URL_ATTRIBUTE = "javax.servlet.forward.request_uri";
    public static final String STATUS_CODE = "javax.servlet.error.status_code";

    private final ErrorAttributes errorAttributes;

    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(value = "${error.path:/error}", produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request) {
        Throwable businessException = getBusinessException(request);
        Map<String, Object> errorAttributes = getErrorAttributes(request,
            businessException);

        errorAttributes.put("exception", businessException);
        errorAttributes.put("exceptions", getExceptions(businessException));

        return defaultErrorHandler(errorAttributes);
    }

    @RequestMapping(value = "${error.path:/error}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request,
        @ModelAttribute CurrentAccount currentUser) {
        Throwable businessException = getBusinessException(request);
        Map<String, Object> body = getErrorAttributes(request, businessException);

        HttpStatus status = getStatus(request);
        body.put("message", businessException == null ? "Not found object of error"
            : businessException.getMessage());

        return new ResponseEntity<>(body, status);
    }

    private ModelAndView defaultErrorHandler(Map<String, Object> errorAttributes) {
        ModelAndView mav = new ModelAndView(getErrorPath());

        errorAttributes.entrySet()
            .forEach(entry -> mav.addObject(entry.getKey(), entry.getValue()));

        return mav;
    }

    private Throwable getBusinessException(HttpServletRequest httpServletRequest) {
        Throwable error = errorAttributes
            .getError(new ServletRequestAttributes(httpServletRequest));
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
        }
        return error;
    }

    private List<Throwable> getExceptions(Throwable e) {
        List<Throwable> exceptions = new ArrayList<>();
        while (e != null) {
            exceptions.add(e);
        }
        return exceptions;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
        Throwable e) {
        Map<String, Object> result = new HashMap<>();
        if (e instanceof ApplicationException) {
            ApplicationException se = (ApplicationException) e;
            result.put("errorCode", se.getError().getCode());
            result.put("errorType", se.getError().getErrorType());
        }

        result.put("timestamp", new Date());
        Object currentUser = request.getAttribute(CURRENT_USER_ATTRIBUTE);

        if (currentUser == null) {

            SecurityContext securityContext = (SecurityContext) request.getSession()
                .getAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

            if (securityContext != null && securityContext.getAuthentication() != null
                && securityContext.getAuthentication()
                    .getPrincipal() instanceof CurrentAccount) {

                currentUser = securityContext.getAuthentication().getPrincipal();
            }
        }
        result.put("currentUser", currentUser);
        result.put("status", getStatus(request).value());
        result.put("url", request.getAttribute(URL_ATTRIBUTE));

        return result;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(STATUS_CODE);
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (IllegalArgumentException ex) {
                logger.debug("Unsupported status code {}", statusCode, ex);
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
