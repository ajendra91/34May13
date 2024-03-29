package hello;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Setter
@Getter
public class EmpCustom implements UserDetails {

    public  Emp emp;

    /*public EmpCustom(Emp emp){
        this.emp=emp;
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return emp.role.stream().map(r->

            new SimpleGrantedAuthority("ROLE_"+ r.role)
        ).collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return emp.password;
    }

    @Override
    public String getUsername() {
        return emp.name;
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

    public void setEmp(Emp emp) {
        this.emp = emp;
    }
}
