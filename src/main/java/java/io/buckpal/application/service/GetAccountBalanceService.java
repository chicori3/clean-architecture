package java.io.buckpal.application.service;

import lombok.RequiredArgsConstructor;

import java.io.buckpal.application.port.in.GetAccountBalanceQuery;
import java.io.buckpal.application.port.out.LoadAccountPort;
import java.io.buckpal.domain.Account.AccountId;
import java.io.buckpal.domain.Money;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final LoadAccountPort loadAccountPort;

    @Override
    public Money getAccountBalance(AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
                .calculateBalance();
    }
}
