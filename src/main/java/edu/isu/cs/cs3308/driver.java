package edu.isu.cs.cs3308;




public class driver {
    public static void main(String[] args) {
        Simulation fixture;


        fixture = new Simulation(18, 10, 1, 1024);
        fixture.runSimulation();
    }
}
