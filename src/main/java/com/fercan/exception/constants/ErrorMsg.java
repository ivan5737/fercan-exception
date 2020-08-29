package com.fercan.exception.constants;

/**
 * Enum class that contains the error messages
 * when there is an exception.
 * 
 * @author ivan
 *
 */
public enum ErrorMsg {
	ERROR_DESCONOCIDO("0000", "Error desconocido, favor de reportarlo al administrador.", ErrorCause.CODIGO),
	ERROR_NO_DATOS_BUSQUEDA_USUARIOS("0001",
			"Error, debes de proporcionar al menos el id de usuario, correo, o nombre para la búsqueda",
			ErrorCause.REGLANEGOCIO),
	ERROR_NO_DATOS_BUSQUEDA_USUARIO("0002", "Error, debes de proporcionar el id de usuario para la búsqueda",
			ErrorCause.REGLANEGOCIO),
	ERROR_USUARIO_NO_ENCONTRADO("0003", "El usuario o la contraseña son incorrectos.", ErrorCause.REGLANEGOCIO),
	ERROR_USUARIO_ACTIVO("0004", "El usuario tiene una sesión activa.", ErrorCause.REGLANEGOCIO),
	ERROR_INICIO_SESION_TOKEN("0005",
			"Ocurrió un error al generar token de inicio de sesión, favor de reportarlo al administrador.",
			ErrorCause.CODIGO),
	ERROR_TOKEN_NO_VALIDO("0006", "El token proporcionado no es válido.", ErrorCause.REGLANEGOCIO),
	ERROR_TOKEN_VACIO("0007", "Debe de proporcionar un token para la consulta del servicio.", ErrorCause.REGLANEGOCIO),
	ERROR_CIERRE_SESION("0008", "Ocurrió un error al cerrar la sesión, favor de reportarlo al administrador.",
			ErrorCause.CODIGO),
	ERROR_RESPUESTA_INCORRECTA("0009", "La pregunta o la respuesta es incorrecta.", ErrorCause.REGLANEGOCIO),
	ERROR_ID_USUAIRO_INCORRECTO("0010", "El id de usuario proporcionado es incorrecto.", ErrorCause.REGLANEGOCIO),
	ERROR_CORREO_DUPLICADO("0020", "Error, ya existe un usuario con el correo ingresado.", ErrorCause.REGLANEGOCIO),
	ERROR_LLAVE_UNICA("0021", "Error, se quiso insertar algún dato duplicado en la base.", ErrorCause.REGLANEGOCIO),
	ERROR_LLAVE_FORANEA("0023",
			"Error, algún id con llave foranea que se ha enviado es incorrecto "
					+ "o no existe, favor de reportarlo con el administrador del sistema.",
			ErrorCause.CODIGO),
	ERROR_NO_ESTADOS_ENCONTRADOS("0030", "Error, no se encontraron estados con el ID proporcionado.",
			ErrorCause.REGLANEGOCIO),
	ERROR_NO_MUNICIPIOS_ENCONTRADOS("0031", "Error, no se encontraron municipios con el ID proporcionado.",
			ErrorCause.REGLANEGOCIO),
	ERROR_NO_COLONIAS_ENCONTRADAS("0032", "Error, no se encontraron colonias con los ID's proporcionados.",
			ErrorCause.REGLANEGOCIO),
	ERROR_NO_COLONIAS_ZIP_CODE("0033", "Error, no se encontraron colonias con el código postal proporcionado.",
			ErrorCause.REGLANEGOCIO),
	ERROR_PASSWORD("0034", "Error, la contraseña solo puede llevar letras y números.", ErrorCause.REGLANEGOCIO),
	ERROR_PASSWORD_SIZE("0035", "Error, la contraseña debe de tener una longitud de entre 8 y 16 caracteres.",
			ErrorCause.REGLANEGOCIO),
	ERROR_RESPUESTA("0036", "Error, la respuesta a tu pregunta solo puede llevar letras, números y espacios.",
			ErrorCause.REGLANEGOCIO);

    /**
     * the variable codigo that 
     * contain the id of the exception.
     */
	private String codigo;

	/**
     * the variable that contain 
     * the message of the exception.
     */
	private String mensaje;

	/**
     * the variable that contain 
     * the cause of the exception.
     */
	private ErrorCause causa;

	/**
	 * Constructor of the enum.
	 */
	ErrorMsg(String codigo, String mensaje, ErrorCause causa) {
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.causa = causa;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public ErrorCause getCausa() {
		return causa;
	}

}
