package basicClasses;

import java.io.Serializable;

public enum ItemState implements Serializable {
    delivered,
    inProgress,
    done,
    toWaiter,
    notStarted;
}
