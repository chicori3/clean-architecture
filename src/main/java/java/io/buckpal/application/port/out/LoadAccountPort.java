package java.io.buckpal.application.port.out;

import java.io.buckpal.domain.Account;
import java.io.buckpal.domain.Account.AccountId;
import java.time.LocalDateTime;

public interface LoadAccountPort {

    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
