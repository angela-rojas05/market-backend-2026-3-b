package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.CompraCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Compra;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(int clientId) {
        List<Compra> compras = compraCrudRepository.findByIdCliente(clientId);
        return Optional.of(mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);

        if (compra.getProductos() != null) {
            compra.getProductos().forEach(producto -> producto.setCompra(compra));
        }

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
