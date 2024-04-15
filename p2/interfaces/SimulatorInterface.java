package p2.interfaces;

/**
 * SimulatorInterface is an interface for managing and controlling a simulation process.
 */
public interface SimulatorInterface {

    /**
     * isFinished is a method that checks if the simulation process is completed.
     * @return true if the simulation is finished, false otherwise.
     * @param: NONE
     */
    public boolean isFinished();

    /**
     * simlulate is a method that executes the simulation process.
     * @return: NONE
     * @param: NONE
     */
    public void simulate();

    /**
     * validate is a method that checks the current state or setup of the simulation to ensure it can run correctly or has run correctly.
     * @return true if the validation passes, false otherwise.
     * @param: NONE
     */
    public boolean validate();
}
