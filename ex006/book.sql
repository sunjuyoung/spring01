--패스워드 만료기간 확인
select * from dba_profiles;

--오라클 사용자 정보 확인
select * from SYS.DBA_USERS;

alter user bookEX IDENTIFIED by 1234; 


create table test_table (username varchar(20), usernum int);


create sequence seq_board;

--게시판 테이블 생성
create table tb1_board(
bno number(10,0),
title varchar2(200) not null,
content varchar2(2000) not null,
writer varchar2(50) not null,
regdate date default sysdate,
updatedate date default sysdate

);





select /*+ INDEX_DESC (tb1_board pk_board)*/ * from (
select   /*+ INDEX_DESC (tb1_board pk_board)*/ bno,title,content,writer,regdate,updateDate,replyCnt,rownum rn 
from tb1_board where (title like '%채원%' or content like '%채원%') and rownum < 10) 
where rn >0 ;


select /*+ INDEX_DESC (tb1_board pk_board)*/ * from (
select   /*+ INDEX_DESC (tb1_board pk_board)*/ bno,title,content,writer,regdate,updateDate,replyCnt,userid,rownum rn 
from tb1_board where rownum < 10) 
where rn >0 ;


select count(*) from tb1_board;



select /*+ INDEX_ASC (tb1_board pk_board) */ * from tb1_board;






alter table tb1_board add constraint pk_board primary key (bno);


insert into tb1_board (bno,title,content,writer )values (SEQ_BOARD.nextval, '테dwefwejfpejp목','테스트 내용','user01');

select * from tb1_board order by bno desc;

delete from tb1_board where bno=33982;

select * from tb1_board where bno = 22;

select * from tb1_board where bno > 0;

insert into tb1_board(bno,title,content,writer)(select seq_board.nextval,title,content,writer from tb1_board);


select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title from tb1_board where rownum <= 10;

select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title,content,writer,regDate,updateDate from 
(select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title,content,writer,regDate,updateDate from tb1_board where rownum <= 10) where rn > 0;

--AND연산자가 OR연산자 보다 우선순위가 높다
-- ROWNUM이 20보다 작거나같으면서 내용에 TEST라는 문자열이 있거나OR 제목에 테스트 라는 문자열이 있는 게시물을 검색하게된다
select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title,content,writer,regDate,updateDate from 
(select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title,content,writer,regDate,updateDate from tb1_board where 
 title like '%테스트%' or content like '%테스트%' and rownum <= 10) where rn > 0;

--AND OR 섞여잇는 경우 우선순위 연산자인 () 을 이용해서 OR조건들을 처리해야한다
select * from (select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title,content,writer,regDate,updateDate from tb1_board where
  (title like '%테스트%' or content like '%테스트%') and rownum <= 10) where rn > 0;


select /*+ INDEX_ASC(tb1_board pk_board) */   rownum rn, bno, title from tb1_board ;

select /*+ FULL(tb1_board) */ rownum rn, bno, title from tb1_board where bno>0 order by bno;

select rownum rn, bno, title from tb1_board;


select 
  bno,title,writer,regDate,updateDate 
  from
  ( select /*+ INDEX_DESC(tb1_board,pk_board) */
   rownum rn, bno,title,writer,regDate,updateDate
    from
      tb1_board
       where rownum <=20)
       where rn > 10;

       
       
select count(*)as count from tb1_board;



--댓글처리를 위한 테이블 생성
create table tb1_reply(
    rno number(10,0),
    bno number(10,0) not null,
    reply varchar2(1000) not null,
    replyer varchar2(50) not null,
    replyDate date default sysdate,
    upateDate date default sysdate
    );
    
    
    
create sequence seq_reply;
    
alter table tb1_reply add constraint pk_reply primary key (rno);
    
alter table tb1_reply add constraint fk_reply_board
foreign key(bno) references tb1_board(bno) on delete cascade;

alter table tb1_reply drop constraint fk_reply_board;
    
    
select * from tb1_board;

select * from tb1_board where rownum <10 ;

select * from tb1_reply order by rno desc;

insert into tb1_reply (rno,bno,reply,replyer)  values(99,23,'댓글테스트','테스터');

select rno , bno, reply, replyer,replyDate,updateDate from tb1_reply;

select rno , bno, reply, replyer,replyDate, updateDate from tb1_reply where bno = 23 order by rno desc;

update tb1_reply set reply = '댓글수정' , updateDate = sysdate where rno=2;




--댓글 페이징   --p429
select bno,rno,reply,replyer,replyDate,updateDate from (
select /*+INDEX (tb1_reply idx_reply) */ rownum rn,bno,rno,reply,replyer,replyDate,updateDate 
from tb1_reply where bno=23 and rno >0 and rownum <5) where rn >0;


-- 댓글의 수를 의미하는 replyCnt 칼럼 추가
alter table tb1_board add (replyCnt number default 0);

-- replyCnt 업데이트
update tb1_board set replyCnt = (select count(bno) from tb1_reply where tb1_board.bno = tb1_reply.bno);


--로그인
alter table tb1_board add   userid varchar2(50);

alter table tb1_board add constraint fk_tb1_board_userid foreign key(userid) references tbl_member(userid);

select * from tb1_board;

select b.userid ,b.title from tb1_board b left outer join tbl_member m on b.userid = m.userid;

select * from tbl_member;



insert into tb1_board (bno,title,content,writer,userid) values ((select max(bno)+1 from tb1_board),'hellow','테스트3내용입니다.','일반사용자1','user1');

--글 삭제 가능






