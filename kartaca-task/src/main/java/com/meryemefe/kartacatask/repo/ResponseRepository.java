package com.meryemefe.kartacatask.repo;

import com.meryemefe.kartacatask.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    List<Response> findAllByTimestampAfter(Timestamp timestamp);
}
