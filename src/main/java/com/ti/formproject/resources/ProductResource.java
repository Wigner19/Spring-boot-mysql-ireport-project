package com.ti.formproject.resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ti.formproject.entities.Product;
import com.ti.formproject.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;

	@GetMapping
	public ModelAndView findAll() {
		List<Product> list = service.findAll();
		ModelAndView mav = new ModelAndView("products");
		mav.addObject("list", list);
		return mav;
	}
	
	@GetMapping(value = "/formProduct")
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView("formProduct");
		return mav;
	}
	
	@PostMapping(value = "/formProduct")
	public void form(Product obj, HttpServletResponse httpResponse) throws IOException {
		service.insert(obj);
		httpResponse.sendRedirect("/products");
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product product = service.findById(id);
		return ResponseEntity.ok().body(product);
	}
	
	@GetMapping(value = "/update")
	public ModelAndView updateProduct(@PathParam(value="product_id") Long product_id) {
		ModelAndView mav = new ModelAndView("updateProduct");
		Product product = service.findById(product_id);
		mav.addObject("product", product);
		return mav;
	}
	
	@PostMapping(value = "/updateProduct")
	public void updateProduct(@ModelAttribute("product") Product obj, HttpServletResponse httpResponse) throws IOException {
		service.update(obj.getId(), obj);
		httpResponse.sendRedirect("/products");
	}
	
	@GetMapping(value = "/delete")
	public void delete(@PathParam(value="product_id") Long product_id, HttpServletResponse httpResponse) throws IOException {
		service.delete(product_id);
		httpResponse.sendRedirect("/products");
	}
}
