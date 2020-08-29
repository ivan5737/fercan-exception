package com.fercan.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import com.fercan.exception.constants.ErrorCause;
import com.fercan.exception.constants.ErrorMsg;

public class ExceptionHandler extends RuntimeException {

  private static final long serialVersionUID = 5074947088470243447L;

  private static ExceptionHandler instance;

  public static final ExceptionHandler getInstance() {
    if (instance == null) {
      instance = new ExceptionHandler();
    }
    return instance;
  }

  public static FercanException getFercanException(ErrorMsg error) {
    return getFercanException(null, error);
  }

  public static FercanException getFercanException(Exception e) {
    return getFercanException(e, getMensaje(e));
  }

  public static FercanException getFercanException(Exception e, ErrorMsg error) {
    return getFercanException(e, error, error.getCausa());
  }

  public static FercanException getFercanException(Exception e, ErrorMsg error, ErrorCause causa) {
    return new FercanException(error.getCodigo(), error.getMensaje(), getStackTrace(e), causa);
  }

  public static FercanException getFercanException(Exception e, String codigo, String mensaje,
      ErrorCause causa) {
    return new FercanException(codigo, mensaje, getStackTrace(e), causa);
  }

  private static ErrorMsg getMensaje(Exception e) {
    if (e instanceof NullPointerException) {
      return ErrorMsg.ERROR_DESCONOCIDO;
    } else if (e instanceof DuplicateKeyException) {
      return ErrorMsg.ERROR_LLAVE_UNICA;
    } else if (e instanceof DataIntegrityViolationException) {
      return ErrorMsg.ERROR_LLAVE_FORANEA;
    } else {
      return ErrorMsg.ERROR_DESCONOCIDO;
    }
  }

  private static String getStackTrace(Exception e) {
    if (e == null) {
      return "";
    }
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }
}
