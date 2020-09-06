package com.fercan.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.TransientDataAccessResourceException;
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
  void shouldGetFercanExceptionOnFercanException() {
    // when
    FercanException fe = exceptionHandler.getFercanException(new NullPointerException());

    // then
    assertNotNull(fe);
    assertNotNull(fe.toString());
    assertEquals("Error desconocido, favor de reportarlo al administrador.", fe.getMensaje());
    assertEquals(ErrorCause.CODIGO, fe.getCausa());
  }

  @Test
  void shouldGetFercanExceptionOnError() {
    // when
    FercanException fe = exceptionHandler.getFercanException(ErrorMsg.ERROR_CIERRE_SESION);

    // then
    assertNotNull(fe);
    assertNotNull(fe.toString());
    assertEquals("Ocurrió un error al cerrar la sesión, favor de reportarlo al administrador.",
        fe.getMensaje());
    assertEquals(ErrorCause.CODIGO, fe.getCausa());
  }

  @Test
  void shouldGetFercanExceptionOnDuplicateKey() {
    // when
    FercanException fe = exceptionHandler.getFercanException(new DuplicateKeyException("foo"));

    // then
    assertNotNull(fe);
    assertNotNull(fe.toString());
    assertEquals("Error, se quiso insertar algún dato duplicado en la base.", fe.getMensaje());
    assertEquals(ErrorCause.REGLANEGOCIO, fe.getCausa());
  }

  @Test
  void shouldGetFercanExceptionOnDataIntegrityViolation() {
    // when
    FercanException fe =
        exceptionHandler.getFercanException(new DataIntegrityViolationException("msg"));

    // then
    assertNotNull(fe);
    assertNotNull(fe.toString());
    assertEquals(
        "Error, algún id con llave foranea que se ha enviado es incorrecto "
            + "o no existe, favor de reportarlo con el administrador del sistema.",
        fe.getMensaje());
    assertEquals(ErrorCause.CODIGO, fe.getCausa());
  }

  @Test
  void shouldGetFercanExceptionOnTransientDataAccess() {
    // when
    FercanException fe = exceptionHandler
        .getFercanException(new TransientDataAccessResourceException("TransientDataAccess"));

    // then
    assertNotNull(fe);
    assertNotNull(fe.toString());
    assertEquals("Error, query de la Base de datos incorrecto, favor de reportarlo con el "
        + "administrador del sistema.", fe.getMensaje());
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
