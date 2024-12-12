package TCP;

/**
 * CLOSED state.
 */


public class StateCLOSED implements State{

    /**
     * Returns null because it's not connected.
     */

    @Override
    public String informationAboutSecondClient() {
        return null;
    }
}
