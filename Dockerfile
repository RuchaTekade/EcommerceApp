FROM tomcat:9.0-jdk8-openjdk

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your entire webapp folder to ROOT
COPY src/main/webapp /usr/local/tomcat/webapps/ROOT/

# Copy compiled Java classes (if they exist)
COPY src/main/java /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

# Ensure proper permissions
RUN chmod -R 755 /usr/local/tomcat/webapps/ROOT

EXPOSE 8080

CMD ["catalina.sh", "run"]
