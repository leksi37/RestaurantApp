package basicClasses;

import java.io.Serializable;

public enum ItemState implements Serializable {
    delivered,
    done,
    inProgress,
    toWaiter,
    notStarted
}
