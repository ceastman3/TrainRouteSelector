# --== CS400 File Header Information ==--
# Name: Connor Eastman
# Email: ceastman
# Notes to Grader: <optional extra notes>


# Defaults program to compile and run TrainSelector.java
default: run

# Runs User interface
run: UserInterface.java
		java UserInterface

TrainSelector.class: TrainSelector.java UserInterface.java RouteState.java City.java
		javac TrainSelector.java UserInterface.java RouteState.java City.java

# Compiles and runs all JUnit tests in routeTester.java
routeTester.class: TrainSelector.java routeTester.java
		javac TrainSelector.java routeTester.java

test: routeTester.class
		java routeTester

# Removes all *.class files
clean:
		rm *.class