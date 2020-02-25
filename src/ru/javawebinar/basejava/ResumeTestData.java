package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.YearMonth;
import java.util.Arrays;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume resume = new Resume("uuid1", "Григорий Кислин");

        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры"));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(
                new TextSection("\"С 2013 года: разработка проектов \\\"Разработка Web приложения\\\",\\\"Java Enterprise\\\", \\\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\\\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."),
                new TextSection("\n С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."),
                new TextSection("\n Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk."),
                new TextSection("\n Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера."),
                new TextSection("\n Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга."),
                new TextSection("\n Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)"),
                new TextSection("\n Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.")
        )));

        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList(
                new TextSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"),
                new TextSection("\n Version control: Subversion, Git, Mercury, ClearCase, Perforce"),
                new TextSection("\n DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle"),
                new TextSection("\n MySQL, SQLite, MS SQL, HSQLDB"),
                new TextSection("\n Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy"),
                new TextSection("\n XML/XSD/XSLT, SQL, C/C++, Unix shell scripts"),
                new TextSection("\n Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)"),
                new TextSection("\n Python: Django"),
                new TextSection("\n JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js"),
                new TextSection("\n Scala: SBT, Play2, Specs2, Anorm, Spray, Akka"),
                new TextSection("\n Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT"),
                new TextSection("\n Инструменты: Maven + plugin development, Gradle, настройка Ngnix"),
                new TextSection("\n Администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer"),
                new TextSection("\n Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования"),
                new TextSection("\n Родной русский, английский \"upper intermediate\"")
        )));

        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(Arrays.asList(
                new Organization(YearMonth.of(2013, 10), YearMonth.now(), "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок", "Java Online Projects", "http://javaops.ru/"),
                new Organization(YearMonth.of(2014, 10), YearMonth.of(2016, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.", "Wrike", "https://www.wrike.com/"),
                new Organization(YearMonth.of(2012, 4), YearMonth.of(2014, 10), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python", "RIT Center", ""),
                new Organization(YearMonth.of(2010, 12), YearMonth.of(2012, 4), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.", "Luxoft (Deutsche Bank)", "http://www.luxoft.ru/"),
                new Organization(YearMonth.of(2008, 6), YearMonth.of(2010, 12), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)", "Yota", "https://www.yota.ru/"),
                new Organization(YearMonth.of(2007, 3), YearMonth.of(2008, 6), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).", "Enkata", "http://enkata.com/"),
                new Organization(YearMonth.of(2005, 1), YearMonth.of(2007, 2), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).", "Siemens AG", "https://www.siemens.com/ru/ru/home.html"),
                new Organization(YearMonth.of(1997, 9), YearMonth.of(2005, 1), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).", "Alcatel", "http://www.alcatel.ru/")
        )));

        resume.addSection(SectionType.EDUCATION, new OrganizationSection(Arrays.asList(
                new Organization(YearMonth.of(2013, 3), YearMonth.of(2013, 5), "Functional Programming Principles in Scala by Martin Odersky", "", "Coursera", "https://www.coursera.org/course/progfun"),
                new Organization(YearMonth.of(2011, 3), YearMonth.of(2011, 4), "Курс Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.", "", "Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"),
                new Organization(YearMonth.of(2005, 1), YearMonth.of(2005, 4), "3 месяца обучения мобильным IN сетям (Берлин)", "", "Siemens AG", "http://www.siemens.ru/"),
                new Organization(YearMonth.of(1997, 9), YearMonth.of(1998, 3), "6 месяцев обучения цифровым телефонным сетям (Москва)", "", "Alcatel", "http://www.alcatel.ru/"),
                new Organization(YearMonth.of(1993, 9), YearMonth.of(1996, 7), "Аспирантура (программист С, С++)", "", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/"),
                new Organization(YearMonth.of(1987, 9), YearMonth.of(1993, 7), "Инженер (программист Fortran, C)", "", "", ""),
                new Organization(YearMonth.of(1984, 9), YearMonth.of(1987, 6), "Закончил с отличием", "", "Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/")
        )));

        resume.addContact(ContactsType.TELEPHONE, "+79969745519");
        resume.addContact(ContactsType.SKYPE, new HyperText("grigory.kislin", "skype:grigory.kislin").getText());
        resume.addContact(ContactsType.MAIL, new HyperText("gkislin@yandex.ru", "mailto:gkislin@yandex.ru").getText());
        resume.addContact(ContactsType.LINKEDIN, new HyperText("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin").getText());
        resume.addContact(ContactsType.GITHUB, new HyperText("Профиль GitHub", "https://github.com/gkislin").getText());
        resume.addContact(ContactsType.STACKOVERFLOW, new HyperText("Профиль StackOverflow", "https://stackoverflow.com/users/548473").getText());
        resume.addContact(ContactsType.HOME, new HyperText("Домашняя страница", "http://gkislin.ru/").getText());

        System.out.println(resume.toString());
        System.out.println("=================");
        for (ContactsType contactsType : ContactsType.values()) {
            System.out.println(resume.getContacts(contactsType));
        }
        System.out.println("=================");
        for (SectionType sectionType : SectionType.values()) {
            System.out.println(resume.getSection(sectionType));
        }
    }
}
