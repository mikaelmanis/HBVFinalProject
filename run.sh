JAR_FILE="target/FinalProject-1.0-SNAPSHOT-jar-with-dependencies.jar"

if [ -f "$JAR_FILE" ]; then
    echo "Running the program..."
    java -jar "$JAR_FILE"
else
    echo "Error: Fat JAR not found. Please build the project first using ./build.sh."
fi