package ro.ccpatrut.beelove.service.impl.shop;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ccpatrut.beelove.dto.shop.AddressDTO;
import ro.ccpatrut.beelove.dto.shop.OrderDTO;
import ro.ccpatrut.beelove.dto.shop.OrderedProductDTO;
import ro.ccpatrut.beelove.dto.shop.UserDTO;
import ro.ccpatrut.beelove.entities.ProductEntity;
import ro.ccpatrut.beelove.entities.shop.OrderEntity;
import ro.ccpatrut.beelove.entities.shop.OrderedProductEntity;
import ro.ccpatrut.beelove.repository.OrderRepository;
import ro.ccpatrut.beelove.repository.ProductRepository;
import ro.ccpatrut.beelove.service.CheckoutService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void doCheckout(final OrderDTO orderDTO) {
        this.validateProducts(orderDTO.getProducts());

        final AddressDTO addressDTO = orderDTO.getAddress();
        final UserDTO userDTO = orderDTO.getUserData();

        final OrderEntity orderEntity = new OrderEntity();
        //user
        orderEntity.setFirstName(userDTO.getFirstName());
        orderEntity.setLastName(userDTO.getLastName());
        orderEntity.setEmailAddress(userDTO.getEmailAddress());

        //address
        orderEntity.setCity(addressDTO.getCity());
        orderEntity.setPostalCode(addressDTO.getPostalCode());
        orderEntity.setPhoneNumber(addressDTO.getPhoneNumber());
        orderEntity.setFullAddress(addressDTO.getFullAddress());

        orderEntity.setOrderedProductEntityList(this.mapProducts(orderDTO.getProducts(), orderEntity));
        this.orderRepository.save(orderEntity);
    }


    private List<OrderedProductEntity> mapProducts(final List<OrderedProductDTO> orderedProductDTOList,
                                                   final OrderEntity orderEntity) {
        final List<OrderedProductEntity> productOrderEntities = new ArrayList<>();
        orderedProductDTOList.forEach(orderedProductDTO -> {
                    final OrderedProductEntity orderedProductEntity = this.modelMapper.map(orderedProductDTO, OrderedProductEntity.class);
                    orderedProductEntity.setId(null);
                    orderedProductEntity.setOrderEntity(orderEntity);
                    productOrderEntities.add(orderedProductEntity);
                }
        );
        return productOrderEntities;
    }

    private void validateProducts(final List<OrderedProductDTO> orderedProductDTOList) {
        this.checkIfProductsExistsInWarehouse(orderedProductDTOList);
    }

    private void checkIfProductsExistsInWarehouse(final List<OrderedProductDTO> orderedProductDTOList) {
        final List<ProductEntity> productEntityList = new ArrayList<>();
        orderedProductDTOList.forEach(orderedProductDTO -> {
            final Optional<ProductEntity> productEntityOptional = this.productRepository.findById(orderedProductDTO.getId());
            productEntityOptional.ifPresent(productEntity -> {
                if (productEntity.getAvailableUnits() < orderedProductDTO.getUnit()) {
                    throw new IllegalArgumentException("The quantity of products ordered is unavailable");
                }
                productEntityList.add(productEntity);

            });
            productEntityOptional.orElseThrow(() -> new IllegalArgumentException("Product with id: " + orderedProductDTO.getId() + " does not exists!"));
        });
    }
}


