package com.fercan.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.TransientDataAccessResourceException;
import com.fercan.exception.constants.ErrorMsg;

/**
 * Exception handler class from the Fercan Web Services.
 * 
 * This class is called in the Fercan Web Services when a exception is throw, and its goal is build
 * the FercanException object.
 * 
 * @author Gonzalo Ivan Lopez
 *
 */
public class ExceptionHandler extends RuntimeException {

  private static final long serialVersionUID = 5074947088470243447L;

  /**
   * The variable INSTANCE that has the static final instance of the ExceptionHandler class.
   */
  private static final ExceptionHandler INSTANCE = new ExceptionHandler();

  /**
   * This method return the instance of this singleton class.
   * 
   * @return the static final instance of this class.
   */
  public static final ExceptionHandler getInstance() {
    return INSTANCE;
  }

  /**
   * This method get the exception thrown in the Fercan Web Service and with that information
   * generate the FercanException object.
   * 
   * @param e.
   * @return FercanException object.
   */
  public FercanException getFercanException(Exception e) {
    if (e instanceof FercanException) {
      return FercanException.class.cast(e);
    }
    return getFercanException(e, getMensaje(e));
  }

  /**
   * This method get the ErrorMsg sent from the Fercan Web Service and generate the FercanException
   * object.
   * 
   * @param error.
   * @return FercanException object.
   */
  public FercanException getFercanException(ErrorMsg error) {
    return getFercanException(null, error);
  }

  /**
   * This method get the Exception and ErrorMsg sent from the Fercan Web Service and generate the
   * FercanException object.
   * 
   * @param e.
   * @param error.
   * @return FercanException object.
   */
  public FercanException getFercanException(Exception e, ErrorMsg error) {
    return new FercanException(error.getCodigo(), error.getMensaje(), getStackTrace(e),
        error.getCausa());
  }

  /**
   * This method get the Exception, ErrorMsg and custom message sent from the Fercan Web Service and
   * generate the FercanException object.
   * 
   * @param e.
   * @param error.
   * @param message.
   * @return FercanException object.
   */
  public FercanException getFercanException(Exception e, ErrorMsg error, String message) {
    return new FercanException(error.getCodigo(), message, getStackTrace(e), error.getCausa());
  }

  /**
   * Get the ErrorMsg object depending of the exception type sent.
   * 
   * @param e.
   * @return ErrorMsg object.
   */
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

  /**
   * this method generate the stacktrace exception from the Exception sent.
   * 
   * @param e.
   * @return String stack trace Object.
   */
  private static String getStackTrace(Exception e) {
    if (e == null) {
      return "";
    }
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }
}
