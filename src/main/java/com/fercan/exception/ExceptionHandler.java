package com.fercan.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.TransientDataAccessResourceException;
import com.fercan.exception.constants.ErrorCause;
import com.fercan.exception.constants.ErrorMsg;

/**
 * 
 * @author Gonzalo Ivan Lopez
 *
 */
public class ExceptionHandler extends RuntimeException {

  private static final long serialVersionUID = 5074947088470243447L;

  private static final ExceptionHandler INSTANCE = new ExceptionHandler();

  public static final ExceptionHandler getInstance() {
    return INSTANCE;
  }

  public FercanException getFercanException(Exception e) {
    if (e instanceof FercanException) {
      return FercanException.class.cast(e);
    }
    return getFercanException(e, getMensaje(e));
  }

  public FercanException getFercanException(ErrorMsg error) {
    return getFercanException(null, error);
  }

  public FercanException getFercanException(Exception e, ErrorMsg error) {
    return getFercanException(e, error, error.getCausa());
  }

  private FercanException getFercanException(Exception e, ErrorMsg error, ErrorCause causa) {
    return new FercanException(error.getCodigo(), error.getMensaje(), getStackTrace(e), causa);
  }

  public FercanException getFercanException(Exception e, String codigo, String mensaje,
      ErrorCause causa) {
    return new FercanException(codigo, mensaje, getStackTrace(e), causa);
  }

  private static ErrorMsg getMensaje(Exception e) {
    ErrorMsg errorMsg = null;
    if (e instanceof NullPointerException) {
      errorMsg = ErrorMsg.ERROR_DESCONOCIDO;
    } else if (e instanceof DuplicateKeyException) {
      errorMsg = ErrorMsg.ERROR_LLAVE_UNICA;
    } else if (e instanceof DataIntegrityViolationException) {
      errorMsg = ErrorMsg.ERROR_LLAVE_FORANEA;
    } else if (e instanceof TransientDataAccessResourceException) {
      errorMsg = ErrorMsg.ERROR_SQL_QUERY;
    } else {
      errorMsg = ErrorMsg.ERROR_DESCONOCIDO;
    }
    return errorMsg;
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
