# Use the official Tomcat image with Java 8
FROM tomcat:9.0-jdk8-openjdk

# Remove the old, empty webapps directory
RUN rm -rf /usr/local/tomcat/webapps

# Rename webapps.dist (which has the default files) to webapps
# This is the directory Tomcat actually uses to serve files [citation:8]
RUN mv /usr/local/tomcat/webapps.dist /usr/local/tomcat/webapps

# Now, copy your project's webapp folder to the ROOT directory.
# It will now be in the correct place where Tomcat expects it.
COPY src/main/webapp /usr/local/tomcat/webapps/ROOT

# Tell Render which port to use
EXPOSE 8080

# Start the Tomcat server
CMD ["catalina.sh", "run"]
