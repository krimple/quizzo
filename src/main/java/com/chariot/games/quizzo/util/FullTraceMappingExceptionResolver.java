package com.chariot.games.quizzo.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class FullTraceMappingExceptionResolver extends
    SimpleMappingExceptionResolver {

  @Override
  protected ModelAndView getModelAndView(String viewName, Exception ex) {
    ex.printStackTrace();
    ModelAndView modelAndView = super.getModelAndView(viewName, ex);
    String[] stackTrace = ExceptionUtils.getRootCauseStackTrace(ex);
    modelAndView.addObject("rootCause", stackTrace);
    return modelAndView;
  }
}