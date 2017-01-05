package org.demoiselle.jee.rest.exception.mapper;

import java.util.logging.Logger;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.demoiselle.jee.core.api.error.ErrorTreatmentInterface;
import org.demoiselle.jee.core.exception.DemoiselleException;

@Provider
public class DemoiselleExceptionMapper implements ExceptionMapper<DemoiselleException> {

	private Logger logger = CDI.current().select(Logger.class).get();
	
	@Context
	protected HttpServletRequest httpRequest;

	@Inject
	protected ErrorTreatmentInterface errorTreatment;

	@Override
	public Response toResponse(DemoiselleException exception) {
		logger.fine("Using DemoiselleExceptionMapper");
		return errorTreatment.getFormatedError(exception, httpRequest);
	}

}