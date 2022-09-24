package uz.pdp.appspringjpalesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appspringjpalesson7.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
