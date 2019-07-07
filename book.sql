create table test_table (username varchar(20), usernum int);


create sequence seq_board;

--�Խ��� ���̺� ����
create table tb1_board(
bno number(10,0),
title varchar2(200) not null,
content varchar2(2000) not null,
writer varchar2(50) not null,
regdate date default sysdate,
updatedate date default sysdate

);

alter table tb1_board add constraint pk_board primary key (bno);


insert into tb1_board (bno,title,content,writer )values (SEQ_BOARD.nextval, '��dwefwejfpejp��','�׽�Ʈ ����','user01');

select * from tb1_board order by bno desc;

delete from tb1_board where bno=33982;

select * from tb1_board where bno = 22;

select * from tb1_board where bno > 0;

insert into tb1_board(bno,title,content,writer)(select seq_board.nextval,title,content,writer from tb1_board);


select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title from tb1_board where rownum <= 10;

select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title,content,writer,regDate,updateDate from 
(select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title,content,writer,regDate,updateDate from tb1_board where rownum <= 10) where rn > 0;

--AND�����ڰ� OR������ ���� �켱������ ����
-- ROWNUM�� 20���� �۰ų������鼭 ���뿡 TEST��� ���ڿ��� �ְų�OR ���� �׽�Ʈ ��� ���ڿ��� �ִ� �Խù��� �˻��ϰԵȴ�
select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title,content,writer,regDate,updateDate from 
(select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title,content,writer,regDate,updateDate from tb1_board where 
 title like '%�׽�Ʈ%' or content like '%�׽�Ʈ%' and rownum <= 10) where rn > 0;

--AND OR �����մ� ��� �켱���� �������� () �� �̿��ؼ� OR���ǵ��� ó���ؾ��Ѵ�
select * from (select /*+ INDEX_DESC(tb1_board pk_board) */ rownum rn, bno, title,content,writer,regDate,updateDate from tb1_board where
  (title like '%�׽�Ʈ%' or content like '%�׽�Ʈ%') and rownum <= 10) where rn > 0;


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



--���ó���� ���� ���̺� ����
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
    foreign key(bno) references tb1_board(bno);
select * from tb1_board where bno <10;

select * from tb1_board where rownum <10 ;
select * from tb1_reply order by rno desc;

insert into tb1_reply (rno,bno,reply,replyer)  values(99,23,'����׽�Ʈ','�׽���');

select rno , bno, reply, replyer,replyDate,updateDate from tb1_reply;

select rno , bno, reply, replyer,replyDate, updateDate from tb1_reply where bno = 23 order by rno desc;

update tb1_reply set reply = '��ۼ���' , updateDate = sysdate where rno=2;




--��� ����¡   --p429
select bno,rno,reply,replyer,replyDate,updateDate from (
select /*+INDEX (tb1_reply idx_reply) */ rownum rn,bno,rno,reply,replyer,replyDate,updateDate 
from tb1_reply where bno=23 and rno >0 and rownum <5) where rn >0;
