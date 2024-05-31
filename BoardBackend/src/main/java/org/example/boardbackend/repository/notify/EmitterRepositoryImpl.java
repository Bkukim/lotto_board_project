package org.example.boardbackend.repository.notify;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
@Repository
public class EmitterRepositoryImpl implements EmitterRepository{
   private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
   private final List<Map<String,SseEmitter>> eventCache = new CopyOnWriteArrayList<>();

   @Override
   public SseEmitter save(String userId, SseEmitter sseEmitter) {
      emitters.put(userId, sseEmitter);
      return sseEmitter;
   }

   @Override
   public SseEmitter findByUserId(String userId) {
      SseEmitter sseEmitter = emitters.get(userId);
      return sseEmitter;
   }



   @Override
   public void saveEventCache(String userId, SseEmitter sseEmitter) {
      Map<String,SseEmitter> emitterMap = new ConcurrentHashMap<>();
      emitterMap.put(userId,sseEmitter);
      eventCache.add(emitterMap);
   }

   @Override
   public Map<String, SseEmitter> findAllEmitterStartWithByUserId(String userId) {
      return emitters.entrySet().stream()
              .filter(entry -> entry.getKey().equals(userId))
              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
   }

   @Override
   public  List<Map<String,SseEmitter>> findAllEventCacheStartWithByUserId(String userId) {
      List list = new ArrayList<>();
      for (int i = 0; i < eventCache.size(); i++) {
         if (eventCache.get(i).containsKey(userId)) {
            list.add(eventCache.get(i));
         }
      }
      return list;
   }

   @Override
   public void deleteById(String userId) {
      emitters.remove(userId);
   }

   @Override
   public void deleteAllEmitterStartWithId(String userId) {
      emitters.forEach(
              (key, emitter) -> {
                 if (key.equals(userId)) {
                    emitters.remove(key);
                 }
              }
      );
   }

   @Override
   public void deleteAllEventCacheStartWithId(String userId) {
      for (int i = 0; i < eventCache.size(); i++) {
         if (eventCache.get(i).containsKey(userId)) {
            Map<String ,SseEmitter> emitterMap = eventCache.get(i);
            eventCache.remove(emitterMap);
         }
      }
   }
}
