
# Defaults program to compile and run TrainSelector.java
default: run

# Runs User interface
run: UserInterface.java
		java UserInterface

TrainSelector.class: TrainSelector.java UserInterface.java RouteState.java
		javac TrainSelector.java UserInterface.java RouteState.java


# Compiles and runs all JUnit tests in routeTester.java
routeTester.class: TrainSelector.java routeTester.java
		javac TrainSelector.java routeTester.java

test: routeTester.class
		java routeTester


# Removes all *.class files
clean:
		rm *.class