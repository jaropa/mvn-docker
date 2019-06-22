package com.caplin.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;


public class App
{

    public static void main( String[] args )
    {

        System.out.println("VALUE IS: ");
        System.out.println(System.getenv("PAWEL_TEST"));
        int port = Integer.parseInt(System.getenv("PORT"));
        System.out.println("New version built");

        PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        List<String> list = new ArrayList<>();

        Gauge gauge = Gauge
                .builder("valuesssss.size", App::getVal)
                .register(prometheusRegistry);

        list.add("1");

        Counter compositeCounter = prometheusRegistry.counter("counter");
        compositeCounter.increment();

        port(port);

        get("/metrics", (req, res)-> {

                    compositeCounter.increment(1);

                    return  prometheusRegistry.scrape();
                }
        );
    }

    private static   Number getVal(){
        Random r = new Random();
        return r.nextInt(100) - 50;
    }
}
