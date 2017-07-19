package com.blogspot.przybyszd.spock.message;

import java.util.HashMap;
import java.util.Map;

public class MessageStore {
    private Map<String, String> map = new HashMap<>();

    public MessageStore() {
        map.put("chip", "Nioh nioh nioh");
        map.put("goofy", "Heheh");
        map.put("pluto", "Woof");
    }

    String latestPostBy(String username) {
        return map.getOrDefault(username, "Hi!");
    }

}
