package com.fercan.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.fercan.exception.constants.ErrorCause;

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

  public String getCodigo() {
    return codigo;
  }

  public String getMensaje() {
    return mensaje;
  }

  public String getDetalle() {
    return detalle;
  }

  public ErrorCause getCausa() {
    return causa;
  }

  public String getUuid() {
    return uuid;
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
    return "FercanException [codigo=" + codigo + ", mensaje=" + mensaje + ", detalle=" + detalle
        + ", causa=" + causa + ", uuid=" + uuid + "]";
  }

}
