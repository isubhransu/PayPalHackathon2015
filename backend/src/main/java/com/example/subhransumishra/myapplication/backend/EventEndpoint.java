package com.example.subhransumishra.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "eventApi",
        version = "v1",
        resource = "event",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.subhransumishra.example.com",
                ownerName = "backend.myapplication.subhransumishra.example.com",
                packagePath = ""
        )
)
public class EventEndpoint {

    private static final Logger logger = Logger.getLogger(EventEndpoint.class.getName());

    /**
     * This method gets the <code>Event</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>Event</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getEvent")
    public Event getEvent(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getEvent method");
        return null;
    }

    /**
     * This inserts a new <code>Event</code> object.
     *
     * @param event The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertEvent")
    public Event insertEvent(Event event) {
        // TODO: Implement this function
        logger.info("Calling insertEvent method");
        return event;
    }
}