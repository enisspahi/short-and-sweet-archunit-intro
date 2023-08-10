---
marp: true
title: Short and sweet intro to ArchUnit
description: Short and sweet intro to ArchUnit
theme: uncover
paginate: true
_paginate: false

---

# Short and sweet

## Intro to ArchUnit

---

# Intro, reason & background

* Architecture/ coding convention compliance
* at build time
* will save you time and nerves during code review

<!--
https://www.archunit.org/motivation
-->

---

# Example

```
@AnalyzeClasses(packages = "com.tngtech.archunit.example)
public class InterfaceRulesTest {

    @ArchTest
    static final ArchRule interfaces_should_not_have_names_ending_with_the_word_interface =
            noClasses().that().areInterfaces().should().haveNameMatching(".*Interface");
}
```

---

# ArchUnit tests 

## are JUnit tests

---

# ArchUnit tests

## have presets

see https://github.com/TNG/ArchUnit

<!--

https://github.com/TNG/ArchUnit/blob/main/archunit/src/main/java/com/tngtech/archunit/library/DependencyRules.java

https://github.com/TNG/ArchUnit/blob/main/archunit/src/main/java/com/tngtech/archunit/library/Architectures.java

-->

---

# ArchUnit tests

## support DDD/ layered architecture

```
@Test
void modulesDoNotHaveMutualDependencies() {
    JavaClasses importedClasses = new ClassFileImporter()
            .importPackages("my.company.project.service");

    ArchRule rule = SlicesRuleDefinition.slices()
            .matching("my.company.project.service.modules.(*)..")
            .should().notDependOnEachOther();

    rule.check(importedClasses);
}
```

---

# Demo (NDA, project)

---

# Links

* https://www.archunit.org
* https://github.com/TNG/ArchUnit-Examples/tree/main/example-junit5
* https://www.morling.dev/blog/the-code-review-pyramid/

---

# Q&A