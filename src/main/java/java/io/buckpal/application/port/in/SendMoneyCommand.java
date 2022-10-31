package java.io.buckpal.application.port.in;

import lombok.Getter;

import java.io.buckpal.common.SelfValidating;
import java.io.buckpal.domain.Account;
import java.io.buckpal.domain.Money;

import static java.util.Objects.requireNonNull;

@Getter
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

    private final Account.AccountId sourceAccountId;
    private final Account.AccountId targetAccountId;
    private final Money money;

    public SendMoneyCommand(Account.AccountId sourceAccountId, Account.AccountId targetAccountId, Money money) {
        requireNonNull(sourceAccountId);
        requireNonNull(targetAccountId);
        this.validateSelf();

        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
    }
}
