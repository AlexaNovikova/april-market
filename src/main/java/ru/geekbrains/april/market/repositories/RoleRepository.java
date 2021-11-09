package ru.geekbrains.april.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.april.market.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
