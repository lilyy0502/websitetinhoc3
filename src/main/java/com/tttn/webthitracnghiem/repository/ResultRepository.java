package com.tttn.webthitracnghiem.repository;

import com.tttn.webthitracnghiem.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    @Query(value = "WITH top as (SELECT *, ROW_NUMBER() over(partition by username,id_exam order by mark desc) as num from result) SELECT id,end_time,start_time,id_exam,mark,username from top where num = 1 order by mark desc limit 10", nativeQuery = true)
    List<Result> findTopTen();

    @Query(value = "SELECT * FROM result where username = :id ", nativeQuery = true)
    List<Result> findByHistory(@Param("id") String id);

    @Query(value = "SELECT SUM(mark)\n" +
            "FROM result\n" +
            "WHERE username= :id ", nativeQuery = true)
    Object findSum(@Param("id") String id);

    @Query(value = "SELECT AVG (mark)\n" +
            "FROM result\n" +
            "WHERE username= :id ", nativeQuery = true)
    Object findAvg(@Param("id") String id);

    @Query(value = "SELECT * FROM result where id_exam= :idExam order by mark desc", nativeQuery = true)
    List<Result> findTopByExam(@Param("idExam") int idExam);
}
