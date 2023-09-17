package com.depo.deposervice.util;

import com.depo.deposervice.collection.RequirementAccount;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class EncryptionAspect {

    @Pointcut("execution(* com.depo.deposervice.service.impl.RequirementAccountServiceImpl.save(..)) || " +
            "execution(* com.depo.deposervice.service.impl.RequirementAccountServiceImpl.update(..))")
    public void saveAndUpdateMethods() {

    }

    @Before("saveAndUpdateMethods()")
    public void beforeSaveOrUpdate(JoinPoint joinPoint) throws Exception {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof RequirementAccount requirementAccount) {
                if (requirementAccount.getGenericAccountInfo()!=null){
                    requirementAccount.setGenericAccountInfo(EncryptionUtils.encrypt(requirementAccount.getGenericAccountInfo()));
                }
            }
        }
    }

    @AfterReturning(pointcut = "saveAndUpdateMethods()", returning = "returnValue")
    public void afterSaveOrUpdate(Object returnValue) throws Exception {
        if (returnValue instanceof RequirementAccount requirementAccount) {
            requirementAccount.setGenericAccountInfo(EncryptionUtils.decrypt(requirementAccount.getGenericAccountInfo()));
        }
    }

    @Pointcut("execution(* com.depo.deposervice.service.impl.RequirementAccountServiceImpl.getAll(..)) || " +
            "execution(* com.depo.deposervice.service.impl.RequirementAccountServiceImpl.getById(..)) ")

    public void getByIdAndAllMethods() {

    }

    @AfterReturning(pointcut = "getByIdAndAllMethods()", returning = "returnValue")
    public void afterGetByIdAndAll(Object returnValue) throws Exception {
        if (returnValue instanceof RequirementAccount) {
            RequirementAccount requirementAccount = (RequirementAccount) returnValue;
            requirementAccount.setGenericAccountInfo(EncryptionUtils.decrypt(requirementAccount.getGenericAccountInfo()));
        } else if (returnValue instanceof List<?>) {
            List<RequirementAccount> requirementAccounts = (List<RequirementAccount>) returnValue;
            for (RequirementAccount requirementAccount : requirementAccounts) {
                requirementAccount.setGenericAccountInfo(EncryptionUtils.decrypt(requirementAccount.getGenericAccountInfo()));
            }
        }
    }
}
