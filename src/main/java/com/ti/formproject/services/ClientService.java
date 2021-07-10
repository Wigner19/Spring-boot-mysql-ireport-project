package com.ti.formproject.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ti.formproject.entities.Client;
import com.ti.formproject.repositories.ClientRepository;
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
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Client findById(Long id) {
		Optional<Client> client = repository.findById(id);
		return client.orElseThrow(() -> new ResourceNotFoundException("Client not found"));
	}

	public Client insert(Client obj) {
		alreadyExists(obj);
		return repository.save(obj);
	}

	public Client update(Long id, Client obj) {
		try {
			Optional<Client> client = repository.findById(id);
			updateData(client, obj);
			return repository.save(obj);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Client not found!");
		}
	}

	private void updateData(Optional<Client> client, Client obj) {
		alreadyExists(obj);
		client.get().setName(obj.getName());
		client.get().setEmail(obj.getEmail());
		client.get().setPassword(obj.getPassword());
	}

	public void alreadyExists(Client obj) {
		List<Client> list = repository.findAll();
		for (Client x : list) {
			if (x.equals(obj)) {
				throw new AlreadyRegisteredException();
			}
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Client not found");
		}
	}

	public byte[] generateReport() throws FileNotFoundException, JRException {
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(repository.findAll());

		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream(
				"C:\\Users\\wigne\\Desktop\\Crud\\FormProject\\src\\main\\resources\\clientsReport.jrxml"));

		JasperPrint report = JasperFillManager.fillReport(compileReport, null, datasource);
		byte[] data = JasperExportManager.exportReportToPdf(report);
		
		return data;
	}
}
