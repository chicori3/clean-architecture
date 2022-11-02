package java.io.buckpal.application.port.out;

import static java.io.buckpal.domain.Account.AccountId;

public interface AccountLock {

    void lockAccount(AccountId accountId);

    void releaseAccount(AccountId accountId);
}
