package com.fercan.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fercan.exception.constants.ErrorCause;
import com.fercan.exception.constants.ErrorMsg;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerTest {

  @InjectMocks
  private ExceptionHandler exceptionHandler;

  @Test
  void shouldGetInstanceOfExceptionHandler() {
    // when
    ExceptionHandler exception = ExceptionHandler.getInstance();

    // then
    assertNotNull(exception);
  }

  @Test
  void shouldGetFercanExceptionOnNullPointer() {
    // when
    FercanException fe = exceptionHandler
        .getFercanException(new FercanException("0000", "Error Fercan", null, ErrorCause.CODIGO));

    // then
    assertNotNull(fe);
    assertNotNull(fe.toString());
    assertEquals("Error Fercan", fe.getMensaje());
    assertEquals(ErrorCause.CODIGO, fe.getCausa());
  }

  @Test
  void shouldGetFercanExceptionOnAnyException() {
    // when
    FercanException fe = exceptionHandler.getFercanException(new ArrayIndexOutOfBoundsException());

    // then
    assertNotNull(fe);
    assertNotNull(fe.toString());
    assertEquals("Error desconocido, favor de reportarlo al administrador.", fe.getMensaje());
    assertEquals(ErrorCause.CODIGO, fe.getCausa());
  }

  @Test
  void shouldThrowFercanException() {
    // when
    ErrorMsg error = ErrorMsg.ERROR_ID_PREGUNTA;
    String mensaje = String.format(error.getMensaje(), String.valueOf(1));
    FercanException fe = exceptionHandler.getFercanException(null, error, mensaje);

    // then
    assertNotNull(fe);
    assertNotNull(fe.toString());
    assertEquals("El id de pregunta (1) que se ha enviado desde el servicio es incorrecto.",
        fe.getMensaje());
    assertEquals(ErrorCause.REGLANEGOCIO, fe.getCausa());
    assertEquals("0016", fe.getCodigo());
    assertEquals("", fe.getDetalle());
    assertNotNull(fe.getUuid());
  }

  @Test
  void shouldGetResponseDataOnFercanException() {
    // given
    FercanException fe = exceptionHandler.getFercanException(ErrorMsg.ERROR_CIERRE_SESION);
    // when
    Map<String, String> response = fe.getResponse();

    // then
    assertNotNull(response);
  }

}
