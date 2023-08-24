package de.openvalue.example;

import de.openvalue.example.Main;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModulithTests {

    @Test
    void verify() {
        ApplicationModules.of(Main.class).verify();
    }

}
