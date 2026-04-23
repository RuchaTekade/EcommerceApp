FROM tomcat:9.0-jdk8-openjdk

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your compiled classes to WEB-INF/classes
COPY src/main/webapp/WEB-INF/classes /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

# Copy your HTML/CSS/JS files
COPY src/main/webapp /usr/local/tomcat/webapps/ROOT/

# Copy MySQL connector
COPY src/main/webapp/WEB-INF/lib/*.jar /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/

EXPOSE 8080

CMD ["catalina.sh", "run"]
