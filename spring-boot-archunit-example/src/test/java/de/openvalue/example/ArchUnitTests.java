package de.openvalue.example;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "de.openvalue.example", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchUnitTests {

    @ArchTest
    void recipesDomainIsIsolated(JavaClasses classes) {
        var rule = classes()
                .that()
                .resideInAPackage("..recipes..")
                .should()
                .onlyBeAccessed()
                .byAnyPackage("..recipes..");
        rule.check(classes);
    }


    @ArchTest
    void ingredientsDomainInternalsAreIsolated(JavaClasses classes) {
        var rule = classes()
                .that()
                .resideInAPackage("..ingredients.(**)")
                .should()
                .onlyBeAccessed()
                .byAnyPackage("..ingredients..");
        rule.check(classes);
    }

    @ArchTest
    void domainsShouldBeFreeOfCycles(JavaClasses classes) {
        var rule = slices()
                .matching("de.openvalue.example.(*)..")
                .should()
                .beFreeOfCycles();
        rule.check(classes);
    }

    @ArchTest
    void layeredArchitectureCustomRules(JavaClasses classes) {
        var repositoryRule = classes()
                .that()
                .areAnnotatedWith(Repository.class)
                .should().onlyHaveDependentClassesThat().areAnnotatedWith(Service.class);
        var servicesRule = classes()
                .that()
                .areAnnotatedWith(Service.class)
                .should().onlyHaveDependentClassesThat().areAnnotatedWith(RestController.class);

        repositoryRule.check(classes);
        servicesRule.check(classes);
    }

    @ArchTest
    void layeredArchitectureRule(JavaClasses classes) {

        var controllerPredicate = new DescribedPredicate<JavaClass>("Controller") {
            @Override
            public boolean test(JavaClass javaClass) {
                return javaClass.isAnnotatedWith(RestController.class);
            }
        };

        var servicePredicate = new DescribedPredicate<JavaClass>("Service") {
            @Override
            public boolean test(JavaClass javaClass) {
                return javaClass.isAnnotatedWith(Service.class);
            }
        };

        var repositoryPredicate = new DescribedPredicate<JavaClass>("Repository") {
            @Override
            public boolean test(JavaClass javaClass) {
                return javaClass.isAnnotatedWith(Repository.class);
            }
        };

        var rule = layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy(controllerPredicate)
                .layer("Service").definedBy(servicePredicate)
                .layer("Repository").definedBy(repositoryPredicate)

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");
        rule.check(classes);
    }

}
