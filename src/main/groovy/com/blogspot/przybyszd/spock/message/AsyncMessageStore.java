package com.blogspot.przybyszd.spock.message;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class AsyncMessageStore {

    private final MessageStore delegate;
    private final ExecutorService executor;
    public AsyncMessageStore(MessageStore delegate, ExecutorService executor) {
        this.delegate = delegate;
        this.executor = executor;
    }
    public void latestPostBy(String username, Consumer<String> callback) {
        executor.submit(() -> {
            String result = delegate.latestPostBy(username);
            callback.accept(result);
        });
    }
    void latestPostsBy(List<String> usernames, Consumer<String> callback) {
        usernames.forEach(username -> latestPostBy(username, callback));
    }
}
