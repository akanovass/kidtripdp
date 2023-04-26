package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.model.dto.request.TariffPlanRequest;
import kz.iitu.kidtirp.model.entity.Parent;
import kz.iitu.kidtirp.model.entity.ParentSubscriptions;
import kz.iitu.kidtirp.model.entity.Subscription;
import kz.iitu.kidtirp.repository.ParentRepository;
import kz.iitu.kidtirp.repository.ParentSubscriptionsRepository;
import kz.iitu.kidtirp.repository.SubscriptionRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class SubscriptionService {

    SubscriptionRepository subscriptionRepository;
    ParentRepository parentRepository;
    ParentSubscriptionsRepository parentSubscriptionsRepository;

    public Subscription createTariffPlan(TariffPlanRequest request) {
        Subscription Subscription = new Subscription();
        Subscription.saveRequest(request);
        return subscriptionRepository.save(Subscription);
    }

    public Subscription updateTariffPlan(TariffPlanRequest request) {
        Subscription Subscription = subscriptionRepository.findById(request.getId()).orElseThrow();
        Subscription.saveRequest(request);
        return subscriptionRepository.save(Subscription);
    }

//    public ParentSubscriptions createParentSubscriptions() {
//
//    }

}
