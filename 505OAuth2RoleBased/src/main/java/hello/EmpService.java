package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmpService implements UserDetailsService {

    @Autowired
    private EmpDao empDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Emp emp = empDao.findByName(username);
        EmpCustom empCustom = null;
        if (emp != null) {
            empCustom = new EmpCustom();
            empCustom.setEmp(emp);
        } else {
            throw new UsernameNotFoundException("User not exist with name : " + username);
        }
        return empCustom;

        /*Optional<Emp> emp =empDao.findByname(username);
        emp.orElseThrow(()->
                new UsernameNotFoundException("user name not found"));
        return emp.map(EmpCustom::new).get();*/
    }
}
