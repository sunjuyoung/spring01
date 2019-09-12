
 

select r.rno,b.bno,b.content from tb1_board b inner join tb1_reply r on r.bno = b.bno;

select * from tb1_reply ;
  update tb1_reply set reply='tsetsetset', updateDate=sysdate where rno=1;

  select * from tb1_reply where bno = 23 order by rno asc;

select * from table(dbms_xplan.display_cursor(sql_id=>'a0ard087bnpgv', format=>'ALLSTATS LAST'));


  insert into tb1_reply (rno,bno,reply,replyer) values (SEQ_REPLY.nextval,104,'이런','아아');
  insert into tb1_reply (rno,bno,reply,replyer) values(99,23,'댓글테스트','테스터');
  
  --p429
  create index idx_reply on tb1_reply (bno desc,rno asc);
  --p429
select /*+INDEX (tb1_reply idx_reply) */ rownum rn,bno,rno,reply,replyer,replyDate,updateDate 
from tb1_reply where bno=23 and rno >0 ;

--댓글 페이징   --p429
select bno,rno,reply,replyer,replyDate,updateDate from (
select /*+INDEX (tb1_reply idx_reply) */ rownum rn,bno,rno,reply,replyer,replyDate,updateDate 
from tb1_reply where bno=23 and rno >0 and rownum <5) where rn >0;


select count(rno) from tb1_reply where bno = 23;

select * from tb1_sample2;

delete tb1_sample1;


select bno,rno,reply,replyer,replyDate,updateDate 
from (
select /*+INDEX (tb1_reply idx_reply) */
 rownum rn,bno,rno,reply,replyer,replyDate,updateDate 
from tb1_reply
 where bno=23
 and rno > 0 
 and rownum <=5 )
 where rn >  0;
  