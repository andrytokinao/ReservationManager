package com.kinga.microservice.external.service;

import com.kinga.microservice.external.service.modele.FresTransport;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceTest extends TestCase {
    @Autowired
    RouteServiceSimulation directionService;
    @Test
    public void testSimulationPrix(){
        List<FresTransport> frees = directionService.getFres("test ligne", "test categorie");
        for(FresTransport f : frees) {
            System.out.println(f.toString());
        }
    }

}