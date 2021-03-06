package ru.atom.mm.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.atom.mm.model.Connection;
import ru.atom.mm.service.ConnectionQueue;


@Controller
@RequestMapping("/connection")
public class ConnectionController {
    private static final Logger log = LoggerFactory.getLogger(ConnectionController.class);


    @Autowired
    private ConnectionQueue connectionQueue;

    /**
     * curl test
     *
     * curl -i -X POST -H "Content-Type: application/x-www-form-urlencoded" \
     * localhost:8080/connection/connect -d 'id=1&name=bomberman'
     */
    @RequestMapping(
            path = "connect",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void connect(@RequestParam("id") long id,
                        @RequestParam("name") String name) {

        log.info("New connection id={} name={}", id, name);
        connectionQueue.getQueue().offer(new Connection(id, name));
    }

    /**
     * curl test
     *
     * curl -i localhost:8080/connection/list'
     */
    public String list() {
        throw new UnsupportedOperationException();
    }


}
