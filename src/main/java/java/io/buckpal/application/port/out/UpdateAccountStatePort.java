package java.io.buckpal.application.port.out;

import java.io.buckpal.domain.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);
}
