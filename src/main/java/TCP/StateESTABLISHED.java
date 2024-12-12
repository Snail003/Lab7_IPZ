package TCP;

/**
 * ESTABLISHED state.
 */

public class StateESTABLISHED implements State{
    private String ipSecond;

    /**
     * Stores IP of user with whom you are connected.
     */

    public StateESTABLISHED (String ip) {
        this.ipSecond = ip;
    }

    /**
     * Returns IP of user with whom you are connected.
     */

    @Override
    public String informationAboutSecondClient() {
        return this.ipSecond;
    }
}
