package medev.callfamily.mapper;

import medev.callfamily.dto.SubscribersDto;
import medev.callfamily.entity.Subscribers;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SubscriberMapper {

    SubscribersDto subscribersEntityToDto(Subscribers subscribers);
    Subscribers subscribersDtoToEntity(SubscribersDto subscribersDto);

    List<SubscribersDto> subscribersEntityToDtoList(List<Subscribers> subscribers);
    List<Subscribers> subscribersDtoToEntityList(List<SubscribersDto> subscribersDto);

}
