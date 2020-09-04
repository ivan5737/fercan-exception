package com.fercan.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.fercan.exception.constants.ErrorCause;
import lombok.Getter;

/**
 * 
 * @author Gonzalo Ivan Lopez
 *
 */
@Getter
public class FercanException extends RuntimeException {

  private static final long serialVersionUID = 957746124409166585L;

  private final String codigo;

  private final String mensaje;

  private final String detalle;

  private final ErrorCause causa;

  private final String uuid;

  public FercanException(String codigo, String mensaje, String detalle, ErrorCause causa) {
    super(detalle);
    this.codigo = codigo;
    this.mensaje = mensaje;
    this.detalle = detalle;
    this.causa = causa;
    this.uuid = new StringBuilder().append(UUID.randomUUID()).append("-")
        .append(System.currentTimeMillis()).toString();
  }

  public Map<String, String> getResponse() {
    Map<String, String> response = new HashMap<>();
    response.put("error", this.mensaje);
    response.put("causa", this.causa.toString());
    response.put("codigo", this.codigo);
    response.put("error_id", this.uuid);
    return response;
  }

  @Override
  public String toString() {
    return new StringBuilder().append("FercanException [codigo=").append(codigo)
        .append(", mensaje=").append(mensaje).append(", detalle=").append(detalle)
        .append(", causa=").append(causa).append(", uuid=").append(uuid).append("]").toString();
  }

}
