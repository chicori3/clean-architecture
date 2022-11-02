package java.io.buckpal.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.io.buckpal.application.port.out.LoadAccountPort;
import java.io.buckpal.application.port.out.UpdateAccountStatePort;
import java.io.buckpal.domain.Account;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {

    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate) {
        AccountJpaEntity account = accountRepository.findById(accountId.getValue())
                .orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activities = activityRepository.findByOwnerSince(accountId.getValue(), baselineDate);

        Long withdrawalBalance = orZero(activityRepository.getDepositBalanceUtil(accountId.getValue(), baselineDate));

        Long depositBalance = orZero(activityRepository.getDepositBalanceUtil(accountId.getValue(), baselineDate));

        return accountMapper.mapToDomainEntity(account, activities, withdrawalBalance, depositBalance);
    }

    private Long orZero(Long value) {
        return value == null ? 0L : value;
    }

    @Override
    public void updateActivities(Account account) {
        account.getActivityWindow()
                .getActivities()
                .stream()
                .filter(activity -> activity.getId() != null)
                .forEach(activity -> activityRepository.save(accountMapper.mapToJpaEntity(activity)));
    }
}
