package pe.edu.vallegrande.app.service;

import pe.edu.vallegrande.app.model.Customer;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {

    // ⚙️🔍 Definir método Listar Todos
    List<Customer> findAll();

    // ⚙️🔍 Definir método Listar por Estado
    List<Customer> findByState(String state);

    // ⚙️🔍 Definir método Listar por ID
    Optional<Customer> findById(Long id);

    // ⚙️✅ Definir método Registrar
    Customer save(Customer customer);

    // ⚙️✏️ Definir método Actualizar
    Customer update(Customer customer);

    // ⚙️❌ Definir método Eliminar (Cambio de Estado) por ID
    Customer delete(Long id);

    // ⚙️♻️ Definir método Restaurar (Cambio de Estado) por ID
    Customer restore(Long id);

    // ⚙️✅ Definir método Importar CSV
    void importCsv(MultipartFile file) throws Exception;

    // ⚙️✅ Definir método Exportar PDF
    byte[] exportPdfByState(String state) throws Exception;

    // ⚙️✅ Definir método Exportar Excel
    byte[] exportExcelByState(String state) throws Exception;
    
}
