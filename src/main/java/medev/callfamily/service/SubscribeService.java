package medev.callfamily.service;

import lombok.AllArgsConstructor;
import medev.callfamily.dto.SubscribersDto;
import medev.callfamily.entity.Subscribers;
import medev.callfamily.exception.NotFoundException;
import medev.callfamily.mapper.SubscriberMapper;
import medev.callfamily.repository.SubscribeRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubscribeService {
    private static final SubscriberMapper subscriberMapper = Mappers.getMapper(SubscriberMapper.class);
    private SubscribeRepository subscribeRepository;

    public List<SubscribersDto> getSubscribers() {
        return subscriberMapper.subscribersEntityToDtoList(subscribeRepository.findAll());
    }

    public SubscribersDto getSubscribersById(Long id) throws NotFoundException{
        Subscribers subscribers = subscribeRepository.findById(id).orElseThrow(() -> new NotFoundException("Subscriber not found with id: " +id));
        return subscriberMapper.subscribersEntityToDto(subscribers);
    }

    public SubscribersDto addSubscribers(SubscribersDto subscribers) {
        return subscriberMapper.subscribersEntityToDto(subscribeRepository.save(subscriberMapper.subscribersDtoToEntity(subscribers)));
    }

    public SubscribersDto updateSubscribers(SubscribersDto subscribers, Long id) throws NotFoundException{
        subscribeRepository.findById(id).orElseThrow(() -> new NotFoundException("Subscriber not found with id: " +id));
        return subscriberMapper.subscribersEntityToDto(subscribeRepository.save(subscriberMapper.subscribersDtoToEntity(subscribers)));
    }

    public void deleteSubscribers(Long id) throws NotFoundException{
        Subscribers subscribers = subscribeRepository.findById(id).orElseThrow(() -> new NotFoundException("Subscriber not found with id: " + id));
        subscribeRepository.delete(subscribers);
    }
}
