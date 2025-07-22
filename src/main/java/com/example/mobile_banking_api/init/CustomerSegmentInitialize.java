package com.example.mobile_banking_api.init;

import com.example.mobile_banking_api.domain.CustomerSegment;
import com.example.mobile_banking_api.repository.CustomerSegmentRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomerSegmentInitialize {

    private final CustomerSegmentRepository customerSegmentRepository;

    @PostConstruct
    public void init(){

        if (customerSegmentRepository.count() == 0) {
            CustomerSegment segmentRegular = new CustomerSegment();
            segmentRegular.setSegment("REGULAR");
            segmentRegular.setDescription("REGULAR");
            segmentRegular.setIsDeleted(false);

            CustomerSegment segmentSeliver = new CustomerSegment();
            segmentSeliver.setSegment("SELIVER");
            segmentSeliver.setDescription("SELIVER");
            segmentSeliver.setIsDeleted(false);

            CustomerSegment segmentGold = new CustomerSegment();
            segmentGold.setSegment("GOLD");
            segmentGold.setDescription("GOLD");
            segmentGold.setIsDeleted(false);

            customerSegmentRepository.saveAll(
                    List.of(segmentRegular, segmentSeliver, segmentGold)
            );
        }

    }

}
