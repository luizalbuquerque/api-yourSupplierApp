package br.com.yoursupplierapp.dto;

import java.util.List;

public class OrderDto {

    private Long idOrder;
    private CustomerDTO customer;
    private SupplierDTO supplier;
    private PromoterDTO promoter;
    private PaymentDTO payment;
    private List<ProductDTO> products;


}
