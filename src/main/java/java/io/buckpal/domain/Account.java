package java.io.buckpal.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    @Getter
    private AccountId id;
    @Getter
    private Money baseLineBalance;
    @Getter
    private ActivityWindow activityWindow;

    public static Account withoutId(Money baseLineBalance, ActivityWindow activityWindow) {
        return new Account(null, baseLineBalance, activityWindow);
    }

    public static Account withId(AccountId accountId, Money baseLineBalance, ActivityWindow activityWindow) {
        return new Account(accountId, baseLineBalance, activityWindow);
    }

    public Money calculateBalance() {
        return Money.add(
                this.baseLineBalance,
                this.activityWindow.calculateBalance(this.id)
        );
    }

    public boolean withdraw(Money money, AccountId targetAccountId) {
        if (!mayWithdraw(money)) {
            return false;
        }

        Activity withdrawal = new Activity(
                this.id,
                this.id,
                targetAccountId,
                LocalDateTime.now(),
                money
        );

        this.activityWindow.addActivity(withdrawal);
        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(this.calculateBalance(), money.negate())
                .isPositive();
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {
        Activity deposit = new Activity(
                this.id,
                sourceAccountId,
                this.id,
                LocalDateTime.now(),
                money
        );

        this.activityWindow.addActivity(deposit);
        return true;
    }

    @Value
    public static class AccountId {
        private Long value;
    }
}
