logentries-jboss7
=================

Simple adapter to use LogEntries' AsyncLogger with JBoss7.1.x

To Use
------

1. On logentries website, create a new log and have the token available.
1. Build the jar:

        mvn clean install dependency:copy

1. Create an a new JBoss module directory: i.e. Jboss-as-7.1.0.Final/modules/com/company/logentries/main
1. Copy the target/logentries-jboss7-*.jar and target/dependency/logentries-appender-*.jar to your new jboss module directory</li>
1. Create a module.xml file:

        <?xml version="1.0" encoding="UTF-8"?>
        <module xmlns="urn:jboss:module:1.0" name="com.company.logentries">
          <resources>
            <resource-root path="logentries-jboss7-XXX.jar"/>
            <resource-root path="logentries-appender-XXX.jar"/>
          </resources>
          <dependencies>
            <module name="org.jboss.logging"/>
            <module name="javax.api"/>
          </dependencies>
        </module>

1. Modify your JBoss configuration file (ex: standalone.xml) and add the following sections to your logging subsystem:

        <custom-handler name="LogEntriesAppender" class="us.bigd.logentries.LogEntriesAdapterHandler" module="com.company.logentries">
          <level name="DEBUG"/>
          <formatter>
            <pattern-formatter pattern="%d{HH:mm:ss,SSS} %-5p [%c] (%t) %s%E%n"/>
          </formatter>
          <properties>
            <property name="token" value="YOUR_TOKEN"/>
          </properties>
        </custom-handler>
        ...
        <root-logger>
          <level name="INFO"/>
          <handlers>
            <handler name="CONSOLE"/>
            <handler name="FILE"/>
            <handler name="LogEntriesAppender"/>
          </handlers>
        </root-logger>
    
1. Fire up JBoss and monitor logs to confirm the module loads without errors.
1. Monitor logentries log for incoming data.
    
It'd be nice if logentries supported the Handler interface in future versions!
    