To run Test Analyzer:
1. Compile to JAR (mvn clean install).
2. From the command line:
java -jar test-analyzer-0.1.jar testDir outputFileName

For example, running from where the JAR is (or is on the PATH variable):

java -jar test-analyzer-0.1.jar "C:\Users\Gil\Documents\GitHub\test-analyzer\src\main\java\testanalyzer\examples" output.json