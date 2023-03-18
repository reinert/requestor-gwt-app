package me.client;

import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.*;
import io.reinert.requestor.core.*;
import io.reinert.requestor.core.auth.*;
import io.reinert.requestor.gwtjackson.*;
import org.jboss.elemento.*;

public class App implements EntryPoint {

    final Session session;
    HTMLDivElement ipDiv;
    HTMLDivElement jsonDiv;

    public App() {
        // The session object should be a singleton
        // In complex scenarios you might want to have separate sessions to communicate with different backends (one singleton for each backend)
        session = RequestorGwtJackson.newSession();

        // Initiating this way won't automatically register the gwtjackson serialization modules
//        session = Requestor.newSession();

        // Make the necessary configurations once
//        session.setMediaType("application/json");
//        session.setTimeout(30_000);
//        session.setAuth(new BasicAuth("user", "password"));
//        session.setRetry(DelaySequence.geometric(2, 2, 3), StatusFamily.SERVER_ERROR, Status.TOO_MANY_REQUESTS);
    }

    @Override
    public void onModuleLoad() {
        final HTMLButtonElement showIpButton = Elements.button("Show IP")
                .on(EventType.click, e -> getIp())
                .element();

        ipDiv = Elements.div().element();

        final HTMLButtonElement showJsonButton = Elements.button("Show JSON")
                .on(EventType.click, e -> getJson())
                .element();

        jsonDiv = Elements.div().element();

        Elements.body()
                .add(showIpButton)
                .add(ipDiv)
                .add(showJsonButton)
                .add(jsonDiv);

    }

    public void getIp() {
        session.get("https://httpbin.org/ip", String.class)
                .onSuccess(ip -> ipDiv.textContent = ip);
    }

    public void getJson() {
        session.req("https://jsonplaceholder.typicode.com/todos/1")
                .get(Todo.class)
                .onSuccess(todo -> jsonDiv.textContent = todo.toString());
    }
}
