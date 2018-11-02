package com.sanjnan.rae.identityserver.security.token;



import java.security.Principal;
import java.util.Optional;

/**
 * Created by vinay on 2/3/16.
 */
public class H2OPrincipal implements Principal {

    public H2OPrincipal(Optional<String> remoteAddr,
                        Optional<String> applicationId,
                        Optional<String> tenant,
                        Optional<String> name) {
        this.name = name;
        this.applicationId = applicationId;
        this.tenant = tenant;
        this.remoteAddr = remoteAddr;
    }

    @Override
    public String getName() {
        return name.get();
    }

    public Optional<String> getOptionalName() {
        return name;
    }
    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Optional<String> applicationId) {
        this.applicationId = applicationId;
    }

    public Optional<String> getTenant() {
        return tenant;
    }

    public void setTenant(Optional<String> tenant) {
        this.tenant = tenant;
    }

    public Optional<String> getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(Optional<String> remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public boolean isValid() {
        return validField(name) && validField(tenant) && validField(remoteAddr);
    }
    protected boolean validField(Optional<String> field) {
        return field.isPresent() && !field.get().isEmpty();
    }

    private Optional<String> name;
    private Optional<String> applicationId;
    private Optional<String> tenant;
    private Optional<String> remoteAddr;
}
