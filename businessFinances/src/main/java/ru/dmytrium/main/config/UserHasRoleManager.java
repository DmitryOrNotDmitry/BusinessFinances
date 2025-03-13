package ru.dmytrium.main.config;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import ru.dmytrium.main.entity.Business;
import ru.dmytrium.main.entity.InvolveBusiness;
import ru.dmytrium.main.entity.User;
import ru.dmytrium.main.repo.InvolveBusinessRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public class UserHasRoleManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final InvolveBusinessRepository involveRepository;
    private final String businessIdName;
    private final String[] roleNames;

    public UserHasRoleManager(InvolveBusinessRepository involveRepository, String businessIdName, String... roleNames) {
        this.involveRepository = involveRepository;
        this.businessIdName = businessIdName;
        this.roleNames = roleNames;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        User user = (User) authentication.get().getPrincipal();

        long businessId = extractBusinessId(context);
        if (businessId == -1) {
            return new AuthorizationDecision(false);
        }

        return getAuthorizationDecision(user, businessId);
    }

    private long extractBusinessId(RequestAuthorizationContext context) {
        return Long.parseLong(context.getVariables().getOrDefault(businessIdName, "-1"));
    }

    private AuthorizationDecision getAuthorizationDecision(User user, long businessId) {
        Optional<InvolveBusiness> involve = involveRepository.findByBusinessAndUser(new Business(businessId), user);

        boolean hasRequiredRole = involve.isPresent() && hasMatchingRole(involve.get());
        return new AuthorizationDecision(hasRequiredRole);
    }

    private boolean hasMatchingRole(InvolveBusiness involveBusiness) {
        return Arrays.stream(roleNames)
                .anyMatch(role -> involveBusiness.getRole().getRoleName().equals(role));
    }
}
