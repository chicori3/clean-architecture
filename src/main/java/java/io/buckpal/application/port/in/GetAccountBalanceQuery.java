package java.io.buckpal.application.port.in;

import java.io.buckpal.domain.Account.AccountId;
import java.io.buckpal.domain.Money;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(AccountId accountId);
}
