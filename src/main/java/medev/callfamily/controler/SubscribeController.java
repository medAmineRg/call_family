package medev.callfamily.controler;

import lombok.AllArgsConstructor;
import medev.callfamily.dto.SubscribersDto;
import medev.callfamily.entity.Subscribers;
import medev.callfamily.exception.NotFoundException;
import medev.callfamily.service.SubscribeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/subscribe")
public class SubscribeController {

    private SubscribeService subscribeService;

    @GetMapping
    public ResponseEntity<List<SubscribersDto>> getAllSubscribers() {
        List<SubscribersDto> subscribers = subscribeService.getSubscribers();
        return ResponseEntity.ok(subscribers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscribersDto> getSubscriberById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(subscribeService.getSubscribersById(id));
    }

    @PostMapping
    public ResponseEntity<SubscribersDto> addSubscriber(@RequestBody SubscribersDto subscriber) {
        SubscribersDto createdSubscriber = subscribeService.addSubscribers(subscriber);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubscriber);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscribersDto> updateSubscriber(@RequestBody SubscribersDto subscriber, @PathVariable Long id) throws NotFoundException{
        SubscribersDto updatedSubscriber = subscribeService.updateSubscribers(subscriber, id);
        return ResponseEntity.ok(updatedSubscriber);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscriber(@PathVariable Long id) throws NotFoundException{
        subscribeService.deleteSubscribers(id);
        return ResponseEntity.noContent().build();
    }
}
