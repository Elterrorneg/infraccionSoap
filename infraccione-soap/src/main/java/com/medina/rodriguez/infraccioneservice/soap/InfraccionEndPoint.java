package com.medina.rodriguez.infraccioneservice.soap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.medina.rodriguez.infraccioneservice.Service.InfraccionService;
import com.medina.rodriguez.infraccioneservice.entity.Infraccion;
import com.uss.infraccionservice.GetAllInfraccionesRequest;
import com.uss.infraccionservice.GetAllInfraccionesResponse;
import com.uss.infraccionservice.InfraccionDetalle;


@Endpoint
public class InfraccionEndPoint {
	@Autowired
	private InfraccionService service;
	
	@PayloadRoot(namespace = "http://www.uss.com/infraccionservice", localPart = "GetAllInfraccionesRequest")
	@ResponsePayload
	public GetAllInfraccionesResponse findAll (@RequestPayload GetAllInfraccionesRequest request) {
		GetAllInfraccionesResponse response = new GetAllInfraccionesResponse();
		
		Pageable page = PageRequest.of(request.getOffset(), request.getLimit());
		List<Infraccion	> infracciones;
		infracciones = service.findAll(page);
		
		List<InfraccionDetalle> infraccionesResponse=new ArrayList<>();
		for (int i = 0; i < infracciones.size(); i++) {
		     InfraccionDetalle ob = new InfraccionDetalle();
		     BeanUtils.copyProperties(infracciones.get(i), ob);
		     infraccionesResponse.add(ob);    
		}
		response.getInfraccionDetalle().addAll(infraccionesResponse);
		return response;
	}
}
