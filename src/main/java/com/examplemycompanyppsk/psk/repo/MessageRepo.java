package com.examplemycompanyppsk.psk.repo;

import com.examplemycompanyppsk.psk.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message,Long> {
  List<Message> findByTag(String tag);
}
