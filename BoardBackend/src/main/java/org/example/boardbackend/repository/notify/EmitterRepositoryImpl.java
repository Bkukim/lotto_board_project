package org.example.boardbackend.repository.notify;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
@Repository
public class EmitterRepositoryImpl implements EmitterRepository{
   private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
   private final Map<String, SseEmitter> eventCache = new ConcurrentHashMap<>();

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
      eventCache.put(userId, sseEmitter);
   }

   @Override
   public Map<String, SseEmitter> findAllEmitterStartWithByUserId(String userId) {
      return emitters.entrySet().stream()
              .filter(entry -> entry.getKey().equals(userId))
              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
   }

   @Override
   public Map<String, SseEmitter> findAllEventCacheStartWithByUserId(String userId) {
      return eventCache.entrySet().stream()
              .filter(entry -> entry.getKey().equals(userId))
              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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
      eventCache.forEach(
              (key, emitter) -> {
                 if (key.startsWith(userId)) {
                    eventCache.remove(key);
                 }
              }
      );
   }
}
