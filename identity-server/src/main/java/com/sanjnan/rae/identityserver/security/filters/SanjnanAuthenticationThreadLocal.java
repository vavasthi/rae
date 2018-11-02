package com.sanjnan.rae.identityserver.security.filters;

import com.sanjnan.rae.identityserver.pojos.Account;
import com.sanjnan.rae.identityserver.pojos.Session;
import com.sanjnan.rae.identityserver.pojos.Tenant;

public class SanjnanAuthenticationThreadLocal {

  public static final SanjnanAuthenticationThreadLocal INSTANCE = new SanjnanAuthenticationThreadLocal();
  private SanjnanAuthenticationThreadLocal() {

  }

  public void initializeThreadLocals(Tenant tenant, Account account, Session session) {
    tenantThreadLocal.set(tenant);
    accountThreadLocal.set(account);
    sessionThreadLocal.set(session);
  }
  public void clear() {
    tenantThreadLocal.remove();
    accountThreadLocal.remove();
    sessionThreadLocal.remove();
  }
  public ThreadLocal<Tenant> getTenantThreadLocal() {
    return tenantThreadLocal;
  }

  public void setTenantThreadLocal(ThreadLocal<Tenant> tenantThreadLocal) {
    this.tenantThreadLocal = tenantThreadLocal;
  }

  public ThreadLocal<Account> getAccountThreadLocal() {
    return accountThreadLocal;
  }

  public void setAccountThreadLocal(ThreadLocal<Account> accountThreadLocal) {
    this.accountThreadLocal = accountThreadLocal;
  }

  public ThreadLocal<Session> getSessionThreadLocal() {
    return sessionThreadLocal;
  }

  public void setSessionThreadLocal(ThreadLocal<Session> sessionThreadLocal) {
    this.sessionThreadLocal = sessionThreadLocal;
  }

  private ThreadLocal<Tenant> tenantThreadLocal = new ThreadLocal<>();
  private ThreadLocal<Account> accountThreadLocal = new ThreadLocal<>();
  private ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<>();

}
