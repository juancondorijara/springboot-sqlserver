package pe.edu.vallegrande.app.service.impl;

import pe.edu.vallegrande.app.model.Customer;
import pe.edu.vallegrande.app.repository.CustomerRepository;
import pe.edu.vallegrande.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.opencsv.CSVReader;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    // ✅ Inyección del repository
    private final CustomerRepository customerRepository;

    private final TemplateEngine templateEngine;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TemplateEngine templateEngine) {
        this.customerRepository = customerRepository;
        this.templateEngine = templateEngine;
    }

    // 🛠️🔍 Implementación del método Listar Todos
    @Override
    public List<Customer> findAll() {
        log.info("Listando Datos: ");
        return customerRepository.findAll();
    }

    // 🛠️🔍 Implementación del método Listar por Estado
    @Override
    public List<Customer> findByState(String state) {
        log.info("Listando Datos por Estado: " + state);
        if ("T".equalsIgnoreCase(state)) {
            return customerRepository.findAll();
        }
        return customerRepository.findByState(state);
    }

    // 🛠️🔍 Implementación del método Listar por ID
    @Override
    public Optional<Customer> findById(Long id) {
        log.info("Listando Datos por ID: " + id);
        return customerRepository.findById(id);
    }

    // 🛠️✅ Implementación del método Registrar
    @Override
    public Customer save(Customer customer) {
        log.info("Registrondo Datos: " + customer.toString());
        customer.setState("A");
        customer.setCreatedAt(LocalDateTime.now());  // Establecer la fecha de creación al registrar
        return customerRepository.save(customer);
    }

    // 🛠️✏️ Implementación del método Actualizar
    @Override
    public Customer update(Customer customer) {
        log.info("Editando Datos: " + customer.toString());

        // Buscar el registro actual en la BD
        Customer existing = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customer.getId()));

        // Mantener la fecha de creación original
        customer.setCreatedAt(existing.getCreatedAt());

        customer.setState("A");
        customer.setUpdatedAt(LocalDateTime.now());  // Establecer la fecha de actualización
        return customerRepository.save(customer);
    }

    // 🛠️❌ Implementación del método Eliminar (Cambio de Estado) por ID
    @Override
    public Customer delete(Long id) {
        log.info("Eliminando Datos: " + id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setState("I");
        customer.setDeletedAt(LocalDateTime.now());  // Establecer la fecha de eliminación
        return customerRepository.save(customer);
    }

    // 🛠️♻️ Implementación del método Restaurar (Cambio de Estado) por ID
    @Override
    public Customer restore(Long id) {
        log.info("Restaurando Datos: " + id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setState("A");
        customer.setRestoredAt(LocalDateTime.now());  // Establecer la fecha de restauración
        return customerRepository.save(customer);
    }

    // 🛠️✅ Implementación del método Importar CSV
    public void importCsv(MultipartFile file) throws Exception {
        log.info("Importando CSV: " + file.getOriginalFilename());
        CSVReader reader =new CSVReader(new InputStreamReader(file.getInputStream()));
        List<String[]> rows = reader.readAll();
        List<Customer> customers = new ArrayList<>();
        for(int i=1; i<rows.size(); i++){
            String[] row = rows.get(i);
            Customer customer = new Customer();
            customer.setDni(row[0]);
            customer.setCellPhone(row[1]);
            customer.setFirstName(row[2]);
            customer.setLastName(row[3]);
            customer.setState(row[4]);
            customer.setCreatedAt(LocalDateTime.now());
            customers.add(customer);
        }
        customerRepository.saveAll(customers);
    }

    // 🛠️✅ Implementación del método Exportar PDF
    public byte[] exportPdfByState(String state) throws Exception {
        log.info("Exportando PDF");
        Context context = new Context();
        context.setVariable("customer", findByState(state));
        String html = templateEngine.process("customer", context);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(html, null);
        builder.toStream(out);
        builder.run();
        return out.toByteArray();
    }

    // 🛠️✅ Implementación del método Exportar Excel
    public byte[] exportExcelByState(String state) throws Exception {
        log.info("Exportando Excel");
        List<Customer> customers = findByState(state);
        XSSFWorkbook workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet("Customers");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("DNI");
        header.createCell(1).setCellValue("CELULAR");
        header.createCell(2).setCellValue("NOMBRE");
        header.createCell(3).setCellValue("APELLIDO");
        header.createCell(4).setCellValue("ESTADO");
        int rowNum = 1;
        for(Customer c : customers){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(c.getDni());
            row.createCell(1).setCellValue(c.getCellPhone());
            row.createCell(2).setCellValue(c.getFirstName());
            row.createCell(3).setCellValue(c.getLastName());
            row.createCell(4).setCellValue(c.getState());
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return out.toByteArray();
    }

}
