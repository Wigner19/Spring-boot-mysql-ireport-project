package com.ti.formproject.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ti.formproject.entities.Product;
import com.ti.formproject.repositories.ProductRepository;
import com.ti.formproject.services.exceptions.AlreadyRegisteredException;
import com.ti.formproject.services.exceptions.ResourceNotFoundException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> product = repository.findById(id);
		return product.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}
	
	public Product insert(Product obj) {
		alreadyExists(obj);
		return repository.save(obj);
	}
	
	public Product update(Long id, Product obj) {
		try {
			Optional<Product> product = repository.findById(id);
			updateData(product, obj);
			return repository.save(obj);			
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Product not found!");
		}
	}

	private void updateData(Optional<Product> product, Product obj) {
		alreadyExists(obj);
		product.get().setName(obj.getName());			
		product.get().setPrice(obj.getPrice());			
	}
	
	public void alreadyExists(Product obj) {
		List<Product> list = repository.findAll();
		for (Product x : list) {
			if (x.equals(obj)) {
				throw new AlreadyRegisteredException();
			}
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);			
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Product not found");
		}
	}
	
	public byte[] generateReport() throws FileNotFoundException, JRException {
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(repository.findAll());

		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream(
				"C:\\Users\\wigne\\Desktop\\Crud\\FormProject\\src\\main\\resources\\productsReport.jrxml"));

		JasperPrint report = JasperFillManager.fillReport(compileReport, null, datasource);
		
		byte[] data = JasperExportManager.exportReportToPdf(report);
		
		return data;
	}
}
