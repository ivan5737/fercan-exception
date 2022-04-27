package com.fercan.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.fercan.exception.constants.ErrorCause;
import lombok.Getter;

/**
 * Fercan Exception class that contains the details of every exception in the Fercan Web Services in
 * its variables.
 * 
 * @author Gonzalo Ivan Lopez
 *
 */
@Getter
public class FercanException extends RuntimeException {

  private static final long serialVersionUID = 957746124409166585L;

  /**
   * Contains the 'codigo' or id of the every Fercan exception handled.
   */
  private final String codigo;

  /**
   * Contains the user message of the every Fercan exception handled
   */
  private final String mensaje;

  /**
   * Contains the stack trace of the every Fercan exception handled
   */
  private final String detalle;

  /**
   * Contains the type of error of the every Fercan exception handled
   */
  private final ErrorCause causa;

  /**
   * Contains a unique id of every Fercan exception
   */
  private final String uuid;

  /**
   * Constructor of the class that initialize all the variables.
   * 
   * @param codigo
   * @param mensaje
   * @param detalle
   * @param causa
   */
  public FercanException(String codigo, String mensaje, String detalle, ErrorCause causa) {
    super(detalle);
    this.codigo = codigo;
    this.mensaje = mensaje;
    this.detalle = detalle;
    this.causa = causa;
    this.uuid = new StringBuilder().append(UUID.randomUUID()).append("-")
        .append(System.currentTimeMillis()).toString();
  }

  /**
   * Method that get the variables of the Fercan exception in a Map to be sent like response in
   * every service when a exception occurs.
   * 
   * @return the fercan exception variables in a Map.
   */
  public Map<String, String> getResponse() {
    Map<String, String> response = new HashMap<>();
    response.put("error", this.mensaje);
    response.put("causa", this.causa.toString());
    response.put("codigo", this.codigo);
    response.put("error_id", this.uuid);
    response.put("fecha", Instant.now().toString());
    return response;
  }

  /**
   * toSrtring method that return in a String format every Fercan Exception variable.
   */
  @Override
  public String toString() {
    return new StringBuilder().append("FercanException [codigo=").append(codigo)
        .append(", mensaje=").append(mensaje).append(", detalle=").append(detalle)
        .append(", causa=").append(causa).append(", uuid=").append(uuid).append("]").toString();
  }

}
