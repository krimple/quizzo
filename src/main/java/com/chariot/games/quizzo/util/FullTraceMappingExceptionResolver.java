package com.chariot.games.quizzo.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class FullTraceMappingExceptionResolver extends
    SimpleMappingExceptionResolver {

  Logger logger = LoggerFactory.getLogger(getClass());
      
  @Override
  protected ModelAndView getModelAndView(String viewName, Exception ex) {
    logger.error("Exception thrown in web tier.", ex);
    ModelAndView modelAndView = super.getModelAndView(viewName, ex);
    modelAndView.addObject("cleanTrace", ExceptionUtils.getStackTrace(ex));

    if (ex.getCause() != null) { 
      logger.error("Caused by...", ex.getCause());
      modelAndView.addObject("rootCause", ex.getCause());

    };
    return modelAndView;
  }
}