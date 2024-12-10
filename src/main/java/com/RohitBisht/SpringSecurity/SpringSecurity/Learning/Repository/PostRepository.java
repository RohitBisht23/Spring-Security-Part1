package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Repository;

import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
