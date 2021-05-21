package ru.geekbrains.april.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.april.market.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
