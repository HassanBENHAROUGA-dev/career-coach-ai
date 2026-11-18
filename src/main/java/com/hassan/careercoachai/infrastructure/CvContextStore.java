package com.hassan.careercoachai.infrastructure;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class CvContextStore {

    private final ConcurrentMap<UUID, String> contexts =
            new ConcurrentHashMap<>();

    public void save(UUID conversationId, String text) {
        contexts.put(conversationId, text);
    }

    public Optional<String> find(UUID conversationId) {
        return Optional.ofNullable(contexts.get(conversationId));
    }
}