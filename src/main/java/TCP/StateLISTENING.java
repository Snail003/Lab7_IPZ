package TCP;

/**
 * LISTENING state.
 */

public class StateLISTENING implements State{

    /**
     * Returns null because it's not connected.
     */

    @Override
    public String informationAboutSecondClient() {
        return null;
    }
}
