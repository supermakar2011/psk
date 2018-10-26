package com.examplemycompanyppsk.psk.conntroller;

import com.examplemycompanyppsk.psk.model.Message;
import com.examplemycompanyppsk.psk.model.User;
import com.examplemycompanyppsk.psk.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/main")
    public String messages(Model model,@RequestParam(name = "filter",required = false)String filter){
        List<Message> messages;
        if (filter != null && !filter.isEmpty()){
            messages = messageRepo.findByTag(filter);
        }else {
            messages = messageRepo.findAll();
        }
        model.addAttribute("messages",messages);
        model.addAttribute("filter",filter);
        return "main";
    }
    @PostMapping("/main")
    public String addMessage(Model model, @AuthenticationPrincipal User user, @RequestParam(name = "text")String text, @RequestParam(name = "tag")String tag,
                             @RequestParam(name = "file")MultipartFile file) throws IOException {
        Message message = new Message(text,tag,user);
        if (file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

        String uuid = UUID.randomUUID().toString();
        String fileResult = uuid + "." + file.getOriginalFilename();
        file.transferTo(new File(uploadPath + "/" + fileResult));
        message.setFilename(fileResult);
        }
        messageRepo.save(message);
        List<Message> messages = messageRepo.findAll();
        model.addAttribute("messages",messages);
        return "main";
    }

}
