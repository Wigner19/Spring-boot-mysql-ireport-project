package com.ti.formproject.resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ti.formproject.entities.Client;
import com.ti.formproject.services.ClientService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService service;

	@GetMapping
	public ModelAndView findAll() {
		List<Client> list = service.findAll();
		ModelAndView mav = new ModelAndView("clients");
		mav.addObject("list", list);
		return mav;
	}

	@GetMapping(value = "/formClient")
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView("formClient");
		return mav;
	}

	@PostMapping(value = "/formClient")
	public void form(Client obj, HttpServletResponse httpResponse) throws IOException {
		service.insert(obj);
		httpResponse.sendRedirect("/clients");
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		Client client = service.findById(id);
		return ResponseEntity.ok().body(client);
	}

	@GetMapping(value = "/update")
	public ModelAndView updateClient(@PathParam(value = "client_id") Long client_id) {
		ModelAndView mav = new ModelAndView("updateClient");
		Client client = service.findById(client_id);
		mav.addObject("client", client);
		return mav;
	}

	@PostMapping(value = "/updateClient")
	public void updateClient(@ModelAttribute("client") Client obj, HttpServletResponse httpResponse)
			throws IOException {
		service.update(obj.getId(), obj);
		httpResponse.sendRedirect("/clients");
	}

	@GetMapping(value = "/delete")
	public void delete(@PathParam(value = "client_id") Long client_id, HttpServletResponse httpResponse)
			throws IOException {
		service.delete(client_id);
		httpResponse.sendRedirect("/clients");
	}

	@GetMapping(value = "/pdf")
	public ResponseEntity<byte[]> generatePdf(HttpServletResponse httpResponse) throws JRException, IOException{
		byte[] data = service.generateReport();
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=clientReport.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
