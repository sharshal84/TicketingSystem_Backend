package com.Wizpro.Ticketing.System.Repository;

import com.Wizpro.Ticketing.System.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<Comment,Long> {
        @Query(nativeQuery = true,value = "select c.* from Comment c where c.ticket_id=:id")
        List<Comment> findByTicket_id(int id);


        @Query(nativeQuery = true,value = "select c.*,cu.name as customer from comment c inner join customer_ticket t on c.ticket_id=t.id inner join customer cu on t.customer=cu.id where c.ticket_id=:id")
        List<Comment> findAllComments(int id);

        @Query(nativeQuery = true,value = "select c.*,cu.name as customer from comment c inner join customer_ticket t on c.ticket_id=t.id inner join customer cu on t.customer=cu.id where c.ticket_id=:id order by timestamp desc limit 1")
        Comment findSingleCommentBy(int id);
}
