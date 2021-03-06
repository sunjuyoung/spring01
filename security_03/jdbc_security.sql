create table users(
    username varchar2(20) not null primary key,
    password varchar2(30) not null,
    enabled char(1) default '1'

);

create table authorities(
    username varchar2(20) not null ,
    authority varchar2(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)


);

insert into users (username,password) values ('sun','1234');
insert into users (username,password) values ('ju','1234');
insert into users (username,password) values ('hi','1234');

insert into authorities (username,authority) values ('sun','ROLE_ADMIN');
insert into authorities (username,authority) values ('sun','ROLE_MANAGER');
insert into authorities (username,authority) values ('ju','ROLE_MANAGER');
insert into authorities (username,authority) values ('hi','ROLE_USER');

commit;

select * from users;
select * from authorities;

----------------------------------------------------------------------------------


-- 650p

create table tbl_member(
      userid varchar2(50) not null primary key,
      userpw varchar2(100) not null,
      username varchar2(100) not null,
      regdate date default sysdate, 
      updatedate date default sysdate,
      enabled char(1) default '1');


create table tbl_member_auth (
     userid varchar2(50) not null,
     auth varchar2(50) not null,
     constraint fk_member_auth foreign key(userid) references tbl_member(userid)
);

-- 시큐리티의 로그인 정보 유지하는 테이블
create table persistent_logins(
username varchar2(64) not null,
series varchar2(64) primary key,
token varchar2(64) not null,
last_used TIMESTAMP not null
);



select * from tbl_member;

select * from tbl_member_auth;

delete  from tbl_member_auth;

-- 인증을 하는데 필요한 쿼리 users-by-username-query
select userid, userpw password,enabled from tbl_member where userid='admin90';

--권한을 확인하는데 필요한 쿼리 authorities-by-username-query
select userid username, auth authority from tbl_member_auth where userid='admin90';

--


-------------------------------------
--한사용자가 여러 권한을 가질수있다
-- MemberVO tbl_member 하나이고 AuthVO는 2개이상되어야하는 결과
select 
	mem.userid,userpw,username,enabled,regdate,updatedate,auth 
from
	tbl_member mem LEFT OUTER JOIN tbl_member_auth auth on mem.userid = auth.userid 
where mem.userid='manager80';






alter table tb1_board add   userid varchar2(50);

alter table tb1_board add constraint fk_tb1_board_userid foreign key(userid) references tbl_member(userid);

select count(*) from tb1_board;

alter table tbl_member add  email varchar2(50);

select * from tbl_member;
 
 select * from tb1_board;
 

update tb1_board set userid = 'admin90' where  bno>=33899  and bno<34021;


