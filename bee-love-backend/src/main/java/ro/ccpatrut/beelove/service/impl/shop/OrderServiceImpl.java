package ro.ccpatrut.beelove.service.impl.shop;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ccpatrut.beelove.dto.shop.AddressDTO;
import ro.ccpatrut.beelove.dto.shop.OrderDTO;
import ro.ccpatrut.beelove.dto.shop.OrderedProductDTO;
import ro.ccpatrut.beelove.dto.shop.UserDTO;
import ro.ccpatrut.beelove.entities.shop.OrderEntity;
import ro.ccpatrut.beelove.entities.shop.OrderedProductEntity;
import ro.ccpatrut.beelove.repository.OrderRepository;
import ro.ccpatrut.beelove.service.OrderService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderDTO> getOrders() {
        final List<OrderDTO> orderDTOS = new ArrayList<>();

        final Iterable<OrderEntity> orderEntityIterable = this.orderRepository.findAll();
        final List<OrderEntity> orderEntityList = this.converIterableToList(orderEntityIterable);

        orderEntityList.forEach(orderEntity -> {
            final OrderDTO orderDTO = new OrderDTO();
            final AddressDTO addressDTO = new AddressDTO();
            final UserDTO userDTO = new UserDTO();

            addressDTO.setCity(orderEntity.getCity());
            addressDTO.setFullAddress(orderEntity.getFullAddress());
            addressDTO.setPostalCode(orderEntity.getPostalCode());
            addressDTO.setPhoneNumber(orderEntity.getPhoneNumber());

            userDTO.setEmailAddress(orderEntity.getEmailAddress());
            userDTO.setFirstName(orderEntity.getFirstName());
            userDTO.setLastName(orderEntity.getLastName());

            final List<OrderedProductDTO> productDTOS = new ArrayList<>();
            final List<OrderedProductEntity> orderedProductEntityList = orderEntity.getOrderedProductEntityList();
            for (final OrderedProductEntity orderedProductEntity : orderedProductEntityList) {
                productDTOS.add(this.modelMapper.map(orderedProductEntity, OrderedProductDTO.class));
            }
            orderDTO.setAddress(addressDTO);
            orderDTO.setProducts(productDTOS);
            orderDTO.setUserData(userDTO);

            orderDTOS.add(orderDTO);
        });

        return orderDTOS;
    }

    private List<OrderEntity> converIterableToList(final Iterable<OrderEntity> orderEntityIterable) {
        final List<OrderEntity> orderEntityList = new ArrayList<>();
        orderEntityIterable.forEach(orderEntityList::add);
        return orderEntityList;
    }


}
