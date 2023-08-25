package de.openvalue.example;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ModulithTests {

    @Test
    void verify() {
        var modules = ApplicationModules.of(Main.class);
        modules.verify();
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml(Documenter.DiagramOptions.defaults().withStyle(Documenter.DiagramOptions.DiagramStyle.UML))
                .writeDocumentation();
    }

}
