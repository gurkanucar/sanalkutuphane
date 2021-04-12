package com.gucarsoft.sanalkutuphane.repository;

import com.gucarsoft.sanalkutuphane.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

}
