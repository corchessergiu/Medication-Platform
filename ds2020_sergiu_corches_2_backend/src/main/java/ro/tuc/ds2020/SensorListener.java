package ro.tuc.ds2020;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.DataInfo;
import java.time.temporal.ChronoUnit;

@Service
public class SensorListener {


    @Autowired
    private SimpMessagingTemplate template;


    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_NAME)
    public void reciveMessageFromReader(DataInfo dataInfo) throws Exception{
        long hours = ChronoUnit.HOURS.between(dataInfo.getStart(), dataInfo.getEnd());
            if(dataInfo.getActivity().equals("Sleeping") && hours>7){
             template.convertAndSend("/topic/greetings",dataInfo);
                System.out.println("Problema cu "+dataInfo.getActivity()+ " timp petrecut "+ hours);

        }

        if(dataInfo.getActivity().equals("Leaving") && hours>5){
         template.convertAndSend("/topic/greetings",dataInfo);
            System.out.println("Problema cu "+dataInfo.getActivity()+ " timp petrecut "+ hours);
        }

        if( (dataInfo.getActivity().equals("Toileting") || (dataInfo.getActivity().equals("Grooming")) || dataInfo.getActivity().equals("Showering")) && hours>0.5) {
            System.out.println("Problema cu "+dataInfo.getActivity()+ " timp petrecut "+ hours);
          template.convertAndSend("/topic/greetings",dataInfo);
        }
    }

}
