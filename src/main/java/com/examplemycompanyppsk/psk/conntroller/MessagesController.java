package com.examplemycompanyppsk.psk.conntroller;

import com.examplemycompanyppsk.psk.exception.NotFoundEsception;
import com.examplemycompanyppsk.psk.model.Message;
import com.examplemycompanyppsk.psk.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping
    public List<Message> messages(){
        List<Message> messages = messageRepo.findAll();
        return messages;
    }
    @GetMapping("{id}")
    public Message getOneMessages(@PathVariable String id){
        List<Message> messages = messageRepo.findAll();

                Message  map = messages.stream().filter(message -> message.getId().toString().equals(id))
               .findFirst().orElseThrow(NotFoundEsception::new);
       return map;
    }
}
