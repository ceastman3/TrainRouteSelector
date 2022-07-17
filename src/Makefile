
# Defaults program to compile and run TrainSelector.java
default: run

#
run: TrainSelector.class
		java TrainSelector

TrainSelector.class: TrainSelector.java
		javac TrainSelector.java


# Compiles and runs all JUnit tests in routeTester.java
routeTester.class: TrainSelector.java routeTester.java
		javac TrainSelector.java routeTester.java

test: routeTester.class
		java routeTester


# Removes all *.class files
clean:
		rm *.class