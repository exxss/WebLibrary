package ru.dob.library.WebLibrary.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.dob.library.WebLibrary.models.Staff;

import java.util.Collection;
import java.util.Collections;


public class StaffDetails implements UserDetails {
    private final Staff staff;

    public StaffDetails(Staff staff) {
        this.staff = staff;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(staff.getRole()));
    }

    @Override
    public String getPassword() {
        return this.staff.getPassword();
    }

    @Override
    public String getUsername() {
        return this.staff.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Staff getStaff() {
        return this.staff;
    }
}
