package com.depo.userservice.util;

import com.depo.userservice.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class EncryptionAspect {

    private final PasswordEncoder passwordEncoder;

    public EncryptionAspect(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Pointcut("execution(* com.depo.userservice.service.impl.UserServiceImpl.save(..)) || " +
            "execution(* com.depo.userservice.service.impl.UserServiceImpl.update(..))")
    public void saveAndUpdateMethods() {

    }

    @Before("saveAndUpdateMethods()")
    public void beforeSaveOrUpdate(JoinPoint joinPoint) throws Exception {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof User user) {
                if (user.getPassword()!=null){
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                }
                user.setPhoneNumber(EncryptionUtils.encrypt(user.getPhoneNumber()));
            }
        }
    }

    @AfterReturning(pointcut = "saveAndUpdateMethods()", returning = "returnValue")
    public void afterSaveOrUpdate(Object returnValue) throws Exception {
        if (returnValue instanceof User user) {
            user.setPhoneNumber(EncryptionUtils.decrypt(user.getPhoneNumber()));
        }
    }

    @Pointcut("execution(* com.depo.userservice.service.impl.UserServiceImpl.getAll(..)) || " +
            "execution(* com.depo.userservice.service.impl.UserServiceImpl.getById(..)) || " +
            "execution(* com.depo.userservice.service.impl.UserServiceImpl.getAllByIds(..)) ||" +
            "execution(* com.depo.userservice.service.impl.UserServiceImpl.getByUsername(..)) ")
    public void getByIdAndAllMethods() {

    }

    @AfterReturning(pointcut = "getByIdAndAllMethods()", returning = "returnValue")
    public void afterGetByIdAndAll(Object returnValue) throws Exception {
        if (returnValue instanceof User) {
            User user = (User) returnValue;
            user.setPhoneNumber(EncryptionUtils.decrypt(user.getPhoneNumber()));
        } else if (returnValue instanceof List<?>) {
            List<User> users = (List<User>) returnValue;
            for (User user : users) {
                user.setPhoneNumber(EncryptionUtils.decrypt(user.getPhoneNumber()));
            }
        }
    }
}
