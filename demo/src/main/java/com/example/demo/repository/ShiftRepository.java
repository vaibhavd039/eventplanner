package com.example.demo.repository;

import com.example.demo.domain.Shift;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {

  List<Shift> findShiftByUserEntityId(Long userId);

  @Query("SELECT s FROM Shift s " +
   "WHERE s.userEntity.id IN :userId " +
   "AND s.startTime >= :start " +
   "AND s.endTime <= :end")
  List<Shift> findByUserIdAndTimeRange(
   @Param("userId") Set<Long> userId,
   @Param("start") Timestamp start,
   @Param("end") Timestamp end);
}
