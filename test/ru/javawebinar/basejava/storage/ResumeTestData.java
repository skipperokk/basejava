package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.Month;
import java.util.Map;

import static ru.javawebinar.basejava.util.DateUtil.of;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры"));

        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("\n С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "\n Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "\n Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "\n Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "\n Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)",
                "\n Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."
        ));

        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "\n Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "\n DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle",
                "\n MySQL, SQLite, MS SQL, HSQLDB",
                "\n Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
                "\n XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "\n Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)",
                "\n Python: Django",
                "\n JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "\n Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "\n Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT",
                "\n Инструменты: Maven + plugin development, Gradle, настройка Ngnix",
                "\n Администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer",
                "\n Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "\n Родной русский, английский \"upper intermediate\""
        ));

        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Java Online Projects", "http://javaops.ru/",
                        new Organization.Position(of(2013, Month.OCTOBER), DateUtil.NOW, "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок")),
                new Organization("Wrike", "https://www.wrike.com/",
                        new Organization.Position(of(2014, Month.OCTOBER), of(2016, Month.JANUARY), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")),
                new Organization("RIT Center", "",
                        new Organization.Position(of(2012, Month.APRIL), of(2014, Month.OCTOBER), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")),
                new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                        new Organization.Position(of(2010, Month.DECEMBER), of(2012, Month.APRIL), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.")),
                new Organization("Yota", "https://www.yota.ru/",
                        new Organization.Position(of(2008, Month.JUNE), of(2010, Month.DECEMBER), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)")),
                new Organization("Enkata", "http://enkata.com/",
                        new Organization.Position(of(2007, Month.MARCH), of(2008, Month.JUNE), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)")),
                new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                        new Organization.Position(of(2005, Month.JANUARY), of(2007, Month.FEBRUARY), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).")),
                new Organization("Alcatel", "http://www.alcatel.ru/",
                        new Organization.Position(of(1997, Month.SEPTEMBER), of(2005, Month.JANUARY), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))
        ));

        resume.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("Coursera", "https://www.coursera.org/course/progfun",
                        new Organization.Position(of(2013, Month.MARCH), of(2013, Month.MAY), "Functional Programming Principles in Scala by Martin Odersky", "")),
                new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                        new Organization.Position(of(2011, Month.MARCH), of(2011, Month.APRIL), "Курс Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.", "")),
                new Organization("Siemens AG", "http://www.siemens.ru/",
                        new Organization.Position(of(2005, Month.JANUARY), of(2005, Month.APRIL), "3 месяца обучения мобильным IN сетям (Берлин)", "")),
                new Organization("Alcatel", "http://www.alcatel.ru/",
                        new Organization.Position(of(1997, Month.SEPTEMBER), of(1998, Month.MARCH), "6 месяцев обучения цифровым телефонным сетям (Москва)", "")),
                new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
                        new Organization.Position(of(1993, Month.SEPTEMBER), of(1996, Month.JULY), "Аспирантура (программист С, С++)", ""),
                        new Organization.Position(of(1987, Month.SEPTEMBER), of(1993, Month.JULY), "Инженер (программист Fortran, C)", "")),
                new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
                        new Organization.Position(of(1984, Month.SEPTEMBER), of(1987, Month.JUNE), "Закончил с отличием", ""))
        ));

        resume.addContact(ContactType.PHONE, "+7-(495)-999-99-99");
        resume.addContact(ContactType.MOBILE, "+79969745519");
        resume.addContact(ContactType.HOME_PHONE, "+7-(495)-999-99-11");
        resume.addContact(ContactType.SKYPE, new Link("grigory.kislin", "skype:grigory.kislin").getName());
        resume.addContact(ContactType.MAIL, new Link("gkislin@yandex.ru", "mailto:gkislin@yandex.ru").getName());
        resume.addContact(ContactType.LINKEDIN, new Link("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin").getName());
        resume.addContact(ContactType.GITHUB, new Link("Профиль GitHub", "https://github.com/gkislin").getName());
        resume.addContact(ContactType.STACKOVERFLOW, new Link("Профиль StackOverflow", "https://stackoverflow.com/users/548473").getName());
        resume.addContact(ContactType.HOME_PAGE, new Link("Домашняя страница", "http://gkislin.ru/").getName());

        System.out.println(resume.toString());
        System.out.println("=================");
        for (Map.Entry<ContactType, String> entry: resume.getContacts().entrySet()){
            System.out.println(entry);
        }
        System.out.println("=================");
        for (Map.Entry<SectionType, AbstractSection> entry: resume.getSections().entrySet()){
            System.out.println(entry);
        }
        return resume;
    }

}

