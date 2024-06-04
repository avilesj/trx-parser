# Use the official Clojure image as the base image
FROM clojure:lein-2.11.2

# Set the working directory in the container
WORKDIR /app

# Copy the project file and dependencies first
COPY project.clj /app/

# Download and install all dependencies
RUN lein deps

# Copy the rest of the application code
COPY . /app

# Compile the application
RUN lein uberjar

# Specify the entrypoint, allowing you to pass CLI parameters
ENTRYPOINT ["java", "-jar", "target/uberjar/trx-classifier-0.1.0-SNAPSHOT-standalone.jar"]
# ENTRYPOINT ["java", "-cp", "target/uberjar/trx-classifier.jar", "clojure.main", "trx-classifier.core"]

# Example CMD to show how parameters can be passed
# Replace "/path/to/directory" and "file1.csv file2.csv" with your actual parameters
CMD ["banks/", "trx/"]

