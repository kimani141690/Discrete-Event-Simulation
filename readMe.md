
# Banking System Simulation

This project aims to simulate a banking system using Java, focusing on reading input data from two columns (Inter-Arrival Time - IAT and Service Times), performing the simulation, and calculating queue statistics. The output is presented in tabular format, similar to the provided banking system simulation table.

## Files Used

- **`Colors.java`**: Contains color constants used for highlighting the table.
- **`SimulationData.java`**: Defines the `SimulationData` class responsible for simulating data generation and calculating queue statistics.
- **`SimulationTable.java`**: Implements the `SimulationTable` class for creating a graphical representation of the simulation results in a table format.
- **`Simulation.java`**: Main class to run the simulation and display the results in a JFrame.

## Dependencies

This project relies on Java and Swing for GUI development. Ensure you have the following dependencies installed:

- **Java Development Kit (JDK)**: Required to compile and run Java programs. Download and install the latest version of JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html).
- **Java Swing**: Swing is a GUI toolkit for Java. It is included in the Java SE Development Kit (JDK).

## Steps to Use

1. **Clone the Repository**: Clone this repository to your local machine using Git:
   ```
   git clone https://github.com/your-username/banking-system-simulation.git
   ```

2. **Compile the Java Files**: Navigate to the project directory and compile the Java files using the Java compiler (`javac`):
   ```
   cd banking-system-simulation
   javac *.java
   ```

3. **Run the Simulation**: Run the main class `Simulation.java` to execute the simulation and display the results in a graphical user interface (GUI):
   ```
   java Simulation
   ```

4. **Interpret the Results**: The simulation results will be displayed in a JFrame, showing the simulated data table along with queue statistics.

## Achievements

- **Data Input**: Implemented a mechanism to read input data from two columns (Inter-Arrival Time - IAT and Service Times).
- **Simulation Logic**: Designed simulation logic to simulate customer arrivals, service times, and track relevant parameters.
- **Queue Statistics Calculation**: Calculated queue statistics such as average waiting time, probability of waiting, server utilization, etc., based on the simulated data.
- **Output Presentation**: Presented the simulation results in tabular format using Java Swing components.
- **GUI Integration**: Integrated the simulation table into a graphical user interface (GUI) for easy visualization.
- **Group Collaboration**: Collaborated effectively in groups to divide tasks, implement code modules, and ensure smooth integration of individual components.

---

This project successfully achieved the goal of simulating a banking system, calculating queue statistics, and presenting the results in a tabular format using Java. It demonstrates effective collaboration, modular design, and utilization of Java Swing for GUI development.