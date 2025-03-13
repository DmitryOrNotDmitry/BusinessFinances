package ru.dmytrium.main.config;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import ru.dmytrium.main.entity.User;
import ru.dmytrium.main.services.BusinessService;

import java.util.function.Supplier;

public class BusinessAuthManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final BusinessService businessService;
    private final String businessIdName;

    public BusinessAuthManager(BusinessService businessService, String businessIdName) {
        this.businessService = businessService;
        this.businessIdName = businessIdName;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        User user = (User) authentication.get().getPrincipal();
        boolean hasAccess = businessService.isUserHasAnyRoleInBusiness(user,
                Long.valueOf(object.getVariables().getOrDefault(businessIdName,"-1")));
        return new AuthorizationDecision(hasAccess);
    }
}
