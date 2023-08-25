package br.com.yoursupplierapp;

import br.com.yoursupplierapp.dto.PaymentDTO;
import br.com.yoursupplierapp.entity.PaymentEntity;
import br.com.yoursupplierapp.repository.PaymentRepository;
import br.com.yoursupplierapp.service.impl.PaymentServicelmpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentTest {


    @InjectMocks
    PaymentServicelmpl servicelml;

    @Mock
    PaymentRepository repository;

    PaymentEntity payment;
    PaymentDTO paymentDTO;

}
