package co.istad.mbanking.features.user;

import co.istad.mbanking.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("""
    SELECT r FROM 
    Role AS r
     WHERE r.name = 'USER' 
""")
    Role findRoleUser();

    @Query("""
    SELECT r FROM Role AS r
    WHERE r.name = 'CUSTOMER'
    """)
    Role findRoleCustomer();

}
