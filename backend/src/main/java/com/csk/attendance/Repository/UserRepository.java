package com.csk.attendance.Repository;

import com.csk.attendance.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
