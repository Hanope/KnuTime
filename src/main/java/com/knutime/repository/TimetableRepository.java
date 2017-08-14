package com.knutime.repository;

import com.knutime.domain.timetable.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    Timetable findOneBySerialNumber(String serialNumber);

    boolean existsBySerialNumber(String serialNumber);
}
