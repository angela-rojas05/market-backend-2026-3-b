package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    // Obtener la lista de productos filtrados por
    // id de categoria y ordenados ascendentemente por nombre
    /*
        SELECT *
        FROM Categorias
        WHERE id_categorias = ?
        ORDER BY nombre ASC/DESC
     */

    List<Producto> findByIdCategoriaOrderByNombreAsc(Integer idCategoria);

    Optional<List<Producto>>
    findByCantidadStockLessThanAndEstado(Integer cantidadStock, Boolean estado);


}
