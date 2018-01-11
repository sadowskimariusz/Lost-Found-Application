package com.lostandfoundapp.service;

import com.lostandfoundapp.parsers.common.Parser;
import com.lostandfoundapp.parsers.cracow.ParserCracow;
import com.lostandfoundapp.parsers.gdansk.ParserGdansk;
import com.lostandfoundapp.parsers.warsaw.ParserWarsaw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;


public class main {

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI;
    public static final String protocol;
    public static final Optional<String> host;
    public static final String path;
    public static final Optional<String> port;

    static{
        protocol = "http://";
        host = Optional.ofNullable(System.getenv("HOSTNAME"));
        port = Optional.ofNullable(System.getenv("PORT"));
        path = "myapp";
        BASE_URI = protocol + host.orElse("localhost") + ":" + port.orElse("8080") + "/" + path + "/";

    }

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example.rest package
        final ResourceConfig rc = new ResourceConfig().packages("com.lostandfoundapp.service");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) {

        try {

            Service service;

            ParserWarsaw warsaw = new ParserWarsaw();
            ParserGdansk gdansk = new ParserGdansk();
            ParserCracow cracow = new ParserCracow();

            List<Parser> parsers = new ArrayList<>();

            parsers.add(cracow);
            parsers.add(gdansk);
            parsers.add(warsaw);

            service = new Service(parsers);

            //service.downloadData();

            //Fake method for the time of playing with grizzly
            service.createFakeItemList();

            final HttpServer server = startServer();
            System.out.println(String.format("Jersey app started with WADL available at "
                    + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
            System.in.read();
            server.stop();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

