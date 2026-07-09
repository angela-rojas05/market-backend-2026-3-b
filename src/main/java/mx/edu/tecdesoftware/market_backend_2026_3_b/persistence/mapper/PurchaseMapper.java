package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Compra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {PurchaseItemMapper.class}
)
public interface PurchaseMapper {

    @Mapping(source = "idCompra", target = "purchaseId")
    @Mapping(source = "idCliente", target = "clientId")
    @Mapping(source = "medioPago", target = "paymentMethod")
    @Mapping(source = "productos", target = "items")
    Purchase toPurchase(Compra compra);

    List<Purchase> toPurchases(List<Compra> compras);

    @Mapping(source = "purchaseId", target = "idCompra")
    @Mapping(source = "clientId", target = "idCliente")
    @Mapping(source = "paymentMethod", target = "medioPago")
    @Mapping(source = "items", target = "productos")
    Compra toCompra(Purchase purchase);
}