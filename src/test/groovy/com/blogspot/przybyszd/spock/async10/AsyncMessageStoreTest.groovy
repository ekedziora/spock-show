package com.blogspot.przybyszd.spock.async10

import com.blogspot.przybyszd.spock.message.AsyncMessageStore
import com.blogspot.przybyszd.spock.message.MessageStore
import spock.lang.Specification
import spock.util.concurrent.BlockingVariable
import spock.util.concurrent.PollingConditions

import java.util.concurrent.Executors

class AsyncMessageStoreTest extends Specification {

    private AsyncMessageStore asyncMessageStore = new AsyncMessageStore(new MessageStore(), Executors.newSingleThreadExecutor())

    def "false positive test"() {
        expect:
        asyncMessageStore.latestPostBy(username) {
            assert it == messageText
        }
        where:
        username = "chip"
        messageText = "Nioh nioh nioh"
    }

    def "blocking variable test"() {
        when:
        def result = new BlockingVariable<String>()
        asyncMessageStore.latestPostBy(username) { message ->
            result.set(message)
        }

        then:
        result.get() == messageText

        where:
        username = "chip"
        messageText = "Nioh nioh nioh"
    }

    def "more sophisticated way"() {
        when:
        def callback = new BlockingVariable<String>()
        asyncMessageStore.latestPostBy(username, callback.&set)

        then:
        callback.get() == messageText

        where:
        username = "chip"
        messageText = "Nioh nioh nioh"
    }

    def "polling conditions test"() {
        when:
        def messages = []
        asyncMessageStore.latestPostsBy(usernames) { message ->
            messages << message
        }

        then:
        def conditions = new PollingConditions()
        conditions.eventually {
            assert messages.containsAll(expectedMessages)
        }

        where:
        usernames = ["goofy", "pluto", "chip"]
        expectedMessages = ["Nioh nioh nioh", "Heheh", "Woof"]
    }




}
